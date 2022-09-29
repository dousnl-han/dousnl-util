package com.dousnl.utils;

import java.security.SecureRandom;
import java.util.Random;

import com.sdb.util.Base64;
import com.sdb.util.SignUtil;

public class TestSign {

     public static void main(String[] args) throws Exception {
        String ALG = "DesEde/CBC/PKCS5Padding";
        
        String fsrc = "D://�û�//My Documents//1.xls"; 
        
        String key = "aaZn+QAmAPRLX73vvRx6FDoiopSGMsIT"; 
        
        //��Ҫ�л��ɼ��ܣ����н�if���false��Ϊtrue����
        if(false){
            Random random = new SecureRandom();
            byte[] bkey = new byte[24];
            random.nextBytes(bkey);
            key = new String(Base64.encode(bkey));
            System.out.println("����=[" + key + "]");
            String srcFile = fsrc;
            String zipFile = srcFile + ".zip";
            String encFile = srcFile + ".enc";
            // ��ѹ
            SignUtil.compress(srcFile, zipFile);
            // ����
            SignUtil.encrypt(zipFile, encFile, bkey, ALG, "DesEde", null);
        } else {
            String desFile = fsrc;
            
            String srcFile = fsrc + ".enc";
            String zipFile = fsrc + ".zip";
            byte[] bkey = Base64.decode(key.getBytes());
            // ����
            SignUtil.decrypt(srcFile, zipFile, bkey, ALG, "DesEde", null);
            // ��ѹ
            SignUtil.uncompress(zipFile, desFile);
        }
    }


}
