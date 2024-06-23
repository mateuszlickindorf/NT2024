package com.mateusz.library.library.controller.dto.loan;

import jakarta.validation.constraints.NotBlank;

import java.sql.Date;

public class UpdateLoanDto {
    @NotBlank(message = "Identification number of a loan is required!")
    private Long id;
    @NotBlank(message = "Return date is required!")
    private Date dueDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date returnDate) {
        this.dueDate = dueDate;
    }

    public UpdateLoanDto(Long id, Date dueDate) {
        this.id = id;
        this.dueDate = dueDate;
    }

    public UpdateLoanDto() {
    }
}
