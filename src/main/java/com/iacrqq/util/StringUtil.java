package com.iacrqq.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author sihai
 * 
 */
public class StringUtil extends StringUtils
{
	public static final String DEFAULT_ENCODE = "utf-8";

	public static final String EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	public static Pattern EMAIL_REGEX = Pattern.compile(EMAIL);

	public static final String PHONE = "^(13[4,5,6,7,8,9]|15[0,8,9,1,7]|188|187)\\d{8}$";
	public static Pattern PHONE_REGEX = Pattern.compile(PHONE);

	// Length
	// -----------------------------------------------------------------------
	public static final int length(String str, String encode)
	{
		if (isEmpty(str))
		{
			return 0;
		}

		try
		{
			return str.getBytes(encode).length;
		}
		catch (UnsupportedEncodingException e)
		{
			throw new RuntimeException(e);
		}
	}

	public static final int length(String str)
	{
		return length(str, DEFAULT_ENCODE);
	}

	// Email
	// -----------------------------------------------------------------------
	public static final boolean isEmail(String str)
	{
		if (isBlank(str))
		{
			return false;
		}
		
		return EMAIL_REGEX.matcher(str).matches();
	}

	// 二行制转字符串
	public static final String byte2Hex(byte[] b)
	{
		StringBuilder hs = new StringBuilder();
		String stmp = "";
		for (int n = 0; n < b.length; n++)
		{
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
			{
				hs.append("0").append(stmp);
			}
			else
			{
				hs.append(stmp);
			}
		}
		return hs.toString().toUpperCase();
	}
	
	public static final String formatTitle(String title, int maxLength)
	{
		return String.format("%s %s", substring(title, 0, maxLength), length(title) > maxLength ? "......" : "");
	}
}
