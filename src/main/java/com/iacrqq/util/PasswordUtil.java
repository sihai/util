package com.iacrqq.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 
 * @author sihai
 *
 */
public class PasswordUtil
{
	public static final String DEFAULT_ENCRYPT_METHOD = "SHA-1";
	
	public static final int DEFAULT_PASSWORD_LENGTH = 6;
	private static final char[] PASSWORD_COMPONENTS = {
		'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T',
		'U','V','W','X','Y','Z','\\','^','_','a','b','c','d','e','f','g','h','i',
		'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','!','@','%',
		'*','(',')','+','=','-','#','$'
	};
	
	public static String generatePassword() {
		return generatePassowrd(DEFAULT_PASSWORD_LENGTH);
	}
	
	/**
	 * 
	 * @param length
	 * @return
	 */
	public static String generatePassowrd(int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random(System.currentTimeMillis());
		for(int i = 0; i < length; i++) {
			sb.append(PASSWORD_COMPONENTS[random.nextInt(PASSWORD_COMPONENTS.length)]);
		}
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param password
	 * @return
	 */
	public static final String encrypt(String password)
	{
		return encrypt(password, DEFAULT_ENCRYPT_METHOD);
	}
	
	/**
	 * 
	 * @param password
	 * @param method
	 * @return
	 */
	public static final String encrypt(String password, String method)
	{
		try
		{
			MessageDigest messageDigest = MessageDigest.getInstance(method);
			messageDigest.update(password.getBytes(StringUtil.DEFAULT_ENCODE));
			return StringUtil.byte2Hex(messageDigest.digest());
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
		catch(UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public static final boolean equals(String password, String encryptedPassword)
	{
		return equals(password, encryptedPassword, DEFAULT_ENCRYPT_METHOD);
	}
	
	/**
	 * 
	 * @param password
	 * @param encryptedPassword
	 * @return
	 */
	public static final boolean equals(String password, String encryptedPassword, String method)
	{
		String encrypted = encrypt(password, method);
		return StringUtil.equals(encrypted, encryptedPassword);
		
	}
	
	public static void main(String[] args)
	{
		System.out.println(encrypt("123456"));
		System.out.println(equals("378206", encrypt("378206")));
	}
}
