package com.dousnl.utils.freemud;

import com.dousnl.utils.IntegerEncryptTool;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * 加解密工具，基于AES/ECB/PKCS5Padding
 *
 */
public class EncryptHelper {

    private static final String AES_ECB_PKCS5_PADDING = "AES/ECB/PKCS5Padding";
    private static final String AES = "AES";
    public static final Logger LOGGER = LoggerFactory.getLogger(EncryptHelper.class);
    private static EncryptHelper instance = new EncryptHelper();
    private static byte[] secretKey = "gSPs4aHZL1ocan1w".getBytes();

    private EncryptHelper() {}

    public static EncryptHelper getInstance() {
        return instance;
    }

    /**
     * 由于Java原生jdk只支持128位AES秘钥，故而如果要秘钥长度超过128位，则会：产生
     * 异常java.security.InvalidKeyException:illegal Key Size
     * 如果需要扩展为256位的秘钥，解决方案为：
     * <ol>
     * 	<li>在官方网站下载JCE无限制权限策略文件（JDK7的下载地址：
     *      http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html</li>
     * 	<li>下载后解压，可以看到local_policy.jar和US_export_policy.jar以及readme.txt</li>
     * 	<li>如果安装了JRE，将两个jar文件放到%JRE_HOME%\lib\security目录下覆盖原来的文件</li>
     * 	<li>如果安装了JDK，将两个jar文件放到%JDK_HOME%\jre\lib\security目录下覆盖原来文件</li>
     * </ol>
     * @param secretKey
     * @throws RuntimeException
     */
    public void setSecretKey(byte[] secretKey) {
        EncryptHelper.secretKey = secretKey;
    }

    public String encrypt(String source) {
        if (source == null) {
            return null;
        }
        byte[] encryptBytes = encrypt(source.getBytes(Charset.forName("utf-8")));
        return String.valueOf(Hex.encodeHex(encryptBytes));
    }

    public byte[] encrypt(byte[] source) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(secretKey, AES);
            Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            return cipher.doFinal(source);
        } catch (Exception e) {
            LOGGER.error("Encrypt error.", e);
            return null;
        }
    }

    public String decrypt(String data) {
        try {
            byte[] bytes = Hex.decodeHex(data.toCharArray());
            //
            String str = new String(decrypt(bytes), Charset.forName("utf-8"));
            Base64.getEncoder().encodeToString(decrypt(bytes));
            ///
            return new String(decrypt(bytes), Charset.forName("utf-8"));
        } catch (Exception e) {
            LOGGER.error("Encrypt error.", e);
            return null;
        }
    }

    public byte[] decrypt(byte[] data) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(secretKey, AES);
            Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            LOGGER.error("Decrypt error.", e);
            return null;
        }
    }

    public static void main(String[] args) {
        String str="FEfp88PC7E4n2eDx06q";
        long start = System.currentTimeMillis();
        String encrypt = EncryptHelper.getInstance().encrypt(str);
        System.out.println(encrypt);
        String decrypt = EncryptHelper.getInstance().decrypt(encrypt);
        System.out.println(decrypt);
        System.out.println((System.currentTimeMillis()-start)+"ms");

        long start1 = System.currentTimeMillis();
        String encrypt1 = IntegerEncryptTool.encrypt(1114);
        System.out.println(encrypt1);
        int decrypt1 = IntegerEncryptTool.decrypt("C94ExpcM4jLwd47Tg6x");
        System.out.println(decrypt1);
        System.out.println((System.currentTimeMillis()-start1)+"ms");

        System.out.println(1<<3);
    }
}
