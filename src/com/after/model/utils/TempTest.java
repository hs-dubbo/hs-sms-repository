/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.after.model.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��4��23�� <br>
 * ������
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
