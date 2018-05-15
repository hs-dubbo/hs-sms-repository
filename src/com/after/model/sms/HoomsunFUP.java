/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.after.model.sms;

import java.util.Scanner;

import com.after.model.utils.HoomValidate;
import com.after.model.utils.LogCvt;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年4月12日 <br>
 * 描述：
 */
public class HoomsunFUP {

	public static void main(String[] args) {
		Scanner sb = new Scanner(System.in);  
		
		while (true) {
			   LogCvt.info("|操作类型[1:短信发送  2:邮件发送]******************************************|");
			   LogCvt.info("|***********************************************************************|");
		        String code = sb.nextLine();  
		        if(HoomValidate.validate(code)==-1){
		        	LogCvt.info("|*************************系统关闭************************************|");
		        	sb.close();
		        }else if(HoomValidate.validate(code)==-2){
		        	LogCvt.info("命令不正确，请重新输入！");
		        	continue;
		        }else{
		        	sendSmsFup.loadLocalSourceFile();
		        }
		}
	}
}
