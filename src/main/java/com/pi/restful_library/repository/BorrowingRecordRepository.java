package com.pi.restful_library.repository;

import com.pi.restful_library.model.BorrowingRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecords, Long> {
}