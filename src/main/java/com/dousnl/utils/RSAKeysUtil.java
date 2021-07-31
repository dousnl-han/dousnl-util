package com.dousnl.utils;

import com.alibaba.druid.util.Base64;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/19 11:03
 */
public class RSAKeysUtil {
    private static Logger logger = LoggerFactory.getLogger(RSAKeysUtil.class);
    private static final String DEFAULT_PRIVATE_KEY_STRING = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJurdByQ2IgBkIDpgbplcRwbmWkTSzDh6WgD6D/7OmGFdrNOXW9bYaEkvsEyfxIdPldGE77xHWMzixIICk15FpsNkayNvEYhn+PqcOoPBwqfclc+VYBTGuBi2fG6tCsaayedxd5gdoL85cWtEfj9wuPnO9341chwTObzsESw3Q7XAgMBAAECgYBNAc/6oZy8CyoU226VasE9ilmlpSm5EMiJ8ATgQcNror5JjSajg60ULj0/DV/W8ih4u+3gsx46iLDvRpLyKEiJvVkxq5JFzDSO9avoWcuECgu0WFRSABIW64xdsYMiDcQ58t1AhYW7wIBQujtufQRqSsWjYwf5rAqSHva3Mi3GQQJBANgC4dOnOJrVWARUHtanrcIOMHh72w+TY7tPDqSPqvKIwD4Oy5AYoX9qinxUV39KAU43kkLnEZV0xnz/DMi59DkCQQC4fOQFUbe4jR6QZgZFg1sNVwZNgR/wRPHCpgJXXChXVtyQJFUXAWuQZoh1uNsSHHi60FIV7Pi12xQqL4sIWLuPAkBbCGPpUaieP9i3Akh4x/OHO3mb3P6FwuSd1IRkP5OYYJ87x/prwKWWz+yK1vSE1UhVkT/XaA/V/3+Cv+3ATfWpAkASF5VY/DGTNKupp4e363DTdxRUJ3SVYH2BBDy4in4eSpNow1dg4S/GQnfha5Z4STiBwYo7QjkFxI1OscjlsaX7AkBY7FU95jYZ+V4jkE4nfP5nMIKwPm0OSb+EE/kGkT/duzjZd4VbOqk7m+bPg1/7lJp7Nn+wYqgt0h63QhfKbuqj";
    public static final String DEFAULT_PUBLIC_KEY_STRING = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbq3QckNiIAZCA6YG6ZXEcG5lpE0sw4eloA+g/+zphhXazTl1vW2GhJL7BMn8SHT5XRhO+8R1jM4sSCApNeRabDZGsjbxGIZ/j6nDqDwcKn3JXPlWAUxrgYtnxurQrGmsnncXeYHaC/OXFrRH4/cLj5zvd+NXIcEzm87BEsN0O1wIDAQAB";

    public RSAKeysUtil() {
    }

//    public static void main(String[] args) {
//        try {
//            System.out.println(decrypt("k6HHp9Dqe6IJfB2tl75ZAqG+FZW9mUjA7FZQdkxRgXeSw2mcdHrmp0mh10NxLmPMD8/hzNkqcbal7qYDtKFTuFdPfpsTrvKR7qL0aqq5N9b8nKiADxGcAQ5mkgfVOGvwhMxw2VFNZ2UnD2+QH4QfVOPANyFqdVnRShOT/2T+phM="));
//        } catch (Exception var2) {
//            logger.error("", var2);
//        }
//
//    }

    public static String decrypt(String cipherText) {
        try {
            return decrypt("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbq3QckNiIAZCA6YG6ZXEcG5lpE0sw4eloA+g/+zphhXazTl1vW2GhJL7BMn8SHT5XRhO+8R1jM4sSCApNeRabDZGsjbxGIZ/j6nDqDwcKn3JXPlWAUxrgYtnxurQrGmsnncXeYHaC/OXFrRH4/cLj5zvd+NXIcEzm87BEsN0O1wIDAQAB", cipherText);
        } catch (Exception var2) {
            logger.error("", var2);
            return "";
        }
    }

    public static String decrypt(String publicKeyText, String cipherText) throws Exception {
        PublicKey publicKey = getPublicKey(publicKeyText);
        String str = decrypt(publicKey, cipherText);
        return str;
    }

    public static PublicKey getPublicKeyByX509(String x509File) {
        if (x509File != null && x509File.length() != 0) {
            FileInputStream in = null;

            PublicKey var4;
            try {
                in = new FileInputStream(x509File);
                CertificateFactory factory = CertificateFactory.getInstance("X.509");
                Certificate cer = factory.generateCertificate(in);
                var4 = cer.getPublicKey();
            } catch (Exception var13) {
                throw new IllegalArgumentException("Failed to get public key", var13);
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException var12) {
                    logger.error("", var12);
                }

            }

            return var4;
        } else {
            return getPublicKey((String)null);
        }
    }

    public static PublicKey getPublicKey(String publicKeyText) {
        if (publicKeyText == null || publicKeyText.length() == 0) {
            publicKeyText = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCbq3QckNiIAZCA6YG6ZXEcG5lpE0sw4eloA+g/+zphhXazTl1vW2GhJL7BMn8SHT5XRhO+8R1jM4sSCApNeRabDZGsjbxGIZ/j6nDqDwcKn3JXPlWAUxrgYtnxurQrGmsnncXeYHaC/OXFrRH4/cLj5zvd+NXIcEzm87BEsN0O1wIDAQAB";
        }

        try {
            byte[] publicKeyBytes = Base64.base64ToByteArray(publicKeyText);
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA", "SunRsaSign");
            return keyFactory.generatePublic(x509KeySpec);
        } catch (Exception var4) {
            throw new IllegalArgumentException("Failed to get public key", var4);
        }
    }

    public static PublicKey getPublicKeyByPublicKeyFile(String publicKeyFile) {
        if (publicKeyFile != null && publicKeyFile.length() != 0) {
            FileInputStream in = null;

            try {
                in = new FileInputStream(publicKeyFile);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] b = new byte[64];

                int len;
                while((len = in.read(b)) != -1) {
                    out.write(b, 0, len);
                }

                byte[] publicKeyBytes = out.toByteArray();
                X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
                KeyFactory factory = KeyFactory.getInstance("RSA", "SunRsaSign");
                PublicKey var8 = factory.generatePublic(spec);
                return var8;
            } catch (Exception var17) {
                throw new IllegalArgumentException("Failed to get public key", var17);
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException var16) {
                    logger.error("", var16);
                }

            }
        } else {
            return getPublicKey((String)null);
        }
    }

    public static String decrypt(PublicKey publicKey, String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        try {
            cipher.init(2, publicKey);
        } catch (InvalidKeyException var7) {
            RSAPublicKey rsaPublicKey = (RSAPublicKey)publicKey;
            RSAPrivateKeySpec spec = new RSAPrivateKeySpec(rsaPublicKey.getModulus(), rsaPublicKey.getPublicExponent());
            Key fakePrivateKey = KeyFactory.getInstance("RSA").generatePrivate(spec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(2, fakePrivateKey);
        }

        if (cipherText != null && cipherText.length() != 0) {
            byte[] cipherBytes = Base64.base64ToByteArray(cipherText);
            byte[] plainBytes = cipher.doFinal(cipherBytes);
            return new String(plainBytes);
        } else {
            return cipherText;
        }
    }

    public static String encrypt(String plainText) throws Exception {
        return encrypt((String)null, plainText);
    }

    public static String encrypt(String key, String plainText) throws Exception {
        if (key == null) {
            key = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJurdByQ2IgBkIDpgbplcRwbmWkTSzDh6WgD6D/7OmGFdrNOXW9bYaEkvsEyfxIdPldGE77xHWMzixIICk15FpsNkayNvEYhn+PqcOoPBwqfclc+VYBTGuBi2fG6tCsaayedxd5gdoL85cWtEfj9wuPnO9341chwTObzsESw3Q7XAgMBAAECgYBNAc/6oZy8CyoU226VasE9ilmlpSm5EMiJ8ATgQcNror5JjSajg60ULj0/DV/W8ih4u+3gsx46iLDvRpLyKEiJvVkxq5JFzDSO9avoWcuECgu0WFRSABIW64xdsYMiDcQ58t1AhYW7wIBQujtufQRqSsWjYwf5rAqSHva3Mi3GQQJBANgC4dOnOJrVWARUHtanrcIOMHh72w+TY7tPDqSPqvKIwD4Oy5AYoX9qinxUV39KAU43kkLnEZV0xnz/DMi59DkCQQC4fOQFUbe4jR6QZgZFg1sNVwZNgR/wRPHCpgJXXChXVtyQJFUXAWuQZoh1uNsSHHi60FIV7Pi12xQqL4sIWLuPAkBbCGPpUaieP9i3Akh4x/OHO3mb3P6FwuSd1IRkP5OYYJ87x/prwKWWz+yK1vSE1UhVkT/XaA/V/3+Cv+3ATfWpAkASF5VY/DGTNKupp4e363DTdxRUJ3SVYH2BBDy4in4eSpNow1dg4S/GQnfha5Z4STiBwYo7QjkFxI1OscjlsaX7AkBY7FU95jYZ+V4jkE4nfP5nMIKwPm0OSb+EE/kGkT/duzjZd4VbOqk7m+bPg1/7lJp7Nn+wYqgt0h63QhfKbuqj";
        }

        byte[] keyBytes = Base64.base64ToByteArray(key);
        return encrypt(keyBytes, plainText);
    }

    public static String encrypt(byte[] keyBytes, String plainText) throws Exception {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance("RSA", "SunRsaSign");
        PrivateKey privateKey = factory.generatePrivate(spec);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

        try {
            cipher.init(1, privateKey);
        } catch (InvalidKeyException var10) {
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)privateKey;
            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(rsaPrivateKey.getModulus(), rsaPrivateKey.getPrivateExponent());
            Key fakePublicKey = KeyFactory.getInstance("RSA").generatePublic(publicKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(1, fakePublicKey);
        }

        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
        String encryptedString = Base64.byteArrayToBase64(encryptedBytes);
        return encryptedString;
    }

    public static byte[][] genKeyPairBytes(int keySize) throws NoSuchAlgorithmException, NoSuchProviderException {
        byte[][] keyPairBytes = new byte[2][];
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA", "SunRsaSign");
        gen.initialize(keySize, new SecureRandom());
        KeyPair pair = gen.generateKeyPair();
        keyPairBytes[0] = pair.getPrivate().getEncoded();
        keyPairBytes[1] = pair.getPublic().getEncoded();
        return keyPairBytes;
    }

    public static String[] genKeyPair(int keySize) throws NoSuchAlgorithmException, NoSuchProviderException {
        byte[][] keyPairBytes = genKeyPairBytes(keySize);
        String[] keyPairs = new String[]{Base64.byteArrayToBase64(keyPairBytes[0]), Base64.byteArrayToBase64(keyPairBytes[1])};
        return keyPairs;
    }


    public static void main(String[] args) {
        String str="NdochBwAilT88VKk/oNhHob85tsF2IDi3ydtSsLTVsgHy63HNk0SJrH+aXO6C7it0/PKd3mITRBv4PbD5SCUqkATX3+XwVPTdeu6M85qY6n6ESEdrHc6TVz0NSzAYsWKS1+gNJeWrlZ9zPI1c22JZbBSJU/eoOBws3Nd5yJPf44=";
        String decrypt = RSAKeysUtil.decrypt(str);
        System.out.println(decrypt);
        Integer type=1;
        if (type.equals(1)){
            System.out.println("11111111111111");
        }
    }
}
