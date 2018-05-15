/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
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
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��4��13�� <br>
 * ������
 */
public class ReadFileStreamExcel {

	/**
	 * ���ߣ�Administrator <br>
	 * ����ʱ�䣺2018��4��13�� <br>
	 * ������ 
	 * @param is
	 * @param version
	 * @param message��ȡ���������ļ�
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
		LogCvt.info("��ִ�ļ�·��Ϊ"+StringUtils.getPath());
		final FileChannel channelfosWrite = fosWrite.getChannel();
		try {
			Workbook hwk = null;
			if (Contans.EXCEL_VERSION_XLS.equals(version)) {
				// ��is��ʵ���� һ��excel����
				hwk = new HSSFWorkbook(is);
			} else if (Contans.EXCEL_VERSION_XLSX.equals(version)) {
				// ��is��ʵ���� һ��excel����
				// hwk = new XSSFWorkbook(is);
			} else {
				return null;
			}
			// �õ�book��һ��������sheet
			Sheet sh = hwk.getSheetAt(0);
			// ������
			int rows = sh.getLastRowNum() + 1 - sh.getFirstRowNum();
			/**
			 * ��ʼ�������� 5000������һ��
			 */
			int count_total_index = 1;
			int totalNumber = HoomValidate.getTotalNumber(rows-1);
			List<String>telPhone=null;
			for (int t = 0; t < totalNumber; t++) {
				LogCvt.info("===============��ʼ�����===============" + (t + 1) + "�� ;");
				int cols = 0;
				telPhone=new ArrayList<String>();
				int arrayCols = 0;
				for (int i = count_total_index; i < (count_total_index + Contans.batchNum); i++) {
					Row row = sh.getRow(i);
					if (row == null)// ������� ����
						continue;
					if (i == 0) {// ��ȡ����������
						cols = row.getLastCellNum() - row.getFirstCellNum();
					}
					/**
					 * ��ȡ�绰����
					 */
					Object col1 = row.getCell(0);
					if (col1 != null || "null".equals(String.valueOf(col1))) {
						telPhone.add(String.valueOf(col1));
					}
					arrayCols++;
				}
				count_total_index = count_total_index + Contans.batchNum;
				
				/**************���̴߳���*****************************************************/
				try {
					if (telPhone != null) {
						for (int i = 0; i < telPhone.size(); i++) {
							if (null != telPhone.get(i)|| !"null".equals(String.valueOf(telPhone.get(i)))) {
								final String telphoneNum = String.valueOf(telPhone.get(i)).trim();
								Future<Integer> task = pool.submit(new Callable<Integer>() {
					                public Integer call() throws Exception {
										 try {
											 LogCvt.info("���߳�pid===>" + Thread.currentThread().getName());
//											 int count = LingKaiMSM.sendSMSPost(Contans.CORP_ID, Contans.CORP_PWD,
//														telphoneNum, message, "");
											 int count= -1;
											 if(count>0){
												 LogCvt.info("�û�"+telphoneNum+"���ͳɹ�......");
											 }else{
												  //����ʧ�ܻ�ִ�ļ���¼
												 StringBuilder sbWrite=new StringBuilder();
												 sbWrite.append(telphoneNum).append("\n");
												 ByteBuffer src = Charset.forName(Contans.CHARTSET).encode(sbWrite.toString());
												 channelfosWrite.write(src);
												 LogCvt.info("�û�"+telphoneNum+"����ʧ��......");
											 }
										} catch (Exception e) {
											// TODO: handle exception
											LogCvt.error("���߳��쳣......");
											return -1;
										}
					                    return 0;
					                }
					            });
								
								if(task.get()==-1){
									throw new RuntimeException("���߳��쳣����");
								}
							}
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					throw new RuntimeException("�߳��쳣��ϵͳ��ֹ��");
				}
				
				/**
				 * �˴��ӳٵȴ�����ҵ��㴦������ټ�����ȡ����
				 */
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				LogCvt.info("===============��===============" + (t + 1) + "���������");
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("����Excelʧ�ܣ�����ԭ��Ϊ��" + e.getMessage());
		}finally{
			LogCvt.info("�رջ�ִ�ļ���......");
			fosWrite.close();
			pool.shutdown();
			long end=System.currentTimeMillis();
			LogCvt.info("��ʱ��"+(end-start)+"ms");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		InputStream is;
		try {
			String version = "version2003";// Excel�İ汾��
			is = new FileInputStream("D:/���ŷ���/���������������ж���ģ����η���5.1.xls");
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
