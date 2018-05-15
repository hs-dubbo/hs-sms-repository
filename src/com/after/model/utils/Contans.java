/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.after.model.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年4月12日 <br>
 * 描述：
 */
public class Contans {
	
	public static final int batchNum = 10000;
	public static final String EXCEL_VERSION_XLS = "version2003";
	public static final String EXCEL_VERSION_XLSX = "version2007";
	public static final String SMS_TEMPLATE = "smsTemplate";//存储短信模板信息
	public static final String CHARTSET = "UTF-8";//
	public static final String FILE_PATH = "/config/";//
	public static final String RESP_PATH = "/source/";//回执文件路径
	public static final String FILE_fIX_RESP = ".RESP";//
	public static final String CORP_ID= "XAJS003463";
	public static final String CORP_PWD= "ws@3463";
	public static final Map<String,String>map=new HashMap<String,String>();
	public static  Map<String,String>modelMess=new HashMap<String,String>();
	
	static{
		map.put("1", "短信发送");
		map.put("2", "邮件发送");
		map.put("exit", "关闭系统");
		map.put("start", "开始发送");
		map.put("help", "命令帮助");
		modelMess.put(SMS_TEMPLATE, "");
	}
		
	public static String getValue(String key){
		return Contans.map.get(key);
	}
	
	public static void setModelMessValue(String mess){
		Contans.modelMess.put(SMS_TEMPLATE, mess);
	}
	public static String getModelMessValue(String key){
		return Contans.modelMess.get(key);
	}
	
	public static String toHelp(){
		return JSON.toJSONString(Contans.map);
	}
	
	public static void main(String[] args) {
		File file=new File("D:\\xu.resp");
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
