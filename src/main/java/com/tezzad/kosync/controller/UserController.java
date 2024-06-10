package com.tezzad.kosync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tezzad.kosync.business.UserService;
import com.tezzad.kosync.entity.UserEntity;
import com.tezzad.kosync.models.UserBean;
import com.tezzad.kosync.models.exceptions.CreateUserException;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody UserBean user) throws CreateUserException {
        System.out.println(user.getUsername() + " " + user.getPassword());

        UserEntity userResult = userService.createUser(user);

        return new ResponseEntity<String>(userResult.getUsername(), HttpStatus.CREATED);
    }

    @GetMapping("/auth")
    public ResponseEntity<String> auth(@RequestHeader("x-auth-user") String user, @RequestHeader("x-auth-key") String password) {
        System.out.println(user + " " + password);

        return new ResponseEntity<String>("{\"authorized\": \"OK\"}", HttpStatus.OK);
    }

    @ExceptionHandler({CreateUserException.class})
    public ResponseEntity<String> handelCreateException(CreateUserException exception){
        return new ResponseEntity<String>(exception.getUsername(), HttpStatus.FORBIDDEN);
    }

}
