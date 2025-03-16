package com.pi.restful_library.Controllers;


import com.pi.restful_library.Services.BorrowingRecordService;
import com.pi.restful_library.model.BorrowingRecords;
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
    public List<BorrowingRecords> getAllBorrowingRecords() {
        return borrowingRecordService.getAllBorrowingRecords();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BorrowingRecords> getBorrowingRecordById(@PathVariable Long id) {
        return borrowingRecordService.getBorrowingRecordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BorrowingRecords addBorrowingRecord(@RequestBody BorrowingRecords borrowingRecords) {
        return borrowingRecordService.addBorrowingRecord(borrowingRecords);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BorrowingRecords> updateBorrowingRecord(@PathVariable Long id, @RequestBody BorrowingRecords borrowingRecordsDetails) {
        return ResponseEntity.ok(borrowingRecordService.updateBorrowingRecord(id, borrowingRecordsDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrowingRecord(@PathVariable Long id) {
        borrowingRecordService.deleteBorrowingRecord(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/borrow")
    public BorrowingRecords borrowBook(@RequestParam Long bookId, @RequestParam Long memberId) {
        return borrowingRecordService.borrowBook(bookId, memberId);
    }

    @PostMapping("/return/{id}")
    public BorrowingRecords returnBook(@PathVariable Long id) {
        return borrowingRecordService.returnBook(id);
    }

}