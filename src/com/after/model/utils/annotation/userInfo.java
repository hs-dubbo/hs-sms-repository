/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.after.model.utils.annotation;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��5��15�� <br>
 * ������
 */
public class userInfo {

	private String userId;

	@userInfoAnnotation(userName="xu")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
}
