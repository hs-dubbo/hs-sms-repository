/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.after.model.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��4��12�� <br>
 * ������
 */
public class Contans {
	
	public static final int batchNum = 10000;
	public static final String EXCEL_VERSION_XLS = "version2003";
	public static final String EXCEL_VERSION_XLSX = "version2007";
	public static final String SMS_TEMPLATE = "smsTemplate";//�洢����ģ����Ϣ
	public static final String CHARTSET = "UTF-8";//
	public static final String FILE_PATH = "/config/";//
	public static final String RESP_PATH = "/source/";//��ִ�ļ�·��
	public static final String FILE_fIX_RESP = ".RESP";//
	public static final String CORP_ID= "XAJS003463";
	public static final String CORP_PWD= "ws@3463";
	public static final Map<String,String>map=new HashMap<String,String>();
	public static  Map<String,String>modelMess=new HashMap<String,String>();
	
	static{
		map.put("1", "���ŷ���");
		map.put("2", "�ʼ�����");
		map.put("exit", "�ر�ϵͳ");
		map.put("start", "��ʼ����");
		map.put("help", "�������");
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
