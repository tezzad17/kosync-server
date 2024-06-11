package com.tezzad.kosync.document.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(BookEntityKey.class)
public class BookEntity {

    @Id
    private String username;

    @Id
    private String document;

    private String progress;
    private String percentage;
    private String device;
    private String deviceId;
    private Date timestamp;
}
