package com.dousnl.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
/**
 * 压缩工具类---会出现乱码
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/2 18:53
 */

public class StringCompress {
    public static final byte[] compress(String paramString) {
        if (paramString == null){
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = null;
        ZipOutputStream zipOutputStream = null;
        byte[] arrayOfByte;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
            zipOutputStream.putNextEntry(new ZipEntry("0"));
            zipOutputStream.write(paramString.getBytes());
            zipOutputStream.closeEntry();
            arrayOfByte = byteArrayOutputStream.toByteArray();
        } catch (IOException localIOException5) {
            arrayOfByte = null;
        } finally {
            if (zipOutputStream != null){
                try {
                    zipOutputStream.close();
                } catch (IOException localIOException6) {
                }
            }
            if (byteArrayOutputStream != null){
                try {
                    byteArrayOutputStream.close();
                } catch (IOException localIOException7) {
                }
            }
        }
        return arrayOfByte;
    }

    @SuppressWarnings("unused")
    public static final String decompress(byte[] paramArrayOfByte) {
        if (paramArrayOfByte == null){return null;}
        ByteArrayOutputStream byteArrayOutputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ZipInputStream zipInputStream = null;
        String str;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
            zipInputStream = new ZipInputStream(byteArrayInputStream);
            ZipEntry localZipEntry = zipInputStream.getNextEntry();
            byte[] arrayOfByte = new byte[1024];
            int i = -1;
            while ((i = zipInputStream.read(arrayOfByte)) != -1){
                byteArrayOutputStream.write(arrayOfByte, 0, i);
            }
            str = byteArrayOutputStream.toString();
        } catch (IOException localIOException7) {
            str = null;
        } finally {
            if (zipInputStream != null){
                try {
                    zipInputStream.close();
                } catch (IOException localIOException8) {
                }
            }
            if (byteArrayInputStream != null){
                try {
                    byteArrayInputStream.close();
                } catch (IOException localIOException9) {
                }
            }
            if (byteArrayOutputStream != null){
                try {
                    byteArrayOutputStream.close();
                } catch (IOException localIOException10) {
                }
            }
        }
        return str;
    }

    public static void main(String[] args) {
        byte[] compress = StringCompress.compress("image://image/jpeg;1583718019baaaeed24db3a3d4e45cfea98762dc5fnoxdx3");
        System.out.println("gzip:"+new String(compress));
        String gunzip = StringCompress.decompress(compress);
        System.out.println("gunzip:"+gunzip);
    }
}
