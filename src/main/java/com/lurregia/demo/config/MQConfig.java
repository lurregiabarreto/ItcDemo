package com.lurregia.demo.config;


import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.mq.jms.MQQueue;
import com.ibm.msg.client.wmq.WMQConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;

@Configuration
@EnableJms
public class MQConfig {

    @Value("${ibm.mq.queue-manager}")
    private String queueManager;

    @Value("${ibm.mq.channel}")
    private String channel;

    @Value("${ibm.mq.conn-name}")
    private String connName;

    @Value("${ibm.mq.user}")
    private String user;

    @Value("${ibm.mq.password}")
    private String password;

    @Value("${ibm.mq.queue}")
    private String queueName;

    @Bean
    public MQConnectionFactory mqConnectionFactory() throws JMSException {
        MQConnectionFactory mqConnectionFactory = new MQConnectionFactory();
        mqConnectionFactory.setQueueManager(queueManager);
        mqConnectionFactory.setChannel(channel);
        mqConnectionFactory.setConnectionNameList(connName);
        mqConnectionFactory.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "DemoApp");
        mqConnectionFactory.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, true);
        mqConnectionFactory.setStringProperty(WMQConstants.USERID, user);
        mqConnectionFactory.setStringProperty(WMQConstants.PASSWORD, password);
        return mqConnectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() throws JMSException {
        return new CachingConnectionFactory();
    }

    @Bean
    public JmsTemplate jmsTemplate() throws JMSException {
        return new JmsTemplate();
    }

    @Bean
    public MQQueue queue() throws JMSException {
        return new MQQueue(queueName);
    }
}
