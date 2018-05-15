/**
 * Copyright www.hoomsun.com 红上金融信息服务（上海）有限公司
 */
package com.after.model.utils.mq.activitMq.customer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import com.after.model.utils.LogCvt;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年5月10日 <br>
 * 描述：
 */
public class MqCustomer {

	//ActiveMq 的默认用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //ActiveMq 的默认登录密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //ActiveMQ 的链接地址
    private static final String BROKEN_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    
    ConnectionFactory connectionFactory=null;
    
    Connection connection=null;
    
    Session session=null;
    
    ThreadLocal<MessageConsumer> threadLocal = new ThreadLocal<>();
    
    public void init(){
    	try {
    		connectionFactory=new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKEN_URL);
			connection=connectionFactory.createConnection();
			connection.start();
			session=connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	public void getMessage(String disName){
    	try {
			Thread.sleep(1000);
			Queue queue=session.createQueue(disName);
			MessageConsumer messageConsumer=null;
			if(threadLocal.get()!=null){
				messageConsumer=threadLocal.get();
			}else{
				messageConsumer=session.createConsumer(queue);
				threadLocal.set(messageConsumer);
			}
			while(true){
				Thread.sleep(1000);
				TextMessage message=(TextMessage)messageConsumer.receive();
				if(message!=null){
					message.acknowledge();
					LogCvt.info("消费端接收信息"+message.getText());
					break;
				}else{
					break;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
	public static void main(String[] args) {
		MqCustomer customer=new MqCustomer();
		customer.init();
		customer.getMessage("xuxinyuan");
	}
}
