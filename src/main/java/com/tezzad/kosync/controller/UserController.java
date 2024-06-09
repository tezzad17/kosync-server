package com.tezzad.kosync.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tezzad.kosync.entity.UserBean;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody UserBean user) {
        System.out.println(user.getUsername() + " " + user.getPassword());

        return new ResponseEntity<String>(user.getUsername(), HttpStatus.CREATED);
    }

    @GetMapping("/auth")
    public ResponseEntity<String> auth(@RequestHeader("x-auth-user") String user, @RequestHeader("x-auth-key") String password) {
        System.out.println(user + " " + password);

        return new ResponseEntity<String>("{\"authorized\": \"OK\"}", HttpStatus.OK);
    }

}
