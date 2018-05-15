/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.after.model.utils.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import com.after.model.utils.Contans;
import com.after.model.utils.LogCvt;
import com.after.model.utils.vo.demoInfo;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��5��4�� <br>
 * ������
 */
public class excelUtils {

	public static void readExcel(InputStream is, String version) throws IOException{
		Workbook hwk = getWorkbookXls(is,version);
		Sheet sh = getExcelSheet(hwk,0);
		int rows = statisticsRows(sh);
		int totalNumber = batchNum(rows-1);
		
		int count_total_index = 1;
		for (int t = 0; t < totalNumber; t++) {
			LogCvt.info("===============��ʼ�����===============" + (t + 1) + "�� ;");
			int cols = 0;
			for (int i = count_total_index; i < (count_total_index + Contans.batchNum); i++) {
				Row row = sh.getRow(i);
				if (row == null){
					continue;
				}else{
					cols = row.getLastCellNum() - row.getFirstCellNum();
					Object ob=forName(demoInfo.class,cols,row);
					demoInfo info2=(demoInfo)ob;
					System.err.println("=="+info2.getCastName()+"  "+info2.getCastId()+"  "+info2.getAaId()+"   "+info2.getSsId());
				}
			}
			count_total_index = count_total_index + Contans.batchNum;
			
			LogCvt.info("===============��===============" + (t + 1) + "���������");
		}
	}
	
	/**
	 * ������ ��ȡexcel�ļ����󣬻�ȡ����֮ǰ���ж��Ƿ���xls��ʽ�ļ�
	 *               �����ǣ��򷵻أ��ݲ�֧��xlsx��ʽ��
	 * @param is
	 * @param version
	 * @return
	 */
	public static Workbook getWorkbookXls(InputStream is, String version){
		Workbook hwk = null;
		try {
			if (version == null) {
				version = "version2003";
			}
		if (Contans.EXCEL_VERSION_XLS.equals(version)) {
				hwk = new HSSFWorkbook(is);
		  }else{
			  return null;
		  }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
		return hwk;
	}
	
	/**
	 * ������ ��ȡexcel������Ԫ
	 * @param hwk
	 * @param sheet  ������Ԫ���
	 * @return
	 */
	public static Sheet getExcelSheet(Workbook hwk,int sheet){
		Sheet sh =null;
		try {
			if(hwk==null){
				throw new RuntimeException("workbook object  is not null");
			}
			if(sheet<0){
				throw new RuntimeException("The number of work units should not be less than 0");
			}
			 sh = hwk.getSheetAt(sheet);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("getting the work unit object exception");
		}
		return sh;
	}
	/**
	 * ������ ͳ��ÿ��������Ԫ�е�����������
	 * @param sh
	 * @return ������
	 */
	public static int statisticsRows(Sheet sh){
		int rows=0;
		try {
			if(sh==null){
				throw new RuntimeException("the work unit object is not null ");
			}
			rows = sh.getLastRowNum() + 1 - sh.getFirstRowNum();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("statistics of the total number of data in each unit of work  exception");
		}
		return rows;
	}
	
	/**
	 * ������ ������ȡ��������
	 * @param rows
	 * @return
	 */
	public static int batchNum(int rows){
		try {
			if(rows!=0){
				int totalNumber = rows / Contans.batchNum;
				int remainNumber = rows % Contans.batchNum;
				if (remainNumber != 0) {
					totalNumber = totalNumber + 1;
				}
				return totalNumber;
			}
		} catch (Exception e) {
			// TODO: handle exception  NullPointerException
			throw new NullPointerException("number computational anomaly");
		}
		return rows;
	}
	
	/**
	 * ����ʱ�䣺2018��5��4�� <br>
	 * ������ ����java�����Ŀ�����ֵ
	 * @param ob
	 * @param value
	 */
   public static Object forName(Class clazz,int size,Row row){
	   Object object=null;
	   int count=0;
	   try {  
	     Class<?> clazzInfo=Class.forName(String.valueOf(clazz.getName()));
	     object=clazzInfo.newInstance();
		 Field[] fields = object.getClass().getDeclaredFields();
		 if(fields.length>size){
			 count=size;
		 }else{
			 count=fields.length;
		 }
		 for (int i = 0; i < count; i++) {
			 Object col = row.getCell(i);
			 Method met = object.getClass().getMethod("set"+getUperCase(fields[i].getName()),String.class) ;
			 met.invoke(object,String.valueOf(col)) ;
		   }
		 } catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         return object;
	}
	
   /**
    * ������ �ַ�������ĸ��д
    * @param value
    * @return
    */
   public static String getUperCase(String value){
	   if(!value.isEmpty()){
		   value=value.substring(0,1).toUpperCase()+value.substring(1,value.length());
		   return value;
	   }
	   return value;
   }
	public static void main(String[] args) {
		InputStream is;
		try {
			String version = "version2003";// Excel�İ汾��
			is = new FileInputStream("D:/���ŷ���/20180427/����20180427.xls");
			excelUtils.readExcel(is, version);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
