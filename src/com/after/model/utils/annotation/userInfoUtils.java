/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.after.model.utils.annotation;

import java.lang.reflect.Method;


/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��5��15�� <br>
 * ������
 */
public class userInfoUtils {

	public static void annotationUtils(Class<?> clazz){
		Method[] method =clazz.getDeclaredMethods();
		for (Method method2 : method) {
			userInfoAnnotation annotation=	method2.getAnnotation(userInfoAnnotation.class);
			if(annotation!=null){
				System.out.println(annotation.userName());
			}
		}
	}
	
	public static void main(String[] args) {
		userInfoUtils.annotationUtils(userInfo.class);
	}
}
