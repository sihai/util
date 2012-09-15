package com.iacrqq.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 
 * @author sihai
 *
 */
public class DateUtil
{
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	
	public static String format(Date date)
	{
		return format(date, null);
	}
	
	public static String format(Date date, String format)
	{
		if(date == null)
		{
			return "";
		}
		
		String tmpFormat = DEFAULT_DATE_FORMAT;
		
		if(!StringUtil.isBlank(format))
		{
			tmpFormat = format;
		}
		
		SimpleDateFormat sf = new SimpleDateFormat(tmpFormat);
		
		return sf.format(date);
	}
	
	public static Date parse(String str) throws ParseException
	{
		return parse(str, DEFAULT_DATE_FORMAT);
	}
	
	public static Date parse(String str, String format) throws ParseException
	{
		SimpleDateFormat parser = new SimpleDateFormat(format);
		parser.setTimeZone(TimeZone.getDefault());
		return parser.parse(str);
	}
	
	/**
	 * 
	 * @param time
	 * @return
	 */
	public String distance4String(Date time)
	{
		StringBuilder sb = new StringBuilder();
		Calendar endCal = Calendar.getInstance();
		endCal.setTime(time);
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(new Date());
		
		long distance = (endCal.getTimeInMillis() - startCal.getTimeInMillis()) / 1000;		// seconds
		long day = distance / (24 * 3600);
		distance -= day * 24 * 3600;
		long hour = distance / 3600;
		distance -= hour * 3600;
		long minute = distance / 60;
		//distance -= minute * 60;
		//long second = distance;
		
		if(day > 0)
		{
			sb.append(day);
			sb.append("天");
		}
		
		if(hour > 0)
		{
			sb.append(hour);
			sb.append("小时");
		}
		
		if(minute > 0)
		{
			sb.append(minute);
			sb.append("分钟");
		}
		
		/*if(second > 0)
		{
			sb.append(second);
			sb.append("秒");
		}*/
		
		return sb.toString();
	}
}
