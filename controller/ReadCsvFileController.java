package com.example.readcsvfile.controller;

import com.example.readcsvfile.entity.ReadCsvFile;
import com.example.readcsvfile.helper.CsvHelper;
import com.example.readcsvfile.message.ResponseMessage;
import com.example.readcsvfile.service.ReadCsvFileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/csv")
public class ReadCsvFileController {

    @Autowired
    private ReadCsvFileServiceImpl readCsvFileServiceImpl;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        if (CsvHelper.hasCSVFormat(file)) {
            try {
                readCsvFileServiceImpl.save(file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping("/read")
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
