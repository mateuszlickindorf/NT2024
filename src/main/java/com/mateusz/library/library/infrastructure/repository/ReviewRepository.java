package com.mateusz.library.library.infrastructure.repository;

import com.mateusz.library.library.infrastructure.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByBookId(long bookId);
    List<ReviewEntity> findByUserId(long userId);
    void deleteByUserId(long userId);
    void deleteByBookId(long bookId);
}
