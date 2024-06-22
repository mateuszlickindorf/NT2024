package com.mateusz.library.library.controller.dto.loan;

import com.mateusz.library.library.infrastructure.entity.BookEntity;
import com.mateusz.library.library.infrastructure.entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Date;

public class CreateLoanResponseDto {
    @Schema(name = "id", example = "2")
    private Long id;
    @Schema(name = "book")
    private BookEntity book;
    @Schema(name = "user")
    private UserEntity user;
    @Schema(name = "startDate", example = "2024-06-06")
    private Date startDate;
    @Schema(name = "endDate", example = "2025-05-04")
    private Date endDate;

    public CreateLoanResponseDto(Long id, BookEntity book, UserEntity user, Date startDate, Date endDate) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Date getLoanDate() {
        return startDate;
    }

    public void setLoanDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return endDate;
    }

    public void setDueDate(Date endDate) {
        this.endDate = endDate;
    }
}