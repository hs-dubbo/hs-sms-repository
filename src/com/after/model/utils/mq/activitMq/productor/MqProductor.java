/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.after.model.utils.mq.activitMq.productor;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年5月10日 <br>
 * 描述：
 */
public class MqProductor {

	//ActiveMq 的默认用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //ActiveMq 的默认登录密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //ActiveMQ 的链接地址
    private static final String BROKEN_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    
    ConnectionFactory connectionFactory=null;
    
    Connection connection=null;
    
    Session session=null;
    
    ThreadLocal<MessageProducer> threadLocal = new ThreadLocal<>();
    
    public void init(){
    	try {
    		connectionFactory=new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEN_URL);
			connection=connectionFactory.createConnection();
			connection.start();
			session=connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void sendMsg(String msg){
    	try {
			Queue queue= session.createQueue(msg);
			MessageProducer messageProducer=null;
			if(threadLocal.get()!=null){
				messageProducer=threadLocal.get();
			}else{
				messageProducer=session.createProducer(queue);
				threadLocal.set(messageProducer);
			}
			
			while(true){
				Thread.sleep(1000);
				String msg1=Thread.currentThread().getName()+"   "+msg;
				TextMessage textMessage=session.createTextMessage(msg1);
				messageProducer.send(textMessage);
				session.commit();
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) {
    	MqProductor mqProductor=new MqProductor();
    	mqProductor.init();
    	mqProductor.sendMsg("xuxinyuan");
	}
}
