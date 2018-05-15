/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.after.model.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年4月23日 <br>
 * 描述：
 */
public class TempTest {

	public static void main(String[] args) {
		List<String>list1=new ArrayList<String>();//size=5
		List<String>list2=new ArrayList<String>();//size=2
		
		for (int i = 0; i < 5; i++) {
			list1.add("a"+i);
		}
		for (int i = 0; i < 1; i++) {
			list1.add("@");
		}
		///////////////////////////////////////////////
		int count=0;
		for (int a = 0; a < 3; a++) {
			for (int i= count ; i < (count+2); i++) {
				System.out.println(list1.get(i));
			}
			count=count+2;
			System.out.println("========");
		}
		
		
		
		
		
	}
	
	public static int getTotalNumber(int rows,int core) {
		int totalNumber = rows / core;
		int remainNumber = rows % core;
		if (remainNumber != 0) {
			totalNumber = totalNumber + 1;
		}
		return totalNumber;
	}
}
