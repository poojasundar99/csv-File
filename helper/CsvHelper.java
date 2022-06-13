package com.example.readcsvfile.helper;

import com.example.readcsvfile.entity.ReadCsvFile;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvHelper {
    public static String TYPE = "text/csv";
    static String[] HEADER = {"Id", "Name", "PhoneNumber", "Email"};

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<ReadCsvFile> csvToTutorials(InputStream is) {

        //  Create bufferedReader to convert the input stream into bufferedReader
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

             //CSVParser to convert the bufferedReader file into CSV format
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<ReadCsvFile> readCsvFiles = new ArrayList<ReadCsvFile>();

            // Iterate CSVRecords by Iterator with CsvParser.getRecords()
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            //     from each CSVRecord, use CSVRecord.get() to read and parse fields
            for (CSVRecord csvRecord : csvRecords) {
                ReadCsvFile readCsvFile = new ReadCsvFile(
                        Long.parseLong(csvRecord.get("Id")),
                        csvRecord.get("Name"),
                        Long.parseLong(csvRecord.get("PhoneNumber")),
                        csvRecord.get("Email"));
//                      Boolean.parseBoolean(csvRecord.get("Published"))

                readCsvFiles.add(readCsvFile);
            }
            return readCsvFiles;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        }
    }

}
