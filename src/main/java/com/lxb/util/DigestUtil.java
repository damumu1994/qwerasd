/**
 * Copyright (c) @2016,cmct 版权所有
 */
package com.lxb.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * 加密工具类
 *
 * @author lxb
 * @Date 2018年12月15日
 * @since v0.1
 */
public class DigestUtil {
    private static final String KEY_SHA = "0933910847463829232312312";
    /**
     * 指定字符串生成md5摘要的hex表示
     *
     * @param data 字符串
     * @return String md5的hex
     */
    public static String md5Hex(String data) {
        return DigestUtils.md5Hex(data);
    }

    /**
     * 指定字符串md5加上salt生成的md5 hex
     *
     * @param data 原始字符串
     * @param salt salt值
     * @return String md5的hex
     */
    public static String md5HexWithSalt(String data, String salt) {
        return DigestUtils.md5Hex(data + salt);
    }

    /**
     * SHA 加密字符串
     *
     * @param data
     * @return
     */
    public static final String encryptSHA(String data) throws Exception {
        Base64 base64 = new Base64();
        String result = null;
        byte[] bytes = data.getBytes("UTF8");
        bytes = encryptSHA(bytes);
        if (bytes != null) {
            result = new String(base64.encode(bytes));
        }

        return result;
    }

    /**
     * SHA 解密字符串
     *
     * @param data
     * @return
     */
    public static final String decryptSHA(String data) throws Exception {
        Base64 base64 = new Base64();
        String result = null;

        byte[] bytes = data.getBytes("UTF8");
        bytes = base64.decode(bytes);
        bytes = decryptSHA(bytes);
        if (bytes != null) {
            result = new String(bytes);
        }
        return result;
    }

    /**
     * 生成密钥
     *
     * @return
     */
    private static SecretKeySpec generateKey() {
        SecretKeySpec key = null;
        try {
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            generator.init(128, new SecureRandom(KEY_SHA.getBytes()));
            SecretKey secretKey = generator.generateKey();
            key = new SecretKeySpec(secretKey.getEncoded(), "AES");
        } catch (Exception e) {
        }
        return key;
    }

    /**
     * SHA 加密算法
     *
     * @param bytes
     * @return
     */
    private static byte[] encryptSHA(byte[] bytes) throws Exception {
        byte[] result = null;
        Cipher cipher;
        if (bytes != null) {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, generateKey());
            result = cipher.doFinal(bytes);
        }
        return result;
    }

    /**
     * SHA 解密
     *
     * @param bytes
     * @return
     */
    private static byte[] decryptSHA(byte[] bytes) throws Exception {
        Cipher cipher;
        byte[] result = null;
        if (bytes != null) {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, generateKey());
            result = cipher.doFinal(bytes);
        }
        return result;
    }

    /**
     * Base64加密
     *
     * @param data
     * @return
     */
    public static final String encryptBase64(byte[] data) {
        Base64 encoder = new Base64();
        return encoder.encodeAsString(data);
    }

    /**
     * Base64解密
     *
     * @param data
     * @return
     */
    public static final byte[] decryptBase64(String data) {
        byte[] bytes = null;
        if (data == null) { // 图像数据为空
            return bytes;
        } else {
            Base64 decoder = new Base64();
            try {
                // Base64解码
                bytes = decoder.decode(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }
    /**
     * 将 BASE64 编码的字符串 s 进行解码
     *
     * @return String
     * @author lifq
     * @date 2015-3-4 上午09:24:26
     */
    public static String decode(String s) {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b,"GBK");
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 得到45字摘要
     *
     * @param str
     * @return
     */
    public static String getSummary(String str, int length) {
        if (str.length() < length) {
            //长度小于length
            return str;
        } else {
            //长度大于45 则只取40位 + ...
            return str.substring(0, length - 3) + "...";
        }
    }


}
