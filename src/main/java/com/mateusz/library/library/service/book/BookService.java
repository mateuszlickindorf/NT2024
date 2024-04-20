package com.mateusz.library.library.service.book;

import com.mateusz.library.library.controller.auth.dto.CreateBookDto;
import com.mateusz.library.library.controller.auth.dto.CreateBookResponseDto;
import com.mateusz.library.library.controller.auth.dto.GetBookDto;
import com.mateusz.library.library.infrastructure.entity.BookEntity;
import com.mateusz.library.library.infrastructure.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<GetBookDto> getAll(){
        var books = bookRepository.findAll();
        return books.stream().map((book) -> new GetBookDto(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublication_year(), book.getAvailableCopies() > 0 )).collect(Collectors.toList());
    }

    public GetBookDto getOne(long id){
        var book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return new GetBookDto(book.getId(), book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublisher(), book.getPublication_year(), book.getAvailableCopies() > 0 );
    }

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

    public void delete(long id){
        if(!bookRepository.existsById(id)){
            throw new RuntimeException();
        }
        bookRepository.deleteById(id);
    }

}