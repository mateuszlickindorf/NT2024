package com.mateusz.library.library.infrastructure.repository;

import com.mateusz.library.library.infrastructure.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
