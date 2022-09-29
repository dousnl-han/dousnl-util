package com.dousnl.utils.file;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2022/7/5
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2022/7/5       hanliangliang     新增              1001
 ********************************************************************/
public class TxtUtil {

    /**

     * 读取txt文件的内容

     * @param file 想要读取的文件对象

     * @return 返回文件内容

     */

    public static String txt2String(File file){

        StringBuilder result = new StringBuilder();

        try{

            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件

            String s = null;

            while((s = br.readLine())!=null){//使用readLine方法，一次读一行

                result.append(System.lineSeparator()+s);

            }

            br.close();

        }catch(Exception e){

            e.printStackTrace();

        }

        return result.toString();

    }

    /**
     * 使用gzip压缩字符串
     */
    public static String compress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new sun.misc.BASE64Encoder().encode(out.toByteArray());
    }

    /**
     * 使用gzip解压缩
     */
    public static String uncompress(String compressedStr) {
        if (compressedStr == null || compressedStr.length() == 0) {
            return compressedStr;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed = null;
        String decompressed = null;
        try {
            compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ginzip != null) {
                try {
                    ginzip.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return decompressed;
    }

    public static void main(String[] args){

        File file = new File("D:/1.txt");

        System.out.println(txt2String(file));

    }

}

