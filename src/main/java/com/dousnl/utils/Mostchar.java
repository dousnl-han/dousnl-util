//package com.dousnl.utils;
//
//import com.google.common.base.Strings;
//import com.google.common.collect.Lists;
//import org.apache.commons.lang3.StringUtils;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
///**
// * Description: TODO
// * Company    : 上海黄豆网络科技有限公司
// *
// * @author : hanliangliang
// * Date       : 2021/10/27
// * Modify     : 修改日期          修改人员        修改说明          JIRA编号
// * v1.0.0       2021/10/27       hanliangliang     新增              1001
// ********************************************************************/
//public class Mostchar {
//    public static void main(String[] args){
//        long start=System.currentTimeMillis();
//
//        System.out.println(convertToSimplifiedChinese("名著"));
//        String key = "世界经典名著";
//        System.out.println(convertToTraditionalChinese("名着"));
//        long start1=System.currentTimeMillis();
//        boolean flag = checkString(key);
//        System.out.println((System.currentTimeMillis()-start1)+"ms1");
//        System.out.println(flag);
//        ArrayList<String> strings = Lists.newArrayList( "世界");
//
//        long start2=System.currentTimeMillis();
//        boolean s = strings.stream().map(e -> key.contains(e)).findAny().orElse(false);
//        System.out.println((System.currentTimeMillis()-start2)+"ms2");
//        System.out.println("s:"+s);
//        System.out.println(s);
//
//
//        BigDecimal divide = BigDecimal.valueOf(2388).divide(BigDecimal.valueOf(100),0, RoundingMode.FLOOR);
//
//        BigDecimal subtract = divide.subtract(BigDecimal.valueOf(1));
//        System.out.println(divide);
//        System.out.println(subtract);
//
//        subtract=sub(subtract);
//
//        System.out.println(subtract);
//
//    }
//
//    private static BigDecimal sub(BigDecimal subtract) {
//        subtract=subtract.add(BigDecimal.TEN);
//        return subtract;
//    }
//
//    private static boolean checkString(String key) {
//        ArrayList<String> strings = Lists.newArrayList( "世界");
//        for (String s : strings){
//            if (key.contains(s)){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 繁体转换为简体
//     * @param pinYinSt 要转换的字符串
//     * @return
//     */
//    public static String convertToSimplifiedChinese(String pinYinSt) {
//        String tempStr = null;
//        try {
//            tempStr = ChineseHelper.convertToSimplifiedChinese(pinYinSt);
//        } catch (Exception e) {
//            tempStr = pinYinSt;
//            e.printStackTrace();
//        }
//
//        return tempStr;
//    }
//
//    /**
//     * 简体转换为繁体
//     * @param pinYinStr 要转换的字符串
//     * @return
//     */
//    public static String convertToTraditionalChinese(String pinYinStr) {
//        String tempStr = null;
//        try {
//            tempStr = ChineseHelper.convertToTraditionalChinese(pinYinStr);
//        } catch (Exception e) {
//            tempStr = pinYinStr;
//            e.printStackTrace();
//        }
//        return tempStr;
//    }
//
//
//
//    public static Map<Character,Integer>  mapFun (char[] chars){
//        Map<Character, Integer> map = new HashMap<Character, Integer>();
//        if (chars != null&& chars.length!=0){
//            for (int i=0;i<chars.length;i++){
//                if (null!=map.get(chars[i])){
//                    map.put(chars[i],map.get(chars[i])+1);
//                }else {
//                    map.put(chars[i],1);
//                }
//            }
//        }
//        return map;
//    }
//
//}
