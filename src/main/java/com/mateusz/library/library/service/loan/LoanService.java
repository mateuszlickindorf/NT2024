package com.mateusz.library.library.service.loan;

import com.mateusz.library.library.infrastructure.entity.LoanEntity;
import com.mateusz.library.library.infrastructure.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public LoanEntity create(LoanEntity loan){
        return loanRepository.save(loan);
    }
}
