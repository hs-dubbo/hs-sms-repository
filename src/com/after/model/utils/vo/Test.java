/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.after.model.utils.vo;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��5��4�� <br>
 * ������
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
