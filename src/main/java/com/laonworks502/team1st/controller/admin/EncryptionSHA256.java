package com.laonworks502.team1st.controller.admin;


import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Slf4j

public class EncryptionSHA256 {


    public static String getEncrypt_admin(String source, String salt) {
        return getEncrypt_admin(source, salt.getBytes());
    }

    public static String getEncrypt_admin(String source, byte[] salt) {

        String result = "";

        byte[] a = source.getBytes();
        byte[] bytes = new byte[a.length + salt.length];

        System.arraycopy(a, 0, bytes, 0, a.length);
        System.arraycopy(salt, 0, bytes, a.length, salt.length);

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes);

            byte[] byteData = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
            }

            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String generateSalt() {
        Random random = new Random();

        byte[] salt = new byte[8];
        random.nextBytes(salt);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < salt.length; i++) {
            // byte 값을 Hex 값으로 바꾸기.
            sb.append(String.format("%02x",salt[i]));       // 2진수(byte)들을 16진수(Hex) 2자리로 변환해서 압축
        }

        return sb.toString();
    }

}