/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
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
 * 作者：Administrator <br>
 * 创建时间：2018年4月13日 <br>
 * 描述：
 */
public class WriteFileStream {

	@Deprecated
	public static void writeNio() {
        String filename = "D:\\out.txt";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(filename));
            FileChannel channel = fos.getChannel();
            ByteBuffer src = Charset.forName("utf8").encode("你好你好你好你好你好");
            LogCvt.info("初始化容量和limit：" + src.capacity() + "," + src.limit());
            int length = 0;  
            while ((length = channel.write(src)) != 0) {
                /*  
                 * 注意，这里不需要clear，将缓冲中的数据写入到通道中后 第二次接着上一次的顺序往下读  
                 */  
                LogCvt.info("写入长度:" + length);
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
        		LogCvt.info("文件不存在，开始创建......");
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
			    sb.append("=======================失败手机号码=====================\n");
			    channel.write(Charset.forName(Contans.CHARTSET).encode(sb.toString()));
			    for (int i = 0; i < 3; i++) {
			    	    ByteBuffer src = Charset.forName(Contans.CHARTSET).encode("13631238895\n");
				        LogCvt.info("初始化容量和limit：" + src.capacity() + "," + src.limit());
				        int length = 0;  
						while ((length = channel.write(src)) != 0) {
							    LogCvt.info("写入长度:" + length);
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
