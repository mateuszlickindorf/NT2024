package com.mateusz.library.library.controller.book;

import com.mateusz.library.library.controller.dto.book.CreateBookDto;
import com.mateusz.library.library.controller.dto.book.CreateBookResponseDto;
import com.mateusz.library.library.controller.dto.book.EditBookDto;
import com.mateusz.library.library.controller.dto.book.GetBookDto;
import com.mateusz.library.library.service.book.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@PreAuthorize("hasRole('ADMIN')")
@Tag(name = "Book")
@CrossOrigin
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    @ResponseStatus(code=HttpStatus.CREATED)
    public ResponseEntity<CreateBookResponseDto> addBook(@RequestBody CreateBookDto bookDto) {
        var newBook = bookService.create(bookDto);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_READER')")
    public GetBookDto getOne(@PathVariable long id) {
        return bookService.getOne(id);
    }


    @GetMapping("/get")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_READER')")
    public List<GetBookDto> getAllBooks() {
        return bookService.getAll();
    }

    @PatchMapping("/edit")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<GetBookDto> editBook(@RequestBody EditBookDto bookDto) {
        var bookEdited = bookService.editBook(bookDto);
        return new ResponseEntity<>(bookEdited, HttpStatus.OK);
    }

}