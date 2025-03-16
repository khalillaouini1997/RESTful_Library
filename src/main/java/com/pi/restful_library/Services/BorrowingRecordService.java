package com.pi.restful_library.Services;


import com.pi.restful_library.model.Books;
import com.pi.restful_library.model.BorrowingRecords;
import com.pi.restful_library.model.Members;
import com.pi.restful_library.repository.BorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingRecordService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private BookService bookService;

    public List<BorrowingRecords> getAllBorrowingRecords() {
        return borrowingRecordRepository.findAll();
    }

    public Optional<BorrowingRecords> getBorrowingRecordById(Long id) {
        return borrowingRecordRepository.findById(id);
    }

    public BorrowingRecords addBorrowingRecord(BorrowingRecords borrowingRecords) {
        return borrowingRecordRepository.save(borrowingRecords);
    }

    public BorrowingRecords updateBorrowingRecord(Long id, BorrowingRecords borrowingRecordsDetails) {
        BorrowingRecords borrowingRecords = borrowingRecordRepository.findById(id).orElseThrow(() -> new RuntimeException("BorrowingRecords not found"));
        borrowingRecords.setBooks(borrowingRecordsDetails.getBooks());
        borrowingRecords.setMembers(borrowingRecordsDetails.getMembers());
        borrowingRecords.setBorrowDate(borrowingRecordsDetails.getBorrowDate());
        borrowingRecords.setReturnDate(borrowingRecordsDetails.getReturnDate());
        borrowingRecords.setFineAmount(borrowingRecordsDetails.getFineAmount());
        return borrowingRecordRepository.save(borrowingRecords);
    }

    public void deleteBorrowingRecord(Long id) {
        borrowingRecordRepository.deleteById(id);
    }

    public BorrowingRecords borrowBook(Long bookId, Long memberId) {
        Books books = bookService.borrowBook(bookId);
        Members members = memberService.getMemberById(memberId).orElseThrow(() -> new RuntimeException("Members not found"));

        BorrowingRecords borrowingRecords = new BorrowingRecords();
        borrowingRecords.setBooks(books);
        borrowingRecords.setMembers(members);
        borrowingRecords.setBorrowDate(new Date());
        return borrowingRecordRepository.save(borrowingRecords);
    }

    public BorrowingRecords returnBook(Long borrowingRecordId) {
        BorrowingRecords borrowingRecords = borrowingRecordRepository.findById(borrowingRecordId)
                .orElseThrow(() -> new RuntimeException("BorrowingRecords not found"));
        bookService.returnBook(borrowingRecords.getBooks().getId());
        borrowingRecords.setReturnDate(new Date());
        return borrowingRecordRepository.save(borrowingRecords);
    }
}