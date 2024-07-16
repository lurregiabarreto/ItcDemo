package com.lurregia.demo.config;


import com.ibm.mq.jms.MQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
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

    @Bean
    public CamelContext camelContext() throws JMSException {
        CamelContext camelContext = new DefaultCamelContext();
        return camelContext;
    }

    @Bean
    public ConnectionFactory jmsConnectionFactory() throws JMSException {
        MQConnectionFactory factory = new MQConnectionFactory();
        factory.setQueueManager(queueManager);
        factory.setChannel(channel);
        factory.setConnectionNameList(connName);

        return factory;
    }

    @Bean
    public JmsComponent jmsComponent(CamelContext camelContext, ConnectionFactory jmsConnectionFactory) {
        JmsComponent jmsComponent = JmsComponent.jmsComponentAutoAcknowledge(jmsConnectionFactory);
        camelContext.addComponent("jms", jmsComponent);
        return jmsComponent;
    }

    @Bean
    public JmsTemplate jmsTemplate(ConnectionFactory jmsConnectionFactory) {
        return new JmsTemplate(jmsConnectionFactory);
    }

}
