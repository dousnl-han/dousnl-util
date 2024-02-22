package com.dousnl.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Description:
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hll
 * Date       : 2024/1/16
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2024/1/16       hll    新增              1001
 ********************************************************************/
public class IPutils {

    /**
     *
     * @param content
     * @param encodingString
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getAddresses(String content, String encodingString)
            throws UnsupportedEncodingException {
        // 这里调用接口
        String urlStr = "http://whois.pconline.com.cn/ip.jsp";
        // 从http://whois.pconline.com.cn取得IP所在的省市区信息
        String returnStr = this.getResult(urlStr, content, encodingString);
        if (returnStr != null) {
            // 处理返回的省市区信息
            String[] temp = returnStr.split(" ");
            if(temp.length<2){
                return "0";//无效IP
            }
            String region = temp[0];
            if(StringUtil.isBlank(region) || region.trim().equals("")){
                region = temp[1];
            }
            return region;
        }
        return "未知";
    }

    /**
     * @param urlStr
     * @param content
     * @param encoding
     * @return
     */
    private String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("POST");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());// 打开输出流往对端服务器写数据
            out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
            out.flush();// 刷新
            out.close();// 关闭输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }

    /**
     *
     * @param ip
     * @return
     */
    public static String getCity(String ip) {
        IPutils addressUtils = new IPutils();
        String address = "";
        try {
            address = addressUtils.getAddresses("ip="+ip, "gbk");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return address;
    }

    public static void main(String[] args) {
        System.out.println(getCity("221.180.45.170"));
    }
}
