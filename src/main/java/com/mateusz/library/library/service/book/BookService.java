package com.mateusz.library.library.service.book;

import com.mateusz.library.library.controller.dto.book.CreateBookDto;
import com.mateusz.library.library.controller.dto.book.CreateBookResponseDto;
import com.mateusz.library.library.controller.dto.book.EditBookDto;
import com.mateusz.library.library.controller.dto.book.GetBookDto;
import com.mateusz.library.library.exceptions.BookInvalidNumber;
import com.mateusz.library.library.exceptions.BookNotFound;
import com.mateusz.library.library.exceptions.ReturnPending;
import com.mateusz.library.library.infrastructure.entity.BookEntity;
import com.mateusz.library.library.infrastructure.entity.LoanEntity;
import com.mateusz.library.library.infrastructure.repository.BookRepository;
import com.mateusz.library.library.infrastructure.repository.LoanRepository;
import com.mateusz.library.library.infrastructure.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final ReviewRepository reviewRepository;


    @Autowired
    public BookService(BookRepository bookRepository, LoanRepository loanRepository, ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<GetBookDto> getAll(){
        ArrayList<BookEntity> books = (ArrayList<BookEntity>) bookRepository.findAll();
        return books.stream().map(this::mapBook).collect(Collectors.toList());
    }

    public GetBookDto getById(long id){
        var book = bookRepository.findById(id).orElseThrow(() -> BookNotFound.create(id));
        return mapBook(book);
    }

    @Transactional
    public CreateBookResponseDto create(CreateBookDto book){
        var bookEntity = new BookEntity();
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setIsbn(book.getIsbn());
        bookEntity.setPublisher(book.getPublisher());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAvailableCopies(book.getAvailableCopies());
        bookEntity.setPublicationYear(book.getPublicationYear());
        var newBook = bookRepository.save(bookEntity);
        return new CreateBookResponseDto(newBook.getId(), newBook.getIsbn(), newBook.getTitle(), newBook.getAuthor(), newBook.getPublisher(), newBook.getPublication_year(), newBook.getAvailableCopies());
    }

    @Transactional
    public void deleteById(long id) {
        if (!bookRepository.existsById(id)) {
            throw BookNotFound.create(id);
        }
        boolean noPendingReturns = false;
        if (!loanRepository.findById(id).isEmpty()) {
            List<LoanEntity> loans = loanRepository.findByBookId(id);
            for (LoanEntity loan : loans) {
                if (loan.getReturnDate() != null) {
                    noPendingReturns = true;
                } else {
                    throw ReturnPending.create(loan.getBook().getTitle());
                }
            }
        } else {
            if (!reviewRepository.findById(id).isEmpty()) {
                bookRepository.deleteById(id);
            } else {
                reviewRepository.deleteById(id);
                bookRepository.deleteById(id);
            }
        }
        if (noPendingReturns) {
            loanRepository.deleteById(id);
            reviewRepository.deleteById(id);
            bookRepository.deleteById(id);
        }
    }
    public GetBookDto editBook(EditBookDto bookDTO) {
        BookEntity book = bookRepository.findById(bookDTO.getId()).orElseThrow(() -> BookNotFound.create(bookDTO.getId()));
        if (bookDTO.getAvailableCopies() < 0) throw BookInvalidNumber.create();
        book.setIsbn(bookDTO.getIsbn());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(book.getAuthor());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setPublisher(bookDTO.getPublisher());
        book.setAvailableCopies(bookDTO.getAvailableCopies());
        var newBook = bookRepository.save(book);
        return mapBook(newBook);
    }

    private GetBookDto mapBook(BookEntity book) {
        return new GetBookDto(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublication_year(), book.getAvailableCopies() > 0);
    }
}


