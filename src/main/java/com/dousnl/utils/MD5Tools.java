package com.dousnl.utils;


import com.dousnl.domain.User;
import org.apache.commons.lang3.StringUtils;

import java.security.MessageDigest;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/6/15
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/6/15       hanliangliang     新增              1001
 ********************************************************************/
public class MD5Tools {

    public MD5Tools() {
    }

    public static final String MD5(String pwd) {
        char[] md5String = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            byte[] btInput = pwd.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for(int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = md5String[byte0 >>> 4 & 15];
                str[k++] = md5String[byte0 & 15];
            }

            return new String(str);
        } catch (Exception var10) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(MD5("123456"));
        System.out.println(MD5("admin"));
        System.out.println(MD5("password"));
        User u=new User();
        String address = u.getAddress();
        if (StringUtils.isNotBlank(address) && !"null".equals(address)) {
            System.out.println(address);
        }
    }
}
