/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.after.model.utils.annotation;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年5月15日 <br>
 * 描述：
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
