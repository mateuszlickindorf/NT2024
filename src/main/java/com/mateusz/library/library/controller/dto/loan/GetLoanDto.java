package com.mateusz.library.library.controller.dto.loan;

import com.mateusz.library.library.controller.dto.book.GetBookDto;
import com.mateusz.library.library.controller.dto.user.GetUserSimplifiedDto;
import io.swagger.v3.oas.annotations.media.Schema;


import java.sql.Date;

public class GetLoanDto {
    @Schema(name = "id", example = "1")
    private Long id;
    @Schema(name = "book")
    private GetBookDto book;
    @Schema(name = "userSimplified")
    private GetUserSimplifiedDto user;
    @Schema(name = "startDate", example = "2024-06-06")
    private Date startDate;
    @Schema(name = "endDate", example = "2025-05-04")
    private Date endDate;
    @Schema(name = "wasReturned", example = "true")
    private boolean wasReturned;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GetBookDto getBook() {
        return book;
    }

    public void setBook(GetBookDto book) {
        this.book = book;
    }

    public GetUserSimplifiedDto getUser() {
        return user;
    }

    public void setUser(GetUserSimplifiedDto user) {
        this.user = user;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isWasReturned() {
        return wasReturned;
    }

    public void setWasReturned(boolean wasReturned) {
        this.wasReturned = wasReturned;
    }

    public GetLoanDto() {
    }

    public GetLoanDto(Long id, GetBookDto book, GetUserSimplifiedDto user, Date startDate, Date endDate, boolean wasReturned) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.wasReturned = wasReturned;
    }
}
