package com.tezzad.kosync.document.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tezzad.kosync.document.entity.BookEntity;
import com.tezzad.kosync.document.entity.BookEntityKey;

public interface BookRepository extends JpaRepository<BookEntity, BookEntityKey> {

    List<BookEntity> findByUsername(String username);
    List<BookEntity> findByDocument(String document);
    List<BookEntity> findByUsernameAndDocument(String username, String document);

}
