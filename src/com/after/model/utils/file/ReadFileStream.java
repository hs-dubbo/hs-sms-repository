/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
 */
package com.after.model.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import com.after.model.utils.Contans;

/**
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��4��13�� <br>
 * ������
 */
public class ReadFileStream {

	/**
	 * ���ߣ�Administrator <br>
	 * ����ʱ�䣺2018��4��13�� <br>
	 * ������ NIO��ȡ�ļ�
	 * @param pathname
	 */
	public static void readNIO(String pathname) {  
        Charset charset = Charset.forName("UTF-8");  
        CharsetDecoder decoder = charset.newDecoder();  
        FileInputStream fis = null;  
        try {  
            fis = new FileInputStream(pathname);  
            FileChannel fileChannel = fis.getChannel();  
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);  
            CharBuffer charBuffer = CharBuffer.allocate(1024);  
            int bytes = fileChannel.read(byteBuffer);  
            while(bytes!=-1){  
                byteBuffer.flip();  
                decoder.decode(byteBuffer, charBuffer, false);
                charBuffer.flip();  
                Contans.setModelMessValue(charBuffer.toString());
                charBuffer.clear();  
                byteBuffer.clear();  
                bytes = fileChannel.read(byteBuffer);  
            }  
            if(fis!=null){  
                fis.close();  
            }  
        } catch (FileNotFoundException e) {
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }
	
	public static void main(String[] args) {
		 String pathname = "D://out.txt";  
		ReadFileStream.readNIO(pathname);
		System.out.println(Contans.getModelMessValue(Contans.SMS_TEMPLATE));
	}
}
