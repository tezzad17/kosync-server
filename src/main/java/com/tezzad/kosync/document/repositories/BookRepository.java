package com.tezzad.kosync.document.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tezzad.kosync.document.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByUsername(String username);
    List<BookEntity> findByDocument(String document);
    List<BookEntity> findByUsernameAndDocument(String username, String document);

}
