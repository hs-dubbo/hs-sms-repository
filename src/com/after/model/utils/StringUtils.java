/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.after.model.utils;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��4��13�� <br>
 * ������
 */
public class StringUtils {

	/**
	 * ���ߣ�Administrator <br>
	 * ����ʱ�䣺2018��4��13�� <br>
	 * ������ ��ȡ��Ż�ִ�ļ���ַ
	 * @return
	 */
	public static String getPath(){
		String respFile=System.getProperty("user.dir")+Contans.RESP_PATH+
				DateUtilsSimpleTool.getCurrentDateValue(DateUtilsSimpleTool.yyyyMMddHHmmss24)+Contans.FILE_fIX_RESP;
		return respFile;
	}
	
	public static String getSourceFilePath(){
		return System.getProperty("user.dir")+Contans.RESP_PATH;
	}
}
