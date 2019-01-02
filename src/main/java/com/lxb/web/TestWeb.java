package com.lxb.web;

import com.lxb.model.Family;
import com.lxb.rsa.Base64Utils;
import com.lxb.rsa.RSAUtils;
import com.lxb.service.FamilyService;
import com.lxb.service.TestService;
import com.lxb.util.DigestUtil;
import com.lxb.util.RSA;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.List;
import java.util.Map;

/**
 * @Auther: lxb
 * @Date: 2018/12/25 0025
 * @Description:
 */
@RestController
public class TestWeb {
    @Autowired
    private TestService testService;
    @Autowired
    private FamilyService familyService;
    @GetMapping("test")
    public void  test(){
        testService.getList();
    }
    @GetMapping("msg")
    public List<Family> getMsg(){
        return familyService.toId(1);
    }
    @GetMapping("/token")
    public  String test2() throws Exception {
        Map<String, Object> keyMap = RSA.initKey();
        //公钥
        byte[] publicKey = RSA.getPublicKey(keyMap);

        //私钥
        byte[] privateKey = RSA.getPrivateKey(keyMap);


        System.out.println("================密钥对构造完毕,甲方将公钥公布给乙方，开始进行加密数据的传输=============");
        String str = "RSA密码交换算法";
        System.out.println("/n===========甲方向乙方发送加密数据==============");
        System.out.println("原文:" + str);
        //甲方进行数据的加密
        byte[] code1 = RSA.encryptByPrivateKey(str.getBytes(), privateKey);
        //  System.out.println("===========base64之前=============="+DigestUtil.decode(new String(code1)));
        System.out.println("加密后的数据：" + DigestUtil.encryptBase64(code1));
        String code64=Base64.encodeBase64String(code1);
        System.out.println("===========乙方使用甲方提供的公钥对数据进行解密==============");
        System.out.println("===========base64解密=============="+DigestUtil.decode(code64));
        //乙方进行数据的解密
        //
       // byte[] decode1 = RSA.decryptByPublicKey(DigestUtil.decryptBase64(code64), publicKey);
        byte[] decode1 = RSA.decryptByPublicKey(DigestUtil.decryptBase64("PMP2Go7yGA0YdtQGusVBDBs9oZe2NZz4WwYPlzwQyEFCjfHbaoq28y6DHx8LPtI6BFJpEld8oWb3ji/w/h8X/Q=="), publicKey);
        System.out.println("乙方解密后的数据：" + new String(decode1) + "/n/n");
        return  null;
    }
    //加密
    @GetMapping("/test0")
    public  String test0(String msg) throws Exception {

        //甲方进行数据的加密
        byte[] code1 = RSA.encryptByPrivateKey(msg.getBytes(), RSA.privateKey);
        //签名
        String sign = RSAUtils.sign2(code1, RSA.privateKey);
        System.out.println("sign:"+sign);
        return Base64.encodeBase64String(code1);
    }
    //解密
    @GetMapping("/test1")
public String test1(String data)throws  Exception {
        Map<String, Object> keyMap = RSA.initKey();
        //公钥
        byte[] publicKey = RSA.getPublicKey(keyMap);
        data = data.replaceAll(" ", "+");
        //私钥
        byte[] privateKey = RSA.getPrivateKey(keyMap);
        byte[] decode1 = RSA.decryptByPublicKey(DigestUtil.decryptBase64(data), RSA.publicKey);
       // boolean status = RSAUtils.verify2(decode1, RSA.publicKey, sign);
       // System.out.println(status);
        return new String(decode1);
    }
}
