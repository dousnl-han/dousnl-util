package com.dousnl.utils;

import com.alibaba.fastjson.JSON;
import com.dousnl.domain.User;
import com.dousnl.domain.entity.AdvertImageDTO;
import com.dousnl.utils.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * TODO
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/5/22 15:27
 */
public class AEStest {
    private static RedisTemplate redisTemplate;

    public static void main(String[] args) throws Exception {
        Integer preview = 1;

        System.out.println(checkMasterOrderFee(3800,56));

        if (!Objects.equals(1,preview)){
            System.out.println(1121423);
        }
        Date currentDate = DateUtil.getCurrentDate();
        System.out.println(currentDate);
        System.out.println(new Date());
        User user1 = new User();
        if (Boolean.TRUE.equals(user1.getAt()) && user1.getDate()!=null &&user1.getDate().getTime()<System.currentTimeMillis()){
            System.out.println(1);
        }
        Date date = DateTime.now().millisOfDay().withMaximumValue().toDate();
        Date dateS = DateTime.now().millisOfDay().withMinimumValue().toDate();
        System.out.println("dates:"+DateUtil.dateToString(dateS));
        String name="str";

        caseName(name);
        String aa=very(name);

        System.out.println(aa);
        System.out.println("今天"+DateUtil.dateToString(date));
        // TODO Auto-generated method stub
        String str = "user=admin&pwd=admin";
        String key = "12345678";
        String encrytStr;
        byte[] encrytByte;

        byte[] byteRe = enCrypt(str,key);

        //加密过的二进制数组转化成16进制的字符串
        encrytStr = parseByte2HexStr(byteRe);
        System.out.println("加密后："+encrytStr);

        //加密过的16进制的字符串转化成二进制数组
        encrytByte = parseHexStr2Byte(encrytStr);
        System.out.println("解密后："+deCrypt(encrytByte,key));

        System.out.println("11111:"+IntegerEncryptTool.decrypt("dasdad"));

        System.out.println(IntegerEncryptTool.encrypt(400004272));
        boolean flag1=false;
        boolean flag2=true;
        boolean flag3 = flag1 || flag2;
        System.out.println(flag3);

        User user = new User();
        int size = 0;
        user.setOwn(true);
        user.setName("增长部");
        if (Boolean.TRUE.equals(user.getOwn())){
            size += 4;
        }
        if (Boolean.TRUE.equals(user.getOwn())){
            size += 2;
        }
        System.out.println(size);
        String deptName = String.format("(%s)", user.getName());
        System.out.println(deptName);
        String name1 = String.format("%d %s %s", 1, String.join("-", Arrays.asList("1","2")), deptName);
        System.out.println(name1);

        HashMap<Object, Object> objectHashMap = Maps.newHashMap();
        objectHashMap.put("xiaomi",1111);
        objectHashMap.put("huawei",2222);
        System.out.println(JSON.toJSONString(objectHashMap));

        Map map = JSON.parseObject("{'xiaomi':1111}", Map.class);
        System.out.println(map);

        System.out.println(checkAppRegisterSource(1));
    }

    protected static boolean checkAppRegisterSource(Integer registerSource) {
        return Arrays.asList(1, 2, 3).contains(registerSource);
    }

    private static Boolean checkMasterOrderFee(Integer masterTotalFee, Integer orderFee) {
        if (masterTotalFee == null || orderFee == null) {
            return false;
        }
        if (masterTotalFee.equals(orderFee)) {
            return true;
        }
        BigDecimal divide = BigDecimal.valueOf(masterTotalFee).divide(BigDecimal.valueOf(100));
        if (divide.compareTo(BigDecimal.valueOf(orderFee)) == 0) {
            return true;
        }
        return false;
    }

    private static void caseName(String name) {
        if (name.equals("str")){
            return;
        }
        System.out.println(name);
    }

    private static String very(String name) {
        if (name.equals("str1")) {
            name = "111";
        }
        return name;
    }

    /**
     * 加密函数
     * @param content   加密的内容
     * @param strKey    密钥
     * @return          返回二进制字符数组
     * @throws Exception
     */
    public static byte[] enCrypt(String content,String strKey) throws Exception{
        KeyGenerator keygen;
        SecretKey desKey;
        Cipher c;
        byte[] cByte;
        String str = content;

        keygen = KeyGenerator.getInstance("AES");
        keygen.init(128, new SecureRandom(strKey.getBytes()));

        desKey = keygen.generateKey();
        c = Cipher.getInstance("AES");

        c.init(Cipher.ENCRYPT_MODE, desKey);

        cByte = c.doFinal(str.getBytes("UTF-8"));

        return cByte;
    }

    /** 解密函数
     * @param src   加密过的二进制字符数组
     * @param strKey  密钥
     * @return
     * @throws Exception
     */
    public static String deCrypt (byte[] src,String strKey) throws Exception{
        KeyGenerator keygen;
        SecretKey desKey;
        Cipher c;
        byte[] cByte;

        keygen = KeyGenerator.getInstance("AES");
        keygen.init(128, new SecureRandom(strKey.getBytes()));

        desKey = keygen.generateKey();
        c = Cipher.getInstance("AES");

        c.init(Cipher.DECRYPT_MODE, desKey);


        cByte = c.doFinal(src);

        return new String(cByte,"UTF-8");
    }


    /**2进制转化成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }


    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
