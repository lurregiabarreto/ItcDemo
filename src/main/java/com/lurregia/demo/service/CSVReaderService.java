package com.lurregia.demo.service;

import com.lurregia.demo.model.ITCRecord;
import com.lurregia.demo.model.ProdutoCartao;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CSVReaderService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void processCsvFile(String filePath) throws IOException {
        try (CSVParser parser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withHeader())) {
            for (CSVRecord record : parser) {
                ITCRecord itcRecord = new ITCRecord();
                itcRecord.setTipoCartao(record.get("tipo_cartao"));
                itcRecord.setCredito(record.get("Crédito"));
                itcRecord.setDebito(record.get("Débito"));
                itcRecord.setPrePago(record.get("Pré pago"));
                itcRecord.setTipoPessoa(record.get("tipo_pessoa"));
                itcRecord.setCpCnpVbv(record.get("cp_cnp_vbv"));
                itcRecord.setMcc(record.get("mcc"));
                itcRecord.setProduto(record.get("produto"));
                itcRecord.setParcelamento(record.get("parcelamento"));
                itcRecord.setRecorrente(record.get("recorrente"));
                itcRecord.setTaxa(Double.parseDouble(record.get("taxa")));
                itcRecord.setTeto(Double.parseDouble(record.get("teto")));

                Map<String, Object> message = new HashMap<>();
                message.put("tipo_cartao", itcRecord.getTipoCartao());
                message.put("crédito", itcRecord.getCredito());
                message.put("débito", itcRecord.getDebito());
                message.put("pré_pago", itcRecord.getPrePago());
                message.put("tipo_pessoa", itcRecord.getTipoPessoa());
                message.put("cp_cnp_vbv", itcRecord.getCpCnpVbv());
                message.put("mcc", itcRecord.getMcc());
                message.put("produto", ProdutoCartao.valueOf(itcRecord.getProduto().toUpperCase().replace(" ", "_")).name());
                message.put("parcelamento", itcRecord.getParcelamento());
                message.put("recorrente", itcRecord.getRecorrente());
                message.put("taxa", itcRecord.getTaxa());
                message.put("teto", itcRecord.getTeto());

                jmsTemplate.convertAndSend("CAPTURA.INTEGRADA.QUEUE", message);
            }
        }
    }
}
