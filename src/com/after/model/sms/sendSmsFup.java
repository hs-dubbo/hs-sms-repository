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
 * 作者：Administrator <br>
 * 创建时间：2018年4月13日 <br>
 * 描述：
 */
public class sendSmsFup {

	/**
	 * 作者：Administrator <br>
	 * 创建时间：2018年4月13日 <br>
	 * 描述： 加载预判相关文档是否合法存在
	 * @param sourceFile
	 * @param templateFile
	 * @return 0：正常 -1：系统异常
	 */
	public static int loadLocalSourceFile(){
		
		String sourcePath=StringUtils.getSourceFilePath();
		File file=new File(sourcePath);
		File[] files=file.listFiles();
		try {
		if(files!=null){
			for (int i = 0; i < files.length; i++) {
				String filesTemp=String.valueOf(files[i]);
				LogCvt.info("所有文件信息"+filesTemp);
				if(filesTemp.indexOf(".txt")>0){
					ReadFileStream.readNIO(filesTemp);
				}
				if(filesTemp.indexOf(".xls")>0){
					String version = "version2003";// Excel的版本号
					InputStream	is = new FileInputStream(filesTemp);
					LogCvt.info("短信信息为【"+Contans.getModelMessValue(Contans.SMS_TEMPLATE)+"】");
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
		String sourceFile = "./source/根据进件编号查询.xls";
		String templateFile = "./source/SmsTemplate.txt";
		sendSmsFup.loadLocalSourceFile();
	}
}
