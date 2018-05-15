package com.after.model.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtilsSimpleTool {

	public static final String yyyyMMddHHmmss="yyyy-MM-dd HH:mm:ss";
	public static final String yyyyMMddHHmmss24="yyyyMMddHHmmss";
	public static final String yyyyMMddHHmm="yyyy-MM-dd HH:mm";
	public static final String yyyyMMdd="yyyy-MM-dd";
	
	
	public static String dateToString(Date date,String type){
		SimpleDateFormat sdf= new SimpleDateFormat(type);
		return sdf.format(date);
	}
	
	
	public static Date stringToDate(String date,String type){
		Date newDate=null;
		SimpleDateFormat sdf= new SimpleDateFormat(type);
		try {
			newDate=sdf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newDate;
	}
	
	
	public static String stringChangeDate(String value,String type){
		if(value!=null){
			Date date=stringToDate(value,type);
			return dateToString(date,type);
		}
		return "";
	}
	
	public static String getCurrentDateValue(String type){
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		return sdf.format(d);
	}
	
	
	public static Date getCurrentDate(String type){
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		return stringToDate(sdf.format(new Date()),type);
	}
	
	
	@Deprecated
	public static String stringToDateParse(String date,String type){
		date = date.replace("Z", " UTC");
		String dateTime="";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
		try {
			Date d = format.parse(date );
			dateTime=DateUtilsSimpleTool.dateToString(d, type);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dateTime;
	}
	
	public static Date getendDate() {
		Date start = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date d1 = c.getTime();
			start = sdf.parse(sdf.format(d1));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return start;
	}
	public static void main(String[] args) {
		//System.out.println(dateToString(getCurrentDateValue("yyyy/MM"),"yyyy/MM"));
	}
}

