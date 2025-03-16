package com.pi.restful_library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class BorrowingRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Books books;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Members members;

    @Column(nullable = false)
    private Date borrowDate;

    private Date returnDate;

    private double fineAmount;
}