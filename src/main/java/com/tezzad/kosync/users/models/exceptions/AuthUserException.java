package com.tezzad.kosync.users.models.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserException extends Exception {

    public AuthUserException(String username){
        this.username = username;
    }

    private String username;

}
