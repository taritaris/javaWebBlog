package com.taritari.blog.utils;


import com.taritari.blog.config.YmlConfig;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

/**
*SAUtil.java
*RSA加密解密工具类
*@Author: taritari
*@Date: 2023-04-02
*/

public class RSAUtil {
    private YmlConfig ymlConfig = new YmlConfig();
    private Map<String, Object> datasource = ymlConfig.getApplication("rsa");
    private  String publicKey = datasource.get("public").toString();
    private  String privateKey = datasource.get("private").toString();
    /**
     * 将Base64编码的公钥字符串解码成公钥对象
     * @return 公钥对象
     * @throws Exception
     */
    public PublicKey getPublicKey() throws Exception {

        byte[] keyBytes = Base64.getDecoder().decode(publicKey.getBytes());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(spec);
    }

    /**
     * 将Base64编码的私钥字符串解码成私钥对象
     * @return 私钥对象
     * @throws Exception
     */
    public PrivateKey getPrivateKey() throws Exception {

        byte[] keyBytes = Base64.getDecoder().decode(privateKey.getBytes());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(spec);
    }

    /**
     * 使用RSA公钥加密数据
     * @param data 待加密数据
     * @return 加密后的数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data) throws Exception {
        // 获取RSA加密对象
        Cipher cipher = Cipher.getInstance("RSA");
        // 初始化加密对象，设置加密模式和公钥
        cipher.init(Cipher.ENCRYPT_MODE,new RSAUtil().getPublicKey());
        // 加密数据
        return cipher.doFinal(data);
    }

    /**
     * 使用RSA私钥解密数据
     * @param data 待解密数据
     * @return 解密后的数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data) throws Exception {
        // 获取RSA解密对象
        Cipher cipher = Cipher.getInstance("RSA");
        // 初始化解密对象，设置解密模式和私钥
        cipher.init(Cipher.DECRYPT_MODE, new RSAUtil().getPrivateKey());
        // 解密数据
        return cipher.doFinal(data);
    }
}
