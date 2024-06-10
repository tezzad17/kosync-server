package com.tezzad.kosync.models.exceptions;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserException extends Exception {

    public CreateUserException(String username){
        this.username = username;
    }

    private String username;

}
