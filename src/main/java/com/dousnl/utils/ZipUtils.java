package com.dousnl.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
/**
 * 压缩工具类---会出现乱码
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/2 18:53
 */


public class ZipUtils {

    // 压缩
    public static String compress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        gzip.write(str.getBytes());
        gzip.close();
        return out.toString("ISO-8859-1");
    }

    // 解压缩
    public static String uncompress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(str
                .getBytes("ISO-8859-1"));
        GZIPInputStream gunzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n;
        while ((n = gunzip.read(buffer))>= 0) {
            out.write(buffer, 0, n);
        }
        // toString()使用平台默认编码，也可以显式的指定如toString(&quot;GBK&quot;)
        return out.toString();
    }

    public static void main(String[] args) throws IOException {
        String gzip = ZipUtils.compress("image://image/jpeg;1583718019baaaeed24db3a3d4e45cfea98762dc5fnoxdx3");
        System.out.println("gzip:"+gzip);
        String gunzip = ZipUtils.uncompress(gzip);
        System.out.println("gunzip:"+gunzip);

        //测试字符串
        String str="%5B%7B%22lastUpdateTime%22%3A%222011-10-28+9%3A39%3A41%22%2C%22smsList%22%3A%5B%7B%22liveState%22%3A%221";
        String compress = ZipUtils.compress(str);

        System.out.println("原长度："+str.length());

        System.out.println("压缩后："+compress);

        System.out.println("解压缩："+ZipUtils.uncompress(compress));
    }
}
