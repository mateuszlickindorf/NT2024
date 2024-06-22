package com.mateusz.library.library.controller.dto.loan;

import java.sql.Date;

public class CreateLoanDto {

    private Long bookId;

    private Long userId;

    private Date endDate;

    public CreateLoanDto() {
    }

    public CreateLoanDto(Long bookId, Long userId, Date endDate) {
        this.bookId = bookId;
        this.userId = userId;
        this.endDate = endDate;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
