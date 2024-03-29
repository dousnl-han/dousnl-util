package com.dousnl.utils.freemud;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HashIds designed for Generating short hashes from numbers (like YouTube and Bitly), obfuscate
 * database IDs, use them as forgotten password hashes, invitation codes, store shard numbers.
 * This is implementation of http://hashids.org v1.0.0 version.
 * <p>
 * This implementation is immutable, thread-safe, no lock is necessary.
 */
public class HashIds {
    /**
     * Max number that can be encoded with HashIds.
     */
    public static final long MAX_NUMBER = 9007199254740992L;

    private static final String DEFAULT_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final String DEFAULT_SEPS = "cfhistuCFHISTU";
    private static final String DEFAULT_SALT = "8M4F2arfeY3pN9";

    private static final int DEFAULT_MIN_HASH_LENGTH = 4;
    private static final int MIN_ALPHABET_LENGTH = 16;
    private static final double SEP_DIV = 3.5;
    private static final int GUARD_DIV = 12;

    private final String salt;
    private final int minHashLength;
    private final String alphabet;
    private final String seps;
    private final String guards;

    public HashIds() {
        this(DEFAULT_SALT);
    }

    public HashIds(String salt) {
        this(salt, 0);
    }

    public HashIds(String salt, int minHashLength) {
        this(salt, minHashLength, DEFAULT_ALPHABET);
    }

    public HashIds(String salt, int minHashLength, String alphabet) {
        this.salt = salt != null ? salt : DEFAULT_SALT;
        this.minHashLength = minHashLength > 0 ? minHashLength : DEFAULT_MIN_HASH_LENGTH;

        StringBuilder uniqueAlphabet = new StringBuilder();
        for (int i = 0; i < alphabet.length(); i++) {
            if (uniqueAlphabet.indexOf(String.valueOf(alphabet.charAt(i))) == -1) {
                uniqueAlphabet.append(alphabet.charAt(i));
            }
        }

        alphabet = uniqueAlphabet.toString();

        if (alphabet.length() < MIN_ALPHABET_LENGTH) {
            throw new IllegalArgumentException(
                    "alphabet must contain at least " + MIN_ALPHABET_LENGTH + " unique characters");
        }

        if (alphabet.contains(" ")) {
            throw new IllegalArgumentException("alphabet cannot contains spaces");
        }

        // seps should contain only characters present in alphabet;
        // alphabet should not contains seps
        String seps = DEFAULT_SEPS;
        for (int i = 0; i < seps.length(); i++) {
            int j = alphabet.indexOf(seps.charAt(i));
            if (j == -1) {
                seps = seps.substring(0, i) + " " + seps.substring(i + 1);
            } else {
                alphabet = alphabet.substring(0, j) + " " + alphabet.substring(j + 1);
            }
        }

        alphabet = alphabet.replaceAll("\\s+", "");
        seps = seps.replaceAll("\\s+", "");
        seps = HashIds.consistentShuffle(seps, this.salt);

        if ((seps.isEmpty()) || (((float) alphabet.length() / seps.length()) > SEP_DIV)) {
            int seps_len = (int) Math.ceil(alphabet.length() / SEP_DIV);

            if (seps_len == 1) {
                seps_len++;
            }

            if (seps_len > seps.length()) {
                int diff = seps_len - seps.length();
                seps += alphabet.substring(0, diff);
                alphabet = alphabet.substring(diff);
            } else {
                seps = seps.substring(0, seps_len);
            }
        }

        alphabet = HashIds.consistentShuffle(alphabet, this.salt);
        // use double to round up
        int guardCount = (int) Math.ceil((double) alphabet.length() / GUARD_DIV);

        String guards;
        if (alphabet.length() < 3) {
            guards = seps.substring(0, guardCount);
            seps = seps.substring(guardCount);
        } else {
            guards = alphabet.substring(0, guardCount);
            alphabet = alphabet.substring(guardCount);
        }
        this.guards = guards;
        this.alphabet = alphabet;
        this.seps = seps;
    }

    /**
     * Encrypt numbers to string
     *
     * @param numbers the numbers to encrypt
     * @return the encrypt string
     */
    public String encode(long... numbers) {
        if (numbers.length == 0) {
            return "";
        }

        for (long number : numbers) {
            if (number < 0) {
                return "";
            }
            if (number > MAX_NUMBER) {
                throw new IllegalArgumentException("number can not be greater than " + MAX_NUMBER + "L");
            }
        }
        return this._encode(numbers);
    }

    /**
     * Decrypt string to numbers
     *
     * @param hash the encrypt string
     * @return decryped numbers
     */
    public long[] decode(String hash) {
        if (hash.isEmpty()){
            return new long[0];
        }
        return this._decode(hash, this.alphabet);
    }

    /**
     * Encrypt hexa to string
     *
     * @param hexa the hexa to encrypt
     * @return the encrypt string
     */
    public String encodeHex(String hexa) {
        if (!hexa.matches("^[0-9a-fA-F]+$")){
            return "";
        }
        List<Long> matched = new ArrayList<Long>();
        Matcher matcher = Pattern.compile("[\\w\\W]{1,12}").matcher(hexa);

        while (matcher.find())
            matched.add(Long.parseLong("1" + matcher.group(), 16));

        // conversion
        long[] result = new long[matched.size()];
        for (int i = 0; i < matched.size(); i++)
            result[i] = matched.get(i);

        return this._encode(result);
    }

    /**
     * Decrypt string to numbers
     *
     * @param hash the encrypt string
     * @return decryped numbers
     */
    public String decodeHex(String hash) {
        StringBuilder result = new StringBuilder();
        long[] numbers = this.decode(hash);

        for (long number : numbers) {
            result.append(Long.toHexString(number).substring(1));
        }

        return result.toString();
    }

    public static int checkedCast(long value) {
        int result = (int) value;
        if (result != value) {
            // don't use checkArgument here, to avoid boxing
            throw new IllegalArgumentException("Out of range: " + value);
        }
        return result;
    }

    /* Private methods */

    private String _encode(long... numbers) {
        long numberHashInt = 0;
        for (int i = 0; i < numbers.length; i++) {
            numberHashInt += (numbers[i] % (i + 100));
        }
        String alphabet = this.alphabet;
        char ret = alphabet.charAt((int) (numberHashInt % alphabet.length()));
        // char lottery = ret;
        long num;
        long sepsIndex, guardIndex;
        String buffer;
        StringBuilder ret_strB = new StringBuilder(minHashLength);
        ret_strB.append(ret);
        char guard;

        for (int i = 0; i < numbers.length; i++) {
            num = numbers[i];
            buffer = ret + this.salt + alphabet;

            alphabet = HashIds.consistentShuffle(alphabet, buffer.substring(0, alphabet.length()));
            String last = HashIds.hash(num, alphabet);

            ret_strB.append(last);

            if (i + 1 < numbers.length) {
                num %= ((int) last.charAt(0) + i);
                sepsIndex = (int) (num % this.seps.length());
                ret_strB.append(this.seps.charAt((int) sepsIndex));
            }
        }

        String ret_str = ret_strB.toString();
        if (ret_str.length() < this.minHashLength) {
            guardIndex = (numberHashInt + (int) (ret_str.charAt(0))) % this.guards.length();
            guard = this.guards.charAt((int) guardIndex);

            ret_str = guard + ret_str;

            if (ret_str.length() < this.minHashLength) {
                guardIndex = (numberHashInt + (int) (ret_str.charAt(2))) % this.guards.length();
                guard = this.guards.charAt((int) guardIndex);

                ret_str += guard;
            }
        }

        int halfLen = alphabet.length() / 2;
        while (ret_str.length() < this.minHashLength) {
            alphabet = HashIds.consistentShuffle(alphabet, alphabet);
            ret_str = alphabet.substring(halfLen) + ret_str + alphabet.substring(0, halfLen);
            int excess = ret_str.length() - this.minHashLength;
            if (excess > 0) {
                int start_pos = excess / 2;
                ret_str = ret_str.substring(start_pos, start_pos + this.minHashLength);
            }
        }

        return ret_str;
    }

    private long[] _decode(String hash, String alphabet) {
        ArrayList<Long> ret = new ArrayList<Long>();

        int i = 0;
        String regexp = "[" + this.guards + "]";
        String hashBreakdown = hash.replaceAll(regexp, " ");
        String[] hashArray = hashBreakdown.split(" ");

        if (hashArray.length == 3 || hashArray.length == 2) {
            i = 1;
        }

        hashBreakdown = hashArray[i];
        if (!hashBreakdown.isEmpty()) {
            char lottery = hashBreakdown.charAt(0);

            hashBreakdown = hashBreakdown.substring(1);
            hashBreakdown = hashBreakdown.replaceAll("[" + this.seps + "]", " ");
            hashArray = hashBreakdown.split(" ");

            String subHash, buffer;
            for (String aHashArray : hashArray) {
                subHash = aHashArray;
                buffer = lottery + this.salt + alphabet;
                alphabet = HashIds.consistentShuffle(alphabet, buffer.substring(0, alphabet.length()));
                ret.add(HashIds.unhash(subHash, alphabet));
            }
        }

        // transform from List<Long> to long[]
        long[] arr = new long[ret.size()];
        for (int k = 0; k < arr.length; k++) {
            arr[k] = ret.get(k);
        }

        if (!this._encode(arr).equals(hash)) {
            arr = new long[0];
        }

        return arr;
    }

    private static String consistentShuffle(String alphabet, String salt) {
        if (salt.length() <= 0)
            return alphabet;

        int asc_val, j;
        char[] tmpArr = alphabet.toCharArray();
        for (int i = tmpArr.length - 1, v = 0, p = 0; i > 0; i--, v++) {
            v %= salt.length();
            asc_val = (int) salt.charAt(v);
            p += asc_val;
            j = (asc_val + v + p) % i;
            char tmp = tmpArr[j];
            tmpArr[j] = tmpArr[i];
            tmpArr[i] = tmp;
        }

        return new String(tmpArr);
    }

    private static String hash(long input, String alphabet) {
        String hash = "";
        int alphabetLen = alphabet.length();

        do {
            hash = alphabet.charAt((int) (input % alphabetLen)) + hash;
            input /= alphabetLen;
        } while (input > 0);

        return hash;
    }

    private static Long unhash(String input, String alphabet) {
        long number = 0, pos;

        for (int i = 0; i < input.length(); i++) {
            pos = alphabet.indexOf(input.charAt(i));
            number += pos * Math.pow(alphabet.length(), input.length() - i - 1);
        }

        return number;
    }

    /**
     * Get Hashid algorithm version.
     *
     * @return HashIds algorithm version implemented.
     */
    public String getVersion() {
        return "1.0.0";
    }
}
