package com.example.readcsvfile.repository;

import com.example.readcsvfile.entity.ReadCsvFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadCsvFileRepo extends JpaRepository<ReadCsvFile, Long> {
}
