package com.pi.restful_library.Services;

import com.pi.restful_library.model.Books;
import com.pi.restful_library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Books> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Books addBook(Books books) {
        return bookRepository.save(books);
    }

    public Books updateBook(Long id, Books booksDetails) {
        Books books = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Books not found"));
        books.setTitle(booksDetails.getTitle());
        books.setAuthor(booksDetails.getAuthor());
        books.setGenre(booksDetails.getGenre());
        books.setCopiesAvailable(booksDetails.getCopiesAvailable());
        return bookRepository.save(books);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Books borrowBook(Long bookId) {
        Books books = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Books not found"));
        if (books.getCopiesAvailable() > 0) {
            books.setCopiesAvailable(books.getCopiesAvailable() - 1);
            return bookRepository.save(books);
        } else {
            throw new RuntimeException("No copies available");
        }
    }

    public Books returnBook(Long bookId) {
        Books books = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Books not found"));
        books.setCopiesAvailable(books.getCopiesAvailable() + 1);
        return bookRepository.save(books);
    }

}