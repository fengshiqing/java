package com.kunning.commons.utils;

import java.security.MessageDigest;

public class CryptographUtil {

    // ======================================================MD5======================================================

    /**
     * 功能描述：MD5（Message Digest Algorithm 5）。
     * MD5 是用来验证文件的一致性的。
     *
     * @author 冯仕清
     */
    public static void main(String[] args) {
        System.out.println(getMD5("tree"));
    }

    // https://blog.csdn.net/simonchi/article/details/49329097
    public static String getMD5(String source) {
        String resultStr = null;
        char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("MD5");
            msgDigest.update(source.getBytes());// 使用指定的byte数组更新摘要
            byte[] hashCalc = msgDigest.digest();// 完成哈希计算
            char[] result = new char[16 * 2];// MD5结果返回的是32位字符串，每位是16进制表示的
            int k = 0;
            for (int i = 0; i < 16; i++) {// 循环16次，对每个字节进行操作转换
                byte everyByte = hashCalc[i];
                result[k++] = hexChar[everyByte >>> 4 & 0xf];// 对每个字节的高4位进行处理，逻辑右移，再相与
                result[k++] = hexChar[everyByte & 0xf];// 低4位转换
            }
            resultStr = new String(result);
        } catch (Exception e) {
            return null;
        }
        return resultStr;
    }


    /**
     * 十六进制数字组成的数组
     */
    private static final String[] hexDigIts = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 功能描述：MD5加密
     *
     * @param origin      字符
     * @param charsetname 编码
     *
     * @return
     */
    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (null == charsetname || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        } catch (Exception e) {
            System.out.println("【异常】" + e);
        }
        return resultString;
    }


    public static String byteArrayToHexString(byte[] byteArr) {
        StringBuilder resultSb = new StringBuilder();
        for (int i = 0; i < byteArr.length; i++) {
            resultSb.append(byteToHexString(byteArr[i]));
        }
        return resultSb.toString();
    }

    public static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigIts[d1] + hexDigIts[d2];
    }


}
