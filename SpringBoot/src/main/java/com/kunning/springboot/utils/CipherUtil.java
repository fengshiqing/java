package com.kunning.springboot.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * 功能简述：密码工具类。
 * MD5摘要、Base64编码。
 */
public class CipherUtil {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CipherUtil.class);

    /**
     * 功能描述：获取 MD5 摘要。
     *
     * @param plaintext 明文
     * @param salt      盐值
     *
     * @return MD5摘要
     */
    public static String getMd5Digest(String plaintext, String salt) {
        // plaintext可以为空字符串""，空字符串""的 MD5摘要 是：d41d8cd98f00b204e9800998ecf8427e
        if (plaintext == null) {
            LOGGER.error("【plaintext不能为空！】");
            return null;
        }
        byte[] byteArr = StringUtils.isEmpty(salt) ? plaintext.getBytes() : (plaintext + salt).getBytes();
        return DigestUtils.md5DigestAsHex(byteArr);
    }

    public static String encrypt(String dataStr, String slat) {
        try {
            dataStr = dataStr + slat;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte byteArr[] = m.digest();
            String result = "";
            for (int i = 0; i < byteArr.length; i++) {
                result += Integer.toHexString((0x000000FF & byteArr[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    // ======================================================分割线======================================================

    /**
     * 功能描述：base64编码
     *
     * @param plaintext 明文
     */
    public void base64(String plaintext) throws IOException {

        // 参考：https://www.cnblogs.com/alter888/p/9140732.html

        // 1、早期在Java上做Base64的编码与解码，会使用到JDK里sun.misc套件下的BASE64Encoder和BASE64Decoder两个类，用法如下：
        String text = "字串文字";
        //编码
        String encodedText = new BASE64Encoder().encode(text.getBytes());
        System.out.println("【base64编码后的值：】" + encodedText);
        //解码
        System.out.println("【base64解码后的值：】" + new String(new BASE64Decoder().decodeBuffer(encodedText)));

        // 从以上代码可以发现，在Java用Base64一点都不难，几行代码就解决了！
        // 只是这个 sun.misc 套件所提供的Base64功能，编码和解码的效率不太好，而且在以后的Java版本可能就不被支持了，所以完全不建议使用。

        // 2、Apache.Commons。Codec有提供Base64的编码与解码功能，会使用到org.apache.commons.codec.binary套件下的Base64类别，用法如下：
        final Base64 base64 = new Base64();
        final byte[] textByte = text.getBytes("UTF-8");
        //编码
        final String encodedText2 = base64.encodeToString(textByte);
        System.out.println("【base64编码后的值：】" + encodedText2);
        //解码
        System.out.println("【base64解码后的值：】" + new String(base64.decode(encodedText2), "UTF-8"));

        // 以上的代码看起来又比早期用sun.mis c套件还要更精简，效能实际执行起来也快了不少。
        // 缺点是需要引用 Apache.Commons.Codec，相对有点麻烦

        // 3、Java 8的java.util套件中，新增了Base64的类别，可以用来处理Base64的编码与解码，用法如下：
        java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
        java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();

        //编码
        final String encodedText3 = encoder.encodeToString(text.getBytes("UTF-8"));
        System.out.println(encodedText3);
        //解码
        System.out.println(new String(decoder.decode(encodedText3), "UTF-8"));

        // 与sun.misc套件和Apache Commons Codec所提供的Base64编解码器来比较的话，Java 8提供的Base64拥有更好的效能。
        // 实际测试编码与解码速度的话，Java 8提供的Base64，要比sun.mis c套件提供的还要快至少11倍，比Apache Commons Codec提供的还要快至少3倍。
        // 因此在Java上若要使用Base64，这个Java 8底下的java .util套件所提供的Base64类别绝对是首选！

        // 4、现在spring封装了 base64 ，用这个
        // spring-core-5.2.0.RELEASE.jar!   org.springframework.util.Base64Utils.class
    }

    // ======================================================分割线======================================================

    /**
     * 功能描述：AES加密后，再进行base64编码。
     *
     * @param plaintext 明文
     * @param secretKey 密钥
     * @param iv        向量
     *
     * @return 加密后的字符串
     *
     * @throws Exception 异常
     */
    public static String Encrypt(String plaintext, String secretKey, String iv) throws Exception {

        // 参考：https://blog.csdn.net/TangHao_0226/article/details/80264572

        if (secretKey == null) {
            LOGGER.error("【Key不能为null】");
            return null;
        }
        // 判断Key是否为16位
        if (secretKey.length() != 16) {
            LOGGER.error("【Key长度必须是16位！】");
            return null;
        }

        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // 算法/模式/补码方式
        IvParameterSpec ivParameterSpec = new IvParameterSpec("0102030405060708".getBytes()); // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] encrypted = cipher.doFinal(plaintext.getBytes()); // 加密

        return Base64Utils.encodeToString(encrypted); // 此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    /**
     * 功能描述：AES解密
     *
     * @param ciphertext 密文
     * @param secretKey  密钥
     * @param iv         向量
     *
     * @return 解密后的字符串
     */
    public static String Decrypt(String ciphertext, String secretKey, String iv) {
        try {
            // 判断Key是否正确
            if (secretKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (secretKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }

            byte[] encrypted = Base64Utils.decodeFromString(ciphertext); // 先用base64解码
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParameterSpec = new IvParameterSpec("0102030405060708".getBytes());
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            byte[] original = cipher.doFinal(encrypted); // 解密
            return new String(original);
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

}
