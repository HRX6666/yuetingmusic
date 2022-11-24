package com.example.app;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class SHA256 {
    public static String md5(String content){
        byte[]hash;
        try{
            hash=MessageDigest.getInstance("SHA-256").digest(content.getBytes());
            StringBuilder hex=new StringBuilder(hash.length*2);
            for (byte b:hash){
                int number=b&0xFF;
                if(number<0x10){
                    hex.append("0");
                }
                hex.append(Integer.toHexString(b & 0xFF));
            }
            return hex.toString();
        }
        catch (NoSuchAlgorithmException e){
            throw new RuntimeException("NoSuchAlgorithmExcepation",e);
        }
    }
}
