package com.dousnl.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/5/19 14:40
 */
public class ShortUUID {
    static final char[] DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    static final Map<Character, Integer> DIGITMAP = new HashMap();
    private static final int MAX_RADIX;
    private static final int MIN_RADIX = 2;

    public ShortUUID() {
    }

    private static String toString(long i, int radix) {
        if (radix < 2 || radix > MAX_RADIX) {
            radix = 10;
        }

        if (radix == 10) {
            return Long.toString(i);
        } else {
            boolean size = true;
            int charPos = 64;
            char[] buf = new char[65];
            boolean negative = i < 0L;
            if (!negative) {
                i = -i;
            }

            while(i <= (long)(-radix)) {
                buf[charPos--] = DIGITS[(int)(-(i % (long)radix))];
                i /= (long)radix;
            }

            buf[charPos] = DIGITS[(int)(-i)];
            if (negative) {
                --charPos;
                buf[charPos] = '-';
            }

            return new String(buf, charPos, 65 - charPos);
        }
    }

    private static NumberFormatException forInputString(String s) {
        return new NumberFormatException("For input string: \"" + s + "\"");
    }

    private static long toNumber(String s, int radix) {
        if (s == null) {
            throw new NumberFormatException("null");
        } else if (radix < 2) {
            throw new NumberFormatException("radix " + radix + " less than Numbers.MIN_RADIX");
        } else if (radix > MAX_RADIX) {
            throw new NumberFormatException("radix " + radix + " greater than Numbers.MAX_RADIX");
        } else {
            long result = 0L;
            boolean negative = false;
            int i = 0;
            int len = s.length();
            long limit = -9223372036854775807L;
            if (len > 0) {
                char firstChar = s.charAt(0);
                if (firstChar < '0') {
                    if (firstChar == '-') {
                        negative = true;
                        limit = -9223372036854775808L;
                    } else if (firstChar != '+') {
                        throw forInputString(s);
                    }

                    if (len == 1) {
                        throw forInputString(s);
                    }

                    ++i;
                }

                Integer digit;
                for(long multmin = limit / (long)radix; i < len; result -= (long)digit) {
                    digit = (Integer)DIGITMAP.get(s.charAt(i++));
                    if (digit == null) {
                        throw forInputString(s);
                    }

                    if (digit < 0) {
                        throw forInputString(s);
                    }

                    if (result < multmin) {
                        throw forInputString(s);
                    }

                    result *= (long)radix;
                    if (result < limit + (long)digit) {
                        throw forInputString(s);
                    }
                }

                return negative ? result : -result;
            } else {
                throw forInputString(s);
            }
        }
    }

    private static String digits(long val, int digits) {
        long hi = 1L << digits * 4;
        return toString(hi | val & hi - 1L, MAX_RADIX).substring(1);
    }

    public static String generate() {
        UUID uuid = UUID.randomUUID();
        StringBuilder sb = new StringBuilder();
        System.out.println("uuid.getMostSignificantBits():"+uuid.getMostSignificantBits());
        sb.append(digits(uuid.getMostSignificantBits() >> 32, 8));
        System.out.println("sb:"+sb);
        sb.append(digits(uuid.getMostSignificantBits() >> 16, 4));
        System.out.println("sb:"+sb);
        sb.append(digits(uuid.getMostSignificantBits(), 4));
        System.out.println("sb:"+sb);
        sb.append(digits(uuid.getLeastSignificantBits() >> 48, 4));
        System.out.println("sb:"+sb);
        sb.append(digits(uuid.getLeastSignificantBits(), 12));
        System.out.println("sb:"+sb);
        return sb.toString();
    }

    static {
        for(int i = 0; i < DIGITS.length; ++i) {
            DIGITMAP.put(DIGITS[i], i);
        }

        MAX_RADIX = DIGITS.length;
    }

    public static void main(String[] args) {
        String generate = generate();
        System.out.println(generate);
    }
}
