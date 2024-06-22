package com.mateusz.library.library.infrastructure.repository;

import com.mateusz.library.library.infrastructure.entity.AuthEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends CrudRepository<AuthEntity, Long> {
    Optional<AuthEntity> findByUsername(String username);
    Optional<AuthEntity> findByUserId(long userId);
    void deleteByUserId(long userId);
}