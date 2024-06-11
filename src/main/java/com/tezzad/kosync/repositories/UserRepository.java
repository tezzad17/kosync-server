package com.tezzad.kosync.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tezzad.kosync.users.entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsUserEntityByUsername(String username);

    List<UserEntity> findByUsername(String username);

}
