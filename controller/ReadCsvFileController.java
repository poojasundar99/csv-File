package com.example.readcsvfile.controller;

import com.example.readcsvfile.entity.ReadCsvFile;
import com.example.readcsvfile.service.ReadCsvFileService;
import com.example.readcsvfile.service.ReadCsvFileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/csv")
public class ReadCsvFileController {

    @Autowired
    private ReadCsvFileServiceImpl readCsvFileServiceImpl;

    @GetMapping("/read-csv")
    public ResponseEntity<List<ReadCsvFile>> getAllReadFields() {
        try {
            List<ReadCsvFile> tutorials = readCsvFileServiceImpl.getAllReadCsvFiles();
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
