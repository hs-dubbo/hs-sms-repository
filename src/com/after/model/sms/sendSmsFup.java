package com.after.model.sms;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.after.model.utils.Contans;
import com.after.model.utils.LogCvt;
import com.after.model.utils.StringUtils;
import com.after.model.utils.file.ReadFileStream;
import com.after.model.utils.file.ReadFileStreamExcel;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��4��13�� <br>
 * ������
 */
public class sendSmsFup {

	/**
	 * ���ߣ�Administrator <br>
	 * ����ʱ�䣺2018��4��13�� <br>
	 * ������ ����Ԥ������ĵ��Ƿ�Ϸ�����
	 * @param sourceFile
	 * @param templateFile
	 * @return 0������ -1��ϵͳ�쳣
	 */
	public static int loadLocalSourceFile(){
		
		String sourcePath=StringUtils.getSourceFilePath();
		File file=new File(sourcePath);
		File[] files=file.listFiles();
		try {
		if(files!=null){
			for (int i = 0; i < files.length; i++) {
				String filesTemp=String.valueOf(files[i]);
				LogCvt.info("�����ļ���Ϣ"+filesTemp);
				if(filesTemp.indexOf(".txt")>0){
					ReadFileStream.readNIO(filesTemp);
				}
				if(filesTemp.indexOf(".xls")>0){
					String version = "version2003";// Excel�İ汾��
					InputStream	is = new FileInputStream(filesTemp);
					LogCvt.info("������ϢΪ��"+Contans.getModelMessValue(Contans.SMS_TEMPLATE)+"��");
					ReadFileStreamExcel.readExcelUserMsg(is, version, Contans.getModelMessValue(Contans.SMS_TEMPLATE));
				}
			}
		}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public static void main(String[] args) {
		String sourceFile = "./source/���ݽ�����Ų�ѯ.xls";
		String templateFile = "./source/SmsTemplate.txt";
		sendSmsFup.loadLocalSourceFile();
	}
}
