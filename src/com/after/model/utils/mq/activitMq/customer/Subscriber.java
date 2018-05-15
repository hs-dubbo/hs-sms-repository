/**
 * Copyright www.hoomsun.com ���Ͻ�����Ϣ�����Ϻ������޹�˾
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
 * ���ߣ�Administrator <br>
 * ����ʱ�䣺2018��5��10�� <br>
 * ������
 */
public class Subscriber {

	//ActiveMq ��Ĭ���û���
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //ActiveMq ��Ĭ�ϵ�¼����
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //ActiveMQ �����ӵ�ַ
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
				LogCvt.info("���ն�����Ϣ"+mapMessage.toString());
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
