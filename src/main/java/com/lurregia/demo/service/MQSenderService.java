package com.lurregia.demo.service;


import com.lurregia.demo.model.ITCRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MQSenderService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendITCRecord(ITCRecord itcRecord) {
        jmsTemplate.convertAndSend("CAPTURA.INTEGRADA.QUEUE", itcRecord);
    }
}

