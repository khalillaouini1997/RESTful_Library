package com.pi.restful_library.Services;


import com.pi.restful_library.model.BorrowingRecord;
import com.pi.restful_library.repository.BorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingRecordRepository.findAll();
    }

    public Optional<BorrowingRecord> getBorrowingRecordById(Long id) {
        return borrowingRecordRepository.findById(id);
    }

    public BorrowingRecord addBorrowingRecord(BorrowingRecord borrowingRecord) {
        return borrowingRecordRepository.save(borrowingRecord);
    }

    public BorrowingRecord updateBorrowingRecord(Long id, BorrowingRecord borrowingRecordDetails) {
        BorrowingRecord borrowingRecord = borrowingRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("BorrowingRecord not found"));
        borrowingRecord.setBook(borrowingRecordDetails.getBook());
        borrowingRecord.setMember(borrowingRecordDetails.getMember());
        borrowingRecord.setBorrowDate(borrowingRecordDetails.getBorrowDate());
        borrowingRecord.setReturnDate(borrowingRecordDetails.getReturnDate());
        borrowingRecord.setFineAmount(borrowingRecordDetails.getFineAmount());
        return borrowingRecordRepository.save(borrowingRecord);
    }

    public void deleteBorrowingRecord(Long id) {
        borrowingRecordRepository.deleteById(id);
    }
}