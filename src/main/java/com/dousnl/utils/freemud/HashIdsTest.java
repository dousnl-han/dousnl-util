package com.dousnl.utils.freemud;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/6/2 10:18
 */
public class HashIdsTest {

    public static void main(String[] args) throws MalformedURLException {
        EncryptHelper helper = EncryptHelper.getInstance();
        String encrypt = helper.encrypt("34");
        System.out.println("encrypt:"+encrypt);
        String decrypt = helper.decrypt(encrypt);
        System.out.println("decrypt:"+decrypt);
        URL u=new URL("https://cdn-ali-images-test.dushu.io/1590988524b5edc6fd049035ddec98db1cb44d55b6t9ofp0");
        String host = u.getHost();
        System.out.println(host);
    }
}
