package com.mateusz.library.library.controller.loan;

import com.mateusz.library.library.infrastructure.entity.LoanEntity;
import com.mateusz.library.library.service.loan.LoanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_READER')")
@Tag(name = "Loan")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<LoanEntity> create(@RequestBody LoanEntity loan) {
        var newLoan = loanService.create(loan);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }
}
