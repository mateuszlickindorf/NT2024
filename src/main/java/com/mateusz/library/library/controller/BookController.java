package com.mateusz.library.library.controller;

import com.mateusz.library.library.infrastructure.entity.BookEntity;
import com.mateusz.library.library.infrastructure.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookEntity> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public BookEntity getOne(@PathVariable long id) {
        return bookService.getOne(id);
    }

    @PostMapping
    public BookEntity create(@RequestBody BookEntity book) {
        return bookService.create(book);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        bookService.delete(id);
    }
}
