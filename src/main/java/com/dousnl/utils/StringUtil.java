package com.dousnl.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2022/7/20
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2022/7/20       hanliangliang     新增              1001
 ********************************************************************/
public class StringUtil {
    /**
     * 将字符串用空格、逗号、句号、分号分割为字符串数组<br />
     * <i>分割后的每个元素都会被去掉前后空格</i>
     *
     * @param str 要分割的字符串
     * @return 分割后的字符串数组。<br />
     *         <i>如果 str 参数为 null，返回 null，如果分割后为空，返回空。</i>
     */
    public static final String[] splitToArray(String str) {
        if (str == null) {
            return new String[] {};
        }
        return StringUtils.split(str, ",;. -");
    }

    /**
     * 将字符串用指定分隔符分割 <br />
     * <i>分割后的每个元素都会被去掉前后空格</i>
     *
     * @param str 要分割的字符串
     * @param separators 分割符号，可以指定多个，连着写就行，多个空格被认为是一个
     * @return 分割后的字符串数组。<br />
     *         <i>如果 str 参数为 null，返回 null，如果分割后为空，返回空。</i><br />
     *         <i>如果 separators 参数为 null，将不分割</i>
     */
    public static final String[] splitToArray(String str, String separators) {
        if (str == null) {
            return new String[] {};
        }
        if (separators == null) {
            separators = "";
        }
        return StringUtils.split(str, separators);
    }

    /**
     * 将字符串用空格、逗号、句号、分号分割为字符串数组<br />
     * <i>分割后的每个元素都会被去掉前后空格</i>
     *
     * @param str 要分割的字符串
     * @return 分割后的字符串数组。<br />
     *         <i>如果 str 参数为 null，返回 null，如果分割后为空，返回空。</i>
     */
    public static final List<String> splitToList(String str) {
        if (str == null) {
            return new ArrayList<String>();
        }
        return Arrays.asList(StringUtils.split(str, ",;. "));
    }

    /**
     * 将字符串用指定分隔符分割<br />
     * <i>分割后的每个元素都会被去掉前后空格</i>
     *
     * @param str 要分割的字符串
     * @param separators 分割符号，可以指定多个，连着写就行，多个空格被认为是一个
     * @return 分割后的字符串数组。<br />
     *         <i>如果 str 参数为 null，返回 null，如果分割后为空，返回空。</i><br />
     *         <i>如果 separators 参数为 null，将不分割</i>
     */
    public static final List<String> splitToList(String str, String separators) {
        if (str == null) {
            return new ArrayList<String>();
        }
        if (separators == null) {
            return new ArrayList<String>();
        }
        return Arrays.asList(StringUtils.split(str, separators));
    }

    /**
     * 将字符串左右去空格
     *
     * @param str 要去空格的字符串，如果为空，返回空
     * @return 去两边空格后的字符串<br />
     *         <i>如果参数为 null，返回 null</i>
     */
    public static final String trim(String str) {
        if (str == null) {
            return null;
        }
        return str.trim();
    }

    /**
     * 将字符串左右去空格
     *
     * @param str 要去空格的字符串
     * @return 去掉两边空格后的字符串<br />
     *         <i>如果参数为 null 或去空格后为空，均返回空</i>
     */
    public static final String trimToEmpty(Object str) {
        if (str == null) {
            return "";
        }
        return StringUtils.trimToEmpty(str.toString());
    }

    /**
     * 判断一个字符串是否由某些字符串开头
     *
     * @param str 要判断的字符串
     * @param prefix 开头字符串，可指定多个
     * @return 如果是以 prefix 开头，返回 true。如果为 null，返回 false
     */
    public static final boolean startsWith(String str, String... prefix) {
        if (prefix == null) {
            return false;
        }
        return StringUtils.startsWithAny(str, prefix);
    }

    /**
     * 判断一个字符串是否由某些字符串结尾
     *
     * @param str 要判断的字符串
     * @param suffix 结尾字符串，可指定多个
     * @return 如果是以 prefix 开头，返回 true
     */
    public static final boolean endsWith(String str, String... suffix) {
        return StringUtils.endsWithAny(str, suffix);
    }

    /**
     * 将字符串中出现的指定内容全部替换成其他内容
     *
     * @param str 原始字符串
     * @param searchStr 要被替换的字符串
     * @param replacement 替换成什么
     * @return 替换后的新字符串<br />
     *         <i>如果原始字符串为 null，返回 null</i><br />
     *         <i>如果要被替换的字符串为 null，将不替换</i><br />
     *         <i>如果要替换成什么的字符串为 null，将删除被替换的字符串</i>
     */
    public static final String replaceAll(String str, String searchStr, String replacement) {
        if (str == null) {
            return null;
        }
        if (searchStr == null) {
            return str;
        }
        if (replacement == null) {
            replacement = "";
        }

        return str.replaceAll(Pattern.quote(searchStr), replacement);
    }

    /**
     * 将字符串中第一个出现的指定内容替换成其他内容
     *
     * @param str 原始字符串
     * @param searchStr 要被替换的字符串
     * @param replacement 替换成什么
     * @return 替换后的新字符串<br />
     *         <i>如果原始字符串为 null，返回 null</i><br />
     *         <i>如果要被替换的字符串为 null，将不替换</i><br />
     *         <i>如果要替换成什么的字符串为 null，将删除被替换的字符串</i>
     */
    public static final String replaceFirst(String str, String searchStr, String replacement) {
        if (str == null) {
            return null;
        }
        if (searchStr == null) {
            return str;
        }
        if (replacement == null) {
            replacement = "";
        }

        return jodd.util.StringUtil.replaceFirst(str, searchStr, replacement);
    }

    /**
     * 将字符串中最后出现的指定内容替换成其他内容
     *
     * @param str 原始字符串
     * @param searchStr 要被替换的字符串
     * @param replacement 替换成什么
     * @return 替换后的新字符串<br />
     *         <i>如果原始字符串为 null，返回 null</i><br />
     *         <i>如果要被替换的字符串为 null，将不替换</i><br />
     *         <i>如果要替换成什么的字符串为 null，将删除被替换的字符串</i>
     */
    public static final String replaceLast(String str, String searchStr, String replacement) {
        if (str == null) {
            return null;
        }
        if (searchStr == null) {
            return str;
        }
        if (replacement == null) {
            replacement = "";
        }

        return jodd.util.StringUtil.replaceLast(str, searchStr, replacement);
    }

    /**
     * 判断字符串中是否出现过指定的字符串
     *
     * @param str 原始字符串
     * @param searchStrs 是否出现过的字符串，可指定多个
     * @return 是否出现过该字符串<br />
     *         <i>如果指定多个字符串，只要有一个出现过，就返回 true</i>
     */
    public static boolean contains(String str, String... searchStrs) {
        if (str == null) {
            return false;
        }
        if (searchStrs == null || searchStrs.length == 0) {
            return false;
        }

        return StringUtils.containsAny(str, searchStrs);
    }

    /**
     * 判断字符串是否为 null 或是否为空
     *
     * @param str 要判断的字符串
     * @return 如果为 null 或为空，返回 true
     */
    public static final boolean isNullOrEmpty(Object str) {
        return str == null || StringUtils.isEmpty(str.toString());
    }

    /**
     * 判断字符串是否不为 null 且不不空
     *
     * @param str 要判断的字符串
     * @return 如果不为 null 且不为空，返回 true
     */
    public static final boolean isNotEmpty(Object str) {
        return str != null && StringUtils.isNotEmpty(str.toString());
    }

    /**
     * 判断字符串是否为 null 或去除两边空格后是否为空
     *
     * @param str 要判断的字符串
     * @return 如果为 null 或去两边空格后为空，返回 true
     */
    public static final boolean isBlank(Object str) {
        return str == null || StringUtils.isBlank(str.toString());
    }

    /**
     * 判断两个字符串是否相等
     *
     * @param a 字符串
     * @param b 字符串
     * @return 如果两个字符串内容相等，返回 true<br />
     *         <i>都是 null，返回 true。</i><br />
     *         <i>一个为空一个为 null，返回 false</i>
     */
    public static final boolean isEquals(String a, String b) {
        return StringUtils.equals(a, b);
    }

    /**
     * 判断左边的字符串是否等于给定的任意一个字符串
     *
     * @param a 字符串
     * @param bs 字符串，可指定多个
     * @return true，如果匹配任意一个
     */
    public static final boolean isEqualsEvenOnce(String a, String... bs) {
        for (String b : bs) {
            if (StringUtil.isEquals(a, b)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断两个字符串是否相等，不区分大小写
     *
     * @param a 字符串
     * @param b 字符串
     * @return 如果两个字符串内容相等，返回 true<br />
     *         <i>都是 null，返回 true。</i><br />
     *         <i>一个为空一个为 null，返回 false</i>
     */
    public static final boolean isEqualsIgnoreCase(String a, String b) {
        return StringUtils.equalsIgnoreCase(a, b);
    }

    /**
     * 将字符串中的全部字符转换为小写
     *
     * @param str 要处理的字符串
     * @return 全部转换为小写的字符串
     */
    public static final String toLowerCase(String str) {
        if (str == null) {
            return null;
        }

        return StringUtils.lowerCase(str);
    }

    /**
     * 将字符串中的全部字符转换为大写
     *
     * @param str 要处理的字符串
     * @return 全部转换为大写的字符串
     */
    public static final String toUpperCase(String str) {
        if (str == null) {
            return null;
        }

        return StringUtils.upperCase(str);
    }

    /**
     * 将字符串中首个字母转换为大写
     *
     * @param str 要处理的字符串
     * @return 首字母大写的字符串
     */
    public static final String firstToUpperCase(String str) {
        if (str == null) {
            return null;
        }

        return StringUtils.capitalize(str);
    }

    /**
     * 将字符串中首个字母转换为小写
     *
     * @param str 要处理的字符串
     * @return 首字母小写的字符串
     */
    public static final String firstToLowerCase(String str) {
        if (str == null) {
            return null;
        }

        return StringUtils.uncapitalize(str);
    }

    /**
     * 判断字符串中首次出现指定字符串中的位置
     *
     * @param str 原始字符串
     * @param searchStr 要找位置的字符串
     * @return 字符串的位置，从 0 开始<br />
     *         <i>如果找不到指定字符串，返回 -1</i><br />
     *         <i>如果两个参数中某个为 null，返回 -1</i>
     */
    public static final int firstIndexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return -1;
        }

        return StringUtils.indexOf(str, searchStr);
    }

    /**
     * 判断字符串中首次出现指定字符串中的位置，不区分大小写
     *
     * @param str 原始字符串
     * @param searchStr 要找位置的字符串
     * @return 字符串的位置，从 0 开始<br />
     *         <i>如果找不到指定字符串，返回 -1</i><br />
     *         <i>如果两个参数中某个为 null，返回 -1</i>
     */
    public static final int firstIndexOfIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return -1;
        }
        return StringUtils.indexOfIgnoreCase(str, searchStr);
    }

    /**
     * 判断字符串中最后一次出现指定字符串中的位置
     *
     * @param str 原始字符串
     * @param searchStr 要找位置的字符串
     * @return 字符串的位置，从 0 开始<br />
     *         <i>如果找不到指定字符串，返回 -1</i><br />
     *         <i>如果两个参数中某个为 null，返回 -1</i>
     */
    public static final int lastIndexOf(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return -1;
        }

        return StringUtils.lastIndexOf(str, searchStr);
    }

    /**
     * 判断字符串中最后一次出现指定字符串中的位置，不区分大小写
     *
     * @param str 原始字符串
     * @param searchStr 要找位置的字符串
     * @return 字符串的位置，从 0 开始<br />
     *         <i>如果找不到指定字符串，返回 -1</i><br />
     *         <i>如果两个参数中某个为 null，返回 -1</i>
     */
    public static final int lastIndexOfIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return -1;
        }
        return StringUtils.lastIndexOfIgnoreCase(str, searchStr);
    }

    /**
     * 在字符串中截取第一次出现指定字符串之前的内容
     *
     * @param str 原始字符串
     * @param separator 截止的字符串
     * @return 截取后的字符串
     */
    public static final String substringBeforeFirst(String str, String separator) {
        if (str == null | separator == null) {
            return null;
        }

        return StringUtils.substringBefore(str, separator);
    }

    /**
     * 在字符串中截取第一次出现指定字符串之后的内容
     *
     * @param str 原始字符串
     * @param separator 截止的字符串
     * @return 截取后的字符串
     */
    public static final String substringAfterFirst(String str, String separator) {
        if (str == null | separator == null) {
            return null;
        }

        return StringUtils.substringAfter(str, separator);
    }

    /**
     * 在字符串中截取最后一次出现指定字符串之前的内容
     *
     * @param str 原始字符串
     * @param separator 起始的字符串
     * @return 截取后的字符串
     */
    public static final String substringBeforeLast(String str, String separator) {
        if (str == null | separator == null) {
            return null;
        }

        return StringUtils.substringBeforeLast(str, separator);
    }

    /**
     * 在字符串中截取最后一次出现指定字符串之后的内容
     *
     * @param str 原始字符串
     * @param separator 起始的字符串
     * @return 截取后的字符串
     */
    public static final String substringAfterLast(String str, String separator) {
        if (str == null | separator == null) {
            return null;
        }

        return StringUtils.substringAfterLast(str, separator);
    }

    /**
     * 查找指定字符串出现了几次
     *
     * @param str 原始字符串
     * @param searchStr 要查找的字符串
     * @return 出现次数
     */
    public static final int countMatches(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return 0;
        }

        return StringUtils.countMatches(str, searchStr);
    }

    /**
     * 将字符串转换成字节数组<br />
     * <strong>这里用 UTF-8 编码</strong>
     *
     * @param str 字符串
     * @return 字节数组
     */
    public static final byte[] toBytes(String str) {
        return str.getBytes();
    }

    /**
     * 将字节数组转换成字符串<br />
     * <strong>这里用 UTF-8 编码</strong>
     *
     * @param bytes 字节数组
     * @return 字符串
     */
    public static final String toString(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return bytes.toString();
    }

    public static final String toString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }
    /**
     * 生成指定数量的指定字符
     *
     * @param c 要生成的字符
     * @param size 数量
     * @return 有指定数量的空格的字符串
     */
    public static String makeRepeat(char c, int size) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < size; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 获取字符串长度
     *
     * @param str 要获取长度的字符串
     * @return 字符串长度，如果是 null 返回 0
     */
    public static int getLength(String str) {
        if (str != null) {
            return str.length();
        }

        return 0;
    }

    static Pattern pattern = Pattern.compile("\\d(\\d{9})");

    public static String realNameTransition(String realName) {
        if (StringUtils.isBlank(realName)) {
            return null;
        }

        Matcher m = pattern.matcher(realName);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String group = m.group(1);
            m.appendReplacement(sb, m.group().replace(group, StringUtils.leftPad("", group.length(), "*")));
        }
        m.appendTail(sb);
        return sb.toString();

    }
}
