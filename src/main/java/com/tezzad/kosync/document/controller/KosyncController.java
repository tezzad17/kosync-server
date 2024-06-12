package com.tezzad.kosync.document.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tezzad.kosync.document.business.KosyncService;
import com.tezzad.kosync.document.models.KosyncDocumentBean;
import com.tezzad.kosync.document.models.KosyncGetProgressResponseBean;
import com.tezzad.kosync.document.models.KosyncSaveProgressResponseBean;
import com.tezzad.kosync.document.models.exceptions.BookNotFoundException;
import com.tezzad.kosync.users.models.UserBean;
import com.tezzad.kosync.users.models.exceptions.AuthUserException;

@RestController
@RequestMapping("/syncs")
public class KosyncController {

    @Autowired
    private KosyncService kosyncService;

    @PutMapping("progress")
    public ResponseEntity<KosyncSaveProgressResponseBean> putMethodName(@RequestBody KosyncDocumentBean document,
            @RequestHeader("x-auth-user") String user, @RequestHeader("x-auth-key") String password)
            throws AuthUserException {

        System.out.println(document.toString() + " " + user + " " + password);

        UserBean userBean = new UserBean();
        userBean.setUsername(user);
        userBean.setPassword(password);

        KosyncSaveProgressResponseBean documentResponse = kosyncService.saveDocumentProgress(document, userBean);

        return ResponseEntity.ok().body(documentResponse);
    }

    @GetMapping("/progress/{document}")
    public KosyncGetProgressResponseBean getProgressDocument(@PathVariable("document") String documentId,
            @RequestHeader("x-auth-user") String user, @RequestHeader("x-auth-key") String password)
            throws AuthUserException, BookNotFoundException {

        System.out.println(documentId + " " + user + " " + password);
        KosyncDocumentBean documentBean = new KosyncDocumentBean();
        documentBean.setDocument(documentId);

        UserBean userBean = new UserBean();
        userBean.setUsername(user);
        userBean.setPassword(password);

        return kosyncService.getDocumentProgress(documentBean, userBean);
    }

    @ExceptionHandler({ AuthUserException.class })
    public ResponseEntity<String> handleCreateException(AuthUserException exception) {
        return new ResponseEntity<String>(exception.getUsername(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ BookNotFoundException.class })
    public ResponseEntity<Map<String,String>> handleBookException(BookNotFoundException exception) {
        return new ResponseEntity<Map<String,String>>(exception.returnResponse(), HttpStatus.NOT_FOUND);
    }

}
