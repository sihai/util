package com.iacrqq.util;

/**
 * 版本：xx.yy.zz
 * 将版本编码到一个Long型整数，只用到Long整数的低3个字节
 * @author sihai
 *
 */
public class VersionUtil
{
	public static final Long DEFAULT_VERSION = 0L;	// 0.0.0
	public static final String VERSION_SEPARATOR = ".";
	public static final int VERSION_LENGTH = 3;
	
	public static String convert2String(Long version)
	{
		if(version == null)
		{
			return null;
		}
		
		long zz = version & 0xFF;
		long yy = (version & 0xFF00) >> 8;
		long xx = (version & 0xFF0000) >> 16;
		
		StringBuilder sb = new StringBuilder();
		sb.append(xx);
		sb.append(VERSION_SEPARATOR);
		sb.append(yy);
		sb.append(VERSION_SEPARATOR);
		sb.append(zz);
		return sb.toString();
	}
	
	public static Long toLong(String version)
	{
		if(version == null)
		{
			return null;
		}
		
		String[] components = version.split(VERSION_SEPARATOR);
		if(components == null || components.length != VERSION_LENGTH)
		{
			return null;
		}
		
		long xx = Long.valueOf(components[0]);
		long yy = Long.valueOf(components[1]);
		long zz = Long.valueOf(components[2]);
		
		return xx | yy | zz;
	}
	
	public static Long littleChange(Long version)
	{
		if(version == null)
		{
			return null;
		}
		
		long zz = version & 0xFF;
		zz++;
		
		return (version & 0x00) | zz;
	}
	
	public static Long middleChange(Long version)
	{
		if(version == null)
		{
			return null;
		}
		
		long yy = version & 0xFF00;
		yy++;
		yy <<= 8;
		return (version & 0x00FF) | yy;
	}
	
	public static Long bigChange(Long version)
	{
		if(version == null)
		{
			return null;
		}
		
		long xx = version & 0xFF0000;
		xx++;
		xx <<= 16;
		return (version & 0x00FFFF) | xx;
	}
	
	public static void main(String[] args)
	{
		Long version = 0L;
		System.out.println(convert2String(middleChange(version)));
		System.out.println(convert2String(bigChange(version)));
	}
}
