/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.after.model.utils;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��4��19�� <br>
 * ������
 */
public class UserInfo {

	public String id;
	public String createTime;
	public Date createTimeDate;
	public Date updateTime;
	public String loanId;
	public String bal;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Date getCreateTimeDate() {
		return createTimeDate;
	}
	public void setCreateTimeDate(Date createTimeDate) {
		this.createTimeDate = createTimeDate;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	public String getBal() {
		return bal;
	}
	public void setBal(String bal) {
		this.bal = bal;
	}
	public static void main(String[] args) {
		Map<String,String> map=new ConcurrentHashMap<String,String>();
		map.put("key1", "132");
		map.putIfAbsent("key", "0000");
		System.out.println(map.getOrDefault("key2", "xu"));
	}
}
