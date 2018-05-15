/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.after.model.utils.vo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * 作者：Administrator <br>
 * 创建时间：2018年5月4日 <br>
 * 描述：
 */
public class demoInfo {

	public String castName;
	
	public String castId;
	
	public String aaId;
	
	public String ssId;

	public String getCastName() {
		return castName;
	}

	public void setCastName(String castName) {
		this.castName = castName;
	}

	public String getCastId() {
		return castId;
	}

	public void setCastId(String castId) {
		this.castId = castId;
	}

	public String getAaId() {
		return aaId;
	}
	public void setAaId(String aaId) {
		this.aaId = aaId;
	}
	
	public String getSsId() {
		return ssId;
	}

	public void setSsId(String ssId) {
		this.ssId = ssId;
	}

	public static void main(String[] args) {
		demoInfo info=new demoInfo();
		 Class<?> clz = info.getClass();
		 Field[] fields = clz.getDeclaredFields();
		 for (Field field : fields) {
			System.err.println(field.getName());
			try {
				//field.getName().substring(0,1).toUpperCase()+field.getName().substring(1,field.getName().length());
				Method met = info.getClass().getMethod("setCastName",String.class) ;
				met.invoke(info,"xuxinyuan") ; //设置setter的内容
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 
		
	}
}
