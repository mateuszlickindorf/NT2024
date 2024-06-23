package com.mateusz.library.library.controller.loan;

import com.mateusz.library.library.controller.dto.loan.*;
import com.mateusz.library.library.infrastructure.entity.LoanEntity;
import com.mateusz.library.library.service.loan.LoanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_READER')")
@Tag(name = "Loan")
@CrossOrigin
public class LoanController {
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_READER')")
    @ResponseStatus(code = HttpStatus.CREATED)

    public ResponseEntity<CreateLoanResponseDto> addRental(@RequestBody @Validated CreateLoanDto loanDto) {
        var newRental = loanService.create(loanDto);
        return new ResponseEntity<>(newRental, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        loanService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_READER')")
    public ResponseEntity<GetLoanDto> getById(@PathVariable long id) {
        GetLoanDto getLoanDto = loanService.getById(id);
        return new ResponseEntity<>(getLoanDto, HttpStatus.OK);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAnyRole('ADMIN', 'READER')")
    public @ResponseBody ResponseEntity<List<GetLoanDto>> getAll(@RequestParam(required = false) Long userId) {
        List<GetLoanDto> getLoanDto = loanService.getAll(userId);
        return new ResponseEntity<>(getLoanDto, HttpStatus.OK);
    }

    @PatchMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<UpdateLoanResponseDto> update(@RequestBody UpdateLoanDto loanDto) {
        var loanUpdated = loanService.update(loanDto);
        return new ResponseEntity<>(loanUpdated, HttpStatus.OK);
    }
}













