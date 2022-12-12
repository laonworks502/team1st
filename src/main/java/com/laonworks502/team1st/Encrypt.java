package com.laonworks502.team1st;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class Encrypt {

    public String getSalt() {
        // salt 생성
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];

        secureRandom.nextBytes(salt);

        // byte To String(10진수 문자열로 변환)
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : salt) {
            stringBuffer.append(String.format("%02x" , b));
        }

        return stringBuffer.toString();
    }

    public String getEncrypt(String passwd) {
        String result = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            messageDigest.update(passwd.getBytes());
            byte[] passwdSalt = messageDigest.digest();

            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : passwdSalt) {
                stringBuffer.append(String.format("%02x" , b));
            }

            result = stringBuffer.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            result = null;
        }

        return result;
    }
}
