package com.mateusz.library.library.controller.dto.loan;

import com.mateusz.library.library.infrastructure.entity.BookEntity;
import com.mateusz.library.library.infrastructure.entity.UserEntity;

import java.sql.Date;

public class UpdateLoanResponseDto {

    private Long id;
    private BookEntity book;
    private UserEntity user;
    private Date loanDate;
    private Date endDate;
    private Date dueDate;

    public UpdateLoanResponseDto(Long id, BookEntity book, UserEntity user, Date loanDate, Date endDate, Date dueDate) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.loanDate = loanDate;
        this.endDate = endDate;
        this.dueDate = dueDate;
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
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date returnDate) {
        this.dueDate = dueDate;
    }
}
