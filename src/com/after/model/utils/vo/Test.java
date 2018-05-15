/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.after.model.utils.vo;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年5月4日 <br>
 * 描述：
 */
public class Test {

	public static void main(String[] args) {
	
		demoInfo info=(demoInfo)forName(demoInfo.class);
	}
	
   public static Object forName(Class clazz){
		System.out.println(clazz.getName());
		
		return null;
	}
}
