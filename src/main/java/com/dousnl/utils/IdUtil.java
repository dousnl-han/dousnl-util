package com.dousnl.utils;

import java.util.Date;
import java.util.UUID;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/1/15 10:47
 */
public class IdUtil {

    private static final String BASE32_ALPHABET = "ABCDEFGHIJKLMNoPQRSTUVWXYZ234567";

    public IdUtil() {
    }

    public static String getUniqueId() {
        String uuid = UUID.randomUUID().toString();
        return uuidToBase32(uuid);
    }

    public static String uuidToBase32(String uuid) {
        String binaryString = uuidToBinaryString(uuid);
        return toBase32(binaryString);
    }

    private static String uuidToBinaryString(String uuid) {
        StringBuilder result = new StringBuilder();
        char[] var2 = uuid.toCharArray();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            char c = var2[var4];
            if (c != '-') {
                String binaryString = Integer.toBinaryString(Integer.parseInt(String.valueOf(c), 16));
                binaryString = "0000" + binaryString;
                binaryString = binaryString.substring(binaryString.length() - 4);
                result.append(binaryString);
            }
        }

        return result.toString();
    }

    public static String getGenerateNumber() {
        Long dt = System.currentTimeMillis();
        String dateString = dt.toString();
        return dateString;
    }

    public static String GenerateCardNo() {
        Date day = new Date();
        String dateString = String.valueOf(day.getTime() / 1000L);
        return dateString;
    }

    public static String GenerateOptCardNo() {
        Date day = new Date();
        String dateString = String.valueOf(day.getTime() / 1000L + 315360000L);
        return dateString;
    }

    private static String toBase32(String binaryString) {
        int binaryStringLength = binaryString.length();
        int loopMax = (binaryStringLength + 4) / 5;
        binaryString = binaryString + "0000";
        StringBuilder base32Str = new StringBuilder();

        for(int loopIndex = 0; loopIndex < loopMax; ++loopIndex) {
            String s = binaryString.substring(loopIndex * 5, loopIndex * 5 + 5);
            int val = Integer.parseInt(s, 2);
            base32Str.append("ABCDEFGHIJKLMNoPQRSTUVWXYZ234567".charAt(val));
        }

        return base32Str.toString();
    }

    public static void main(String[] args) {
        String uniqueId = IdUtil.getUniqueId();

        System.out.println(uniqueId);

        System.out.println(UUID.randomUUID().toString().replace("-",""));
        System.out.println(UUID.randomUUID().toString().replace("-",""));
        System.out.println(5.5/2);
        Long dt = System.currentTimeMillis();
        String dateString = dt.toString();

        System.out.println(dateString);
    }
}
