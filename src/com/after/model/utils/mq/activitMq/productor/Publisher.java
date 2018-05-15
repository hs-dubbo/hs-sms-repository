/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
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
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��5��10�� <br>
 * ������
 */
public class Publisher {
	//ActiveMq ��Ĭ���û���
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //ActiveMq ��Ĭ�ϵ�¼����
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //ActiveMQ �����ӵ�ַ
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
			TextMessage message=session.createTextMessage("����-����ģʽ");
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
