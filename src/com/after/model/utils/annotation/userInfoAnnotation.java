/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.after.model.utils.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��5��15�� <br>
 * ������
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface userInfoAnnotation {

	String userId() default "";
	
	String userName() default "";
	
	String userSex() default "";
	
}
