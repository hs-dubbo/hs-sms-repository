/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.after.model.utils.mq.activitMq.productor;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年5月10日 <br>
 * 描述：
 */
public class Publisher {
	//ActiveMq 的默认用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //ActiveMq 的默认登录密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //ActiveMQ 的链接地址
    private static final String BROKEN_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    
    ConnectionFactory connectionFactory=null;
    
    Connection connection=null;
    
    Session session=null;
    
    MessageProducer messageProducer=null;
    public void init(){
    	try {
			connectionFactory=new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEN_URL);
			connection=connectionFactory.createConnection();
			connection.start();
			session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void sendMsg(){
    	try {
			Destination destination=session.createTopic("Topic_001");
			messageProducer=session.createProducer(destination);
			TextMessage message=session.createTextMessage("发布-订阅模式");
			messageProducer.send(destination, message);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) {
		Publisher publisher=new Publisher();
		publisher.init();
		publisher.sendMsg();
	}
}
