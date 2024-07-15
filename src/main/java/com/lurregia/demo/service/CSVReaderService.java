package com.lurregia.demo.service;

import com.lurregia.demo.model.ITCRecord;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVReaderService {

    public List<ITCRecord> readCSV(String filePath) throws IOException {
        List<ITCRecord> itcRecords = new ArrayList<>();
        try (CSVParser parser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT)) {
            for (CSVRecord record : parser) {
                ITCRecord itcRecord = new ITCRecord();
                itcRecord.setTipoCartao(record.get("tipo_cartao"));
                itcRecord.setTipoPessoa(record.get("tipo_pessoa"));
                itcRecord.setCpCnpVbv(record.get("cp_cnp_vbv"));
                itcRecord.setMcc(record.get("mcc"));
                itcRecord.setProduto(record.get("produto"));
                itcRecord.setParcelamento(record.get("parcelamento"));
                itcRecord.setTaxa(Double.parseDouble(record.get("taxa")));
                itcRecord.setTeto(Double.parseDouble(record.get("teto")));
                itcRecords.add(itcRecord);
            }
        }
        return itcRecords;
    }
}
