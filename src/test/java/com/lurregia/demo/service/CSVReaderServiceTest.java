package com.lurregia.demo;


import com.lurregia.demo.service.CSVReaderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CSVReaderServiceTest {

    @Autowired
    private CSVReaderService csvReaderService;

    @Autowired
    private JmsTemplate jmsTemplate;

    @BeforeEach
    public void setup() {
        // Configurações adicionais, se necessário
    }

    @Test
    public void testProcessCsvFile() throws IOException {
        String filePath = "src/test/resources/test.csv";
        csvReaderService.processCsvFile(filePath);

        Map<String, Object> message = (Map<String, Object>) jmsTemplate.receiveAndConvert("CAPTURA.INTEGRADA.QUEUE");
        assertNotNull(message);
        // Verifique os campos da mensagem conforme necessário
    }
}


