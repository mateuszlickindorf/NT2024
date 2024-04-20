package com.mateusz.library.library.infrastructure.repository;

import com.mateusz.library.library.infrastructure.entity.BookEntity;
import com.mateusz.library.library.infrastructure.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
}