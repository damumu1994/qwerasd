package com.lxb.web;

import com.lxb.rsa.RSAUtils;
import com.lxb.util.DigestUtil;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RestController
public class Test2 {
    static String publicKey;
    static String privateKey;

    static {
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            publicKey = RSAUtils.getPublicKey(keyMap);
            privateKey = RSAUtils.getPrivateKey(keyMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/key")
    public  Map<String,String> key(String msg) throws Exception {
        Map<String,String> map=new HashMap<>();
        byte[] data = msg.getBytes();
        byte[] encodedData = RSAUtils.encryptByPrivateKey(data, privateKey);
        System.out.println("加密后：\r\n" + new String(encodedData));
        //base64
        String code64= Base64.encodeBase64String(encodedData);
        //签名
          String sign = RSAUtils.sign(encodedData, privateKey);
          map.put("token",code64);
          map.put("sign",sign);
        return map;

    }
    @GetMapping("/v")
    public  Map<String,String> v(String data) throws Exception {
        Map<String,String> map=new HashMap<>();
        data = data.replaceAll(" ", "+");
        ///解密
       //  byte[] decodedData = RSA.decryptByPublicKey(DigestUtil.decryptBase64(code64), publicKey);
        byte[] decodedData = RSAUtils.decryptByPublicKey(DigestUtil.decryptBase64(data), publicKey);
        String target = new String(decodedData);

            map.put("msg","ojbk");
            map.put("data",target);

        return map;
    }

}
