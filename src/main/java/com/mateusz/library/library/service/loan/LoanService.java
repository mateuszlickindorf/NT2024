package com.mateusz.library.library.service.loan;

import com.mateusz.library.library.controller.dto.book.GetBookDto;
import com.mateusz.library.library.controller.dto.loan.*;
import com.mateusz.library.library.controller.dto.user.GetUserSimplifiedDto;
import com.mateusz.library.library.exceptions.BookNotAvailable;
import com.mateusz.library.library.exceptions.BookNotFound;
import com.mateusz.library.library.exceptions.LoanNotFound;
import com.mateusz.library.library.exceptions.UserNotFoundForId;
import com.mateusz.library.library.infrastructure.entity.BookEntity;
import com.mateusz.library.library.infrastructure.entity.LoanEntity;
import com.mateusz.library.library.infrastructure.entity.UserEntity;
import com.mateusz.library.library.infrastructure.repository.BookRepository;
import com.mateusz.library.library.infrastructure.repository.LoanRepository;
import com.mateusz.library.library.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<GetLoanDto> getAll(Long userId) {
        List<LoanEntity> loans;
        if (userId == null) {
            loans = (List<LoanEntity>) loanRepository.findAll();
        } else {
            loans = loanRepository.findByUserId(userId);
        }
        return loans.stream().map(this::mapLoan).collect(Collectors.toList());
    }

    public GetLoanDto getById(long id) {
        LoanEntity loanEntity = loanRepository.findById(id).orElseThrow(() -> LoanNotFound.create(id));
        return mapLoan(loanEntity);
    }

    @Transactional
    public CreateLoanResponseDto create(CreateLoanDto loanDto) {
        UserEntity user = userRepository.findById(loanDto.getUserId()).orElseThrow(() -> UserNotFoundForId.create(loanDto.getUserId()));
        BookEntity book = bookRepository.findById(loanDto.getBookId()).orElseThrow(() -> BookNotFound.create(loanDto.getBookId()));
        if (book.getAvailableCopies() == 0) throw BookNotAvailable.create(book.getTitle(), book.getIsbn());
        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setBook(book);
        loanEntity.setUser(user);
        loanEntity.setLoanDate(new Date(System.currentTimeMillis()));
        loanEntity.setDueDate(loanDto.getEndDate());
        var newLoan = loanRepository.save(loanEntity);
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
        return new CreateLoanResponseDto(newLoan.getId(), newLoan.getBook(), newLoan.getUser(), newLoan.getLoanDate(), newLoan.getDueDate());
    }

    public void deleteById (long id) {
        if (!loanRepository.existsById(id)) {
            throw LoanNotFound.create(id);
        }
        loanRepository.deleteById(id);
    }

    public UpdateLoanResponseDto update(UpdateLoanDto loanDto) {
        LoanEntity loan = loanRepository.findById(loanDto.getId()).orElseThrow(() -> LoanNotFound.create(loanDto.getId()));
        loan.setReturnDate(loanDto.getDueDate());
        loanRepository.save(loan);
        BookEntity book = bookRepository.findById(loan.getBook().getId()).orElseThrow(() -> BookNotFound.create(loan.getBook().getId()));
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);
        return new UpdateLoanResponseDto(loan.getId(), loan.getBook(), loan.getUser(), loan.getLoanDate(), loan.getDueDate(), loan.getReturnDate());
    }

    private GetLoanDto mapLoan(LoanEntity loan) {
        GetBookDto book = new GetBookDto(loan.getBook().getId(), loan.getBook().getIsbn(), loan.getBook().getTitle(), loan.getBook().getAuthor(), loan.getBook().getPublisher(), loan.getBook().getPublication_year(), loan.getBook().getAvailableCopies() > 0);
        GetUserSimplifiedDto user = new GetUserSimplifiedDto(loan.getUser().getId(), loan.getUser().getName(), loan.getUser().getEmail());
        return new GetLoanDto(loan.getId(), book, user, loan.getLoanDate(), loan.getDueDate(), loan.getReturnDate() != null);
    }
}