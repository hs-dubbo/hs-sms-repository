/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.after.model.utils.annotation;

import java.lang.reflect.Method;


/**
 * 作者：Administrator <br>
 * 创建时间：2018年5月15日 <br>
 * 描述：
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
