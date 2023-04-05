package com.taritari.blog.service;

import com.taritari.blog.entity.dto.UserTokenDto;
import com.taritari.blog.utils.JwtUtils;
import com.taritari.blog.utils.RSAUtil;

import java.util.Base64;

/**
 * @author taritari
 * @date 2023-04-05 13:37
 * @description
 */
public class EncryptionAndDecryptionService {
    /**
     * 解密token
     * @param token 待解密的token
     * @return UserTokenDto 解密后的用户token实体
     */
    public UserTokenDto decode(String token){
        JwtUtils jwtUtil = new JwtUtils();
        UserTokenDto userTokenDto = null;
        try {
            userTokenDto = jwtUtil.JwtDecoder(token);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return userTokenDto;
    }
    /**
     * 解密rsa
     * @param data 待解密的数据
     * @return String 解密后的字符串
     */
    public String decodeRsa(String data){
        String decrData=null;
        try {
            byte[] decryptedText  =RSAUtil.decrypt(Base64.getUrlDecoder().decode(data));
            decrData = new String(decryptedText);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return decrData;
    }
    /**
     * 通过token解密出rsa获取username
     * @param token
     * @return username
     * */
    public String getUserName(String token){
        UserTokenDto decode = decode(token);
        String rsa = decode.getUsername();
        String username = decodeRsa(rsa);
        return username;
    }
}