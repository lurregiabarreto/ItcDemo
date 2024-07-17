package com.lurregia.demo;

import com.lurregia.demo.model.ProdutoCartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class CsvProcessor {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${csv.file.path}")
    private String csvFilePath;

    public void processCsvFile() {
        try {
            // Leitura do arquivo CSV
            InputStream inputStream = getClass().getResourceAsStream(csvFilePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Processamento de cada linha do CSV
                String[] fields = line.split(",");
                String tipoCartao = fields[0];
                String credito = fields[1];
                String debito = fields[2];
                String prePago = fields[3];
                String tipoPessoa = fields[4];
                String cpCnpVbv = fields[5];
                String mcc = fields[6];
                String produto = fields[7];
                String parcelamento = fields[8];
                String recorrente = fields[9];
                String taxa = fields[10];
                String teto = fields[11];

                // Mapeamento do enum ProdutoCartao
                ProdutoCartao produtoCartao = ProdutoCartao.valueOf(produto.toUpperCase().replace(" ", "_"));

                // Construção do objeto com os campos necessários
                Map<String, Object> message = new HashMap<>();
                message.put("tipo_cartao", tipoCartao);
                message.put("crédito", credito);
                message.put("débito", debito);
                message.put("pré_pago", prePago);
                message.put("tipo_pessoa", tipoPessoa);
                message.put("cp_cnp_vbv", cpCnpVbv);
                message.put("mcc", mcc);
                message.put("produto", produtoCartao.name()); // Nome do produto Visa
                message.put("parcelamento", parcelamento);
                message.put("recorrente", recorrente);
                message.put("taxa", taxa);
                message.put("teto", teto);

                // Envio da mensagem para a fila do IBM MQ
                jmsTemplate.convertAndSend("queueName", message);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

