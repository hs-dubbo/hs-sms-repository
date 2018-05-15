/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.after.model.utils.mq.activitMq.customer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.after.model.utils.LogCvt;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年5月10日 <br>
 * 描述：
 */
public class Subscriber {

	//ActiveMq 的默认用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //ActiveMq 的默认登录密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //ActiveMQ 的链接地址
    private static final String BROKEN_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    
    ConnectionFactory connectionFactory=null;
    
    Connection connection=null;
    
    Session session=null;
    
    MessageConsumer messageConsumer=null;
    
    Destination destination=null;
    public void init(){
    	try {
    		connectionFactory=new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEN_URL);
			connection=connectionFactory.createConnection();
			connection.start();
			session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination=session.createTopic("Topic_001");
			messageConsumer=session.createConsumer(destination);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    class MyLister implements MessageListener{
		@Override
		public void onMessage(Message message) {
			// TODO Auto-generated method stub
			try {
			if(message instanceof MapMessage){
				MapMessage mapMessage=(MapMessage)message;
				LogCvt.info("接收订阅信息"+mapMessage.toString());
				message.acknowledge();
			  }
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    
    public void revice(){
    	try {
			messageConsumer.setMessageListener(new MyLister());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) {
		Subscriber subscriber=new Subscriber();
		subscriber.init();
		subscriber.revice();
	}
}
