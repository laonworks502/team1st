package com.laonworks502.team1st.controller.users;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SHA256 {

	// Static변수는 메모리에 한번 할당되어 프로그램이 종료될 때 해제되는 변수로,
	// 메모리에 한번 할당되므로 여러 객체가 해당 메모리를 공유하게 됩니다.
    public static String getSalt() {
    	
    	// 1. Random, byte 객체 생성
    	SecureRandom sr = new SecureRandom();
    	byte[] salt = new byte[20];
    	
    	// 2. 난수 생성
    	sr.nextBytes(salt);
    	
    	// 3. byte to String (16진수의 문자열로 변경)
    	StringBuffer sb = new StringBuffer();
    	for(byte b : salt) {
    		sb.append(String.format("%02x", b));
    	};
    	
    	return sb.toString();
    }
    
    public static String getEncrypt(String pwd, String salt) throws NoSuchAlgorithmException{
    	
    	// 1. SHA-256 알고리즘 객체 생성
    	MessageDigest md = MessageDigest.getInstance("SHA-256");
    	
    	// 2. pwd와 salt 합친 문자열에 SHA-256 적용
    	md.update((pwd+salt).getBytes());
    	byte[] pwdsalt = md.digest();
    	
    	// 3. byte to String (16진수의 문자열로 변경)
    	StringBuffer sb = new StringBuffer();
    	for(byte b : pwdsalt) {
    		sb.append(String.format("%02x", b));
    	}
    	
		return sb.toString();
    }
}