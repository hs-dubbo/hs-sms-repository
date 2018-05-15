/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.after.model.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年4月12日 <br>
 * 描述：
 */
public class HoomValidate {

	
	public static int validate(String code){
		if(code.equals("exit")){
			return -1;
		}
		if(Contans.getValue(code)==null){
			return -2;
		}
		if("help".equals(code)){
			System.out.println("【"+Contans.toHelp()+"】");
			return -2;
		}
		return 0;
	}
	
	/**
	 * 根据总行数计算分批次数
	 * 
	 * @param rows
	 * @return
	 */
	public static int getTotalNumber(int rows) {
		int totalNumber = rows / Contans.batchNum;
		int remainNumber = rows % Contans.batchNum;
		if (remainNumber != 0) {
			totalNumber = totalNumber + 1;
		}
		return totalNumber;
	}
	
	public static void main(String[] args) {
		UserInfo info=null;
		List<UserInfo>list=new ArrayList<UserInfo>();
		for (int i = 0; i < 10; i++) {
			info=new UserInfo();
			info.setId("xxxx"+i);
			info.setLoanId("wqwq"+i);
			list.add(info);
		}
		
		for (UserInfo userInfo : list) {
			System.out.println(userInfo.getId());
			list.remove(userInfo);
		}
		
	}
}
