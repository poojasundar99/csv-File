package com.example.readcsvfile.service;

import com.example.readcsvfile.entity.ReadCsvFile;
import com.example.readcsvfile.repository.ReadCsvFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import com.example.readcsvfile.helper.CsvHelper;
import java.util.List;

@Service
public class ReadCsvFileServiceImpl {

    @Autowired
    private ReadCsvFileRepo readCsvFileRepo;

    public void save(MultipartFile file) {
        try {
            List<ReadCsvFile> readCsvFiles = CsvHelper.readCsvFiles(file.getInputStream());
            readCsvFileRepo.saveAll(readCsvFiles);
        } catch (IOException e) {
            throw new RuntimeException("Fail to store csv data: " + e.getMessage());
        }
    }
    public List<ReadCsvFile> getAllReadCsvFiles() {return readCsvFileRepo.findAll();
    }

}

