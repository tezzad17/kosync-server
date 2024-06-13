package com.tezzad.kosync.users.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.tezzad.kosync.users.business.UserService;
import com.tezzad.kosync.users.entity.UserEntity;
import com.tezzad.kosync.users.models.UserBean;
import com.tezzad.kosync.users.models.exceptions.AuthUserException;
import com.tezzad.kosync.users.models.exceptions.CreateUserException;

@RestController
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody UserBean user) throws CreateUserException {
        logger.info("Creating User " + user.getUsername());

        UserEntity userResult = userService.createUser(user);

        logger.info("User creation sucessfull");

        return new ResponseEntity<String>(userResult.getUsername(), HttpStatus.CREATED);
    }

    @GetMapping("/auth")
    public ResponseEntity<String> auth(@RequestHeader("x-auth-user") String user,
            @RequestHeader("x-auth-key") String password) throws AuthUserException {
        logger.info("Login for user " + user);

        UserBean userBean = new UserBean();
        userBean.setPassword(password);
        userBean.setUsername(user);

        UserEntity authUser = userService.authUser(userBean);

        logger.info("Login sucessfull");

        return new ResponseEntity<String>("{\"authorized\": \"OK\"}", HttpStatus.OK);
    }

    @ExceptionHandler({ CreateUserException.class })
    public ResponseEntity<String> handelCreateException(CreateUserException exception) {
        logger.info("Can not create user");
        return new ResponseEntity<String>(exception.getUsername(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ AuthUserException.class })
    public ResponseEntity<String> handelCreateException(AuthUserException exception) {
        logger.info("Error on authorization");
        return new ResponseEntity<String>(exception.getUsername(), HttpStatus.FORBIDDEN);
    }

}
