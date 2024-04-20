package com.mateusz.library.library.infrastructure.repository;

import com.mateusz.library.library.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
