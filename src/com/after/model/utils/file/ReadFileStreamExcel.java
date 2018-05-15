/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.after.model.utils.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.after.model.utils.Contans;
import com.after.model.utils.HoomValidate;
import com.after.model.utils.LogCvt;
import com.after.model.utils.StringUtils;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年4月13日 <br>
 * 描述：
 */
public class ReadFileStreamExcel {

	/**
	 * 作者：Administrator <br>
	 * 创建时间：2018年4月13日 <br>
	 * 描述： 
	 * @param is
	 * @param version
	 * @param message读取短信批量文件
	 * @return
	 * @throws Exception
	 */
	public static String readExcelUserMsg(InputStream is, String version, String message) throws Exception {
		long start=System.currentTimeMillis();
		ExecutorService pool=Executors.newFixedThreadPool(5);
		StringBuffer sb = new StringBuffer();
		if (version == null) {
			version = "version2003";
		}
		FileOutputStream fosWrite=WriteFileStream.createFileOutStream(StringUtils.getPath());
		LogCvt.info("回执文件路径为"+StringUtils.getPath());
		final FileChannel channelfosWrite = fosWrite.getChannel();
		try {
			Workbook hwk = null;
			if (Contans.EXCEL_VERSION_XLS.equals(version)) {
				// 将is流实例到 一个excel流里
				hwk = new HSSFWorkbook(is);
			} else if (Contans.EXCEL_VERSION_XLSX.equals(version)) {
				// 将is流实例到 一个excel流里
				// hwk = new XSSFWorkbook(is);
			} else {
				return null;
			}
			// 得到book第一个工作薄sheet
			Sheet sh = hwk.getSheetAt(0);
			// 总行数
			int rows = sh.getLastRowNum() + 1 - sh.getFirstRowNum();
			/**
			 * 开始分批处理 5000条数据一批
			 */
			int count_total_index = 1;
			int totalNumber = HoomValidate.getTotalNumber(rows-1);
			List<String>telPhone=null;
			for (int t = 0; t < totalNumber; t++) {
				LogCvt.info("===============开始处理第===============" + (t + 1) + "批 ;");
				int cols = 0;
				telPhone=new ArrayList<String>();
				int arrayCols = 0;
				for (int i = count_total_index; i < (count_total_index + Contans.batchNum); i++) {
					Row row = sh.getRow(i);
					if (row == null)// 如果空行 跳过
						continue;
					if (i == 0) {// 获取该行总列数
						cols = row.getLastCellNum() - row.getFirstCellNum();
					}
					/**
					 * 读取电话号码
					 */
					Object col1 = row.getCell(0);
					if (col1 != null || "null".equals(String.valueOf(col1))) {
						telPhone.add(String.valueOf(col1));
					}
					arrayCols++;
				}
				count_total_index = count_total_index + Contans.batchNum;
				
				/**************多线程处理*****************************************************/
				try {
					if (telPhone != null) {
						for (int i = 0; i < telPhone.size(); i++) {
							if (null != telPhone.get(i)|| !"null".equals(String.valueOf(telPhone.get(i)))) {
								final String telphoneNum = String.valueOf(telPhone.get(i)).trim();
								Future<Integer> task = pool.submit(new Callable<Integer>() {
					                public Integer call() throws Exception {
										 try {
											 LogCvt.info("子线程pid===>" + Thread.currentThread().getName());
//											 int count = LingKaiMSM.sendSMSPost(Contans.CORP_ID, Contans.CORP_PWD,
//														telphoneNum, message, "");
											 int count= -1;
											 if(count>0){
												 LogCvt.info("用户"+telphoneNum+"发送成功......");
											 }else{
												  //发送失败回执文件记录
												 StringBuilder sbWrite=new StringBuilder();
												 sbWrite.append(telphoneNum).append("\n");
												 ByteBuffer src = Charset.forName(Contans.CHARTSET).encode(sbWrite.toString());
												 channelfosWrite.write(src);
												 LogCvt.info("用户"+telphoneNum+"发送失败......");
											 }
										} catch (Exception e) {
											// TODO: handle exception
											LogCvt.error("子线程异常......");
											return -1;
										}
					                    return 0;
					                }
					            });
								
								if(task.get()==-1){
									throw new RuntimeException("子线程异常结束");
								}
							}
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					throw new RuntimeException("线程异常，系统终止！");
				}
				
				/**
				 * 此处延迟等待调用业务层处理完成再继续读取数据
				 */
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				LogCvt.info("===============第===============" + (t + 1) + "批处理结束");
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("解析Excel失败，错误原因为：" + e.getMessage());
		}finally{
			LogCvt.info("关闭回执文件流......");
			fosWrite.close();
			pool.shutdown();
			long end=System.currentTimeMillis();
			LogCvt.info("耗时："+(end-start)+"ms");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		InputStream is;
		try {
			String version = "version2003";// Excel的版本号
			is = new FileInputStream("D:/短信发送/批量发送民生银行短信模板二次发送5.1.xls");
			ReadFileStreamExcel.readExcelUserMsg(is, version, "");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
