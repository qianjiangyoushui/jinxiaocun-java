package com.ecfund.base.util.common;






import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/16.
 */
public class DecryptDataUtil {
    private String appid;
    private String session_key;
    final Base64.Decoder decoder = Base64.getDecoder();
    final Base64.Encoder encoder = Base64.getEncoder();

    public DecryptDataUtil(String appid, String session_key) {
        this.appid = appid;
        this.session_key = session_key;
    }

    public String DecryptData(String encryptedData, String iv) {
        byte[] aesBytes = decoder.decode(session_key);
        byte[] ivBytes = decoder.decode(iv);
        try {
            IvParameterSpec paramSpec = new IvParameterSpec(ivBytes);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// 创建密码器
            SecretKeySpec key = new SecretKeySpec(aesBytes, "AES");
            cipher.init(Cipher.DECRYPT_MODE, key, paramSpec);// 初始化
            byte[] resultaaa = decoder.decode(encryptedData);
            return new String(cipher.doFinal(resultaaa)); // 解密
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public byte[] encryptData(String content, String password) {
        try {

            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] decrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

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

    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    /*public static void main(String[] args){
        DecryptDataUtil decryptData = new DecryptDataUtil("wx4f4bc4dec97d474b","tiihtNczf5v6AKRyjwEUhQ==");
        String data = "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZM" +
                "QmRzooG2xrDcvSnxIMXFufNstNGTyaGS" +
                "9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+" +
                "3hVbJSRgv+4lGOETKUQz6OYStslQ142d" +
                "NCuabNPGBzlooOmB231qMM85d2/fV6Ch" +
                "evvXvQP8Hkue1poOFtnEtpyxVLW1zAo6" +
                "/1Xx1COxFvrc2d7UL/lmHInNlxuacJXw" +
                "u0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn" +
                "/Hz7saL8xz+W//FRAUid1OksQaQx4CMs" +
                "8LOddcQhULW4ucetDf96JcR3g0gfRK4P" +
                "C7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB" +
                "6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns" +
                "/8wR2SiRS7MNACwTyrGvt9ts8p12PKFd" +
                "lqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYV" +
                "oKlaRv85IfVunYzO0IKXsyl7JCUjCpoG" +
                "20f0a04COwfneQAGGwd5oa+T8yO5hzuy" +
                "Db/XcxxmK01EpqOyuxINew==";
        try {
            String s = decryptData.DecryptData(data,"r7BXXKkLb8qrSNn05n0qiA==");
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    public static void main(String[] agrs) throws  Exception{
        String content = "{account:'18844038491',password:'11111111'}";
        String asB64 = AESUtils.encAESCode(content,"jingtum2017tudou");
        Map<String, String> params = new HashMap<String, String>();
        params.put("data", "[data=" + asB64 + "," + "deviceToken:dev00001,version:1.0]");
        params.put("account", "18844038491");
        params.put("password", "11111111");
        params.put("deviceToken", "dev00001");
        String[] result = HttpClientUtils.postFormData("user/login",
                content, "dev00001", "1.0");
        System.out.println(result);
    }
    public static byte[] encrypt2(String content, String password) {
        try {
            SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

