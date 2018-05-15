/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.after.model.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��4��12�� <br>
 * ������
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
			System.out.println("��"+Contans.toHelp()+"��");
			return -2;
		}
		return 0;
	}
	
	/**
	 * ���������������������
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
