package com.tezzad.kosync.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tezzad.kosync.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsUserEntityByUsername(String username);

}
