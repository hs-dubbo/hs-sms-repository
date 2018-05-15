/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.after.model.utils;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年4月13日 <br>
 * 描述：
 */
public class StringUtils {

	/**
	 * 作者：Administrator <br>
	 * 创建时间：2018年4月13日 <br>
	 * 描述： 获取存放回执文件地址
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
