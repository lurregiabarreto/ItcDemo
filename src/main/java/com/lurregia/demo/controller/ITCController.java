package com.lurregia.demo.controller;


import com.lurregia.demo.service.CSVReaderService;
import com.lurregia.demo.service.MQSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ITCController {

    @Autowired
    private CSVReaderService csvReaderService;

    @Autowired
    private MQSenderService mqSenderService;

    @GetMapping("/process-itc")
    public String processITC(@RequestParam String filePath) {
        try {
            csvReaderService.processCsvFile(filePath);
            return "CSV processed and messages sent to queue.";
        } catch (IOException e) {
            return "Failed to process CSV: " + e.getMessage();
        }
    }
}
