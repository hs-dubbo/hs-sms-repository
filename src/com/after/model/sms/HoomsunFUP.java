/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.after.model.sms;

import java.util.Scanner;

import com.after.model.utils.HoomValidate;
import com.after.model.utils.LogCvt;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��4��12�� <br>
 * ������
 */
public class HoomsunFUP {

	public static void main(String[] args) {
		Scanner sb = new Scanner(System.in);  
		
		while (true) {
			   LogCvt.info("|��������[1:���ŷ���  2:�ʼ�����]******************************************|");
			   LogCvt.info("|***********************************************************************|");
		        String code = sb.nextLine();  
		        if(HoomValidate.validate(code)==-1){
		        	LogCvt.info("|*************************ϵͳ�ر�************************************|");
		        	sb.close();
		        }else if(HoomValidate.validate(code)==-2){
		        	LogCvt.info("�����ȷ�����������룡");
		        	continue;
		        }else{
		        	sendSmsFup.loadLocalSourceFile();
		        }
		}
	}
}
