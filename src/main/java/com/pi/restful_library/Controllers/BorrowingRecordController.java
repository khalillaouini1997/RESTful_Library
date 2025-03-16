package com.pi.restful_library.Controllers;


import com.pi.restful_library.Services.BorrowingRecordService;
import com.pi.restful_library.model.BorrowingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowing-records")
public class BorrowingRecordController {

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @GetMapping
    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingRecordService.getAllBorrowingRecords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingRecord> getBorrowingRecordById(@PathVariable Long id) {
        return borrowingRecordService.getBorrowingRecordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BorrowingRecord addBorrowingRecord(@RequestBody BorrowingRecord borrowingRecord) {
        return borrowingRecordService.addBorrowingRecord(borrowingRecord);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowingRecord> updateBorrowingRecord(@PathVariable Long id, @RequestBody BorrowingRecord borrowingRecordDetails) {
        return ResponseEntity.ok(borrowingRecordService.updateBorrowingRecord(id, borrowingRecordDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowingRecord(@PathVariable Long id) {
        borrowingRecordService.deleteBorrowingRecord(id);
        return ResponseEntity.noContent().build();
    }
}