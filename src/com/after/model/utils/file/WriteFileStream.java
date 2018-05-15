/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.after.model.utils.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import com.after.model.utils.Contans;
import com.after.model.utils.LogCvt;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��4��13�� <br>
 * ������
 */
public class WriteFileStream {

	@Deprecated
	public static void writeNio() {
        String filename = "D:\\out.txt";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(filename));
            FileChannel channel = fos.getChannel();
            ByteBuffer src = Charset.forName("utf8").encode("���������������");
            LogCvt.info("��ʼ��������limit��" + src.capacity() + "," + src.limit());
            int length = 0;  
            while ((length = channel.write(src)) != 0) {
                /*  
                 * ע�⣬���ﲻ��Ҫclear���������е�����д�뵽ͨ���к� �ڶ��ν�����һ�ε�˳�����¶�  
                 */  
                LogCvt.info("д�볤��:" + length);
            }  
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();  
        } finally {  
            if (fos != null) {
                try {  
                    fos.close();  
                } catch (IOException e) {
                    e.printStackTrace();  
                }
            }
        }
    } 
	
	
	public static FileOutputStream createFileOutStream(String filePath){
		//String filename = "D:\\out.txt";
        FileOutputStream fos = null;
        try {
        	File file=new File(filePath);
        	if(!file.exists()){
        		LogCvt.info("�ļ������ڣ���ʼ����......");
        		file.createNewFile();
        	}
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fos;
	}
	
	public static void CloseFileOutStream(FileOutputStream fos){
		try {
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		FileOutputStream fos=WriteFileStream.createFileOutStream("D:\\out.RESP");
		FileChannel channel = fos.getChannel();
		StringBuilder sb=new StringBuilder();
		  try {
			    sb.append("=======================ʧ���ֻ�����=====================\n");
			    channel.write(Charset.forName(Contans.CHARTSET).encode(sb.toString()));
			    for (int i = 0; i < 3; i++) {
			    	    ByteBuffer src = Charset.forName(Contans.CHARTSET).encode("13631238895\n");
				        LogCvt.info("��ʼ��������limit��" + src.capacity() + "," + src.limit());
				        int length = 0;  
						while ((length = channel.write(src)) != 0) {
							    LogCvt.info("д�볤��:" + length);
						}
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			WriteFileStream.CloseFileOutStream(fos);
		}
	}
}
