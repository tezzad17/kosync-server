package com.tezzad.kosync.document.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    Logger logger = LoggerFactory.getLogger(KosyncController.class);

    @PutMapping("progress")
    public ResponseEntity<KosyncSaveProgressResponseBean> putMethodName(@RequestBody KosyncDocumentBean document,
            @RequestHeader("x-auth-user") String user, @RequestHeader("x-auth-key") String password)
            throws AuthUserException {

        logger.info("Saving progress for document " + document.toString() + " For user: " + user);

        UserBean userBean = new UserBean();
        userBean.setUsername(user);
        userBean.setPassword(password);

        KosyncSaveProgressResponseBean documentResponse = kosyncService.saveDocumentProgress(document, userBean);

        logger.info("Saving progress succesful");
        return ResponseEntity.ok().body(documentResponse);
    }

    @GetMapping("/progress/{document}")
    public KosyncGetProgressResponseBean getProgressDocument(@PathVariable("document") String documentId,
            @RequestHeader("x-auth-user") String user, @RequestHeader("x-auth-key") String password)
            throws AuthUserException, BookNotFoundException {

        logger.info("Getting progress for document " + documentId + " For user: " + user);

        KosyncDocumentBean documentBean = new KosyncDocumentBean();
        documentBean.setDocument(documentId);

        UserBean userBean = new UserBean();
        userBean.setUsername(user);
        userBean.setPassword(password);

        logger.info("Getting progress succesful");

        return kosyncService.getDocumentProgress(documentBean, userBean);
    }

    @ExceptionHandler({ AuthUserException.class })
    public ResponseEntity<String> handleCreateException(AuthUserException exception) {
        logger.info("User not found, cant get information");
        return new ResponseEntity<String>(exception.getUsername(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ BookNotFoundException.class })
    public ResponseEntity<Map<String, String>> handleBookException(BookNotFoundException exception) {
        logger.info("Book not found for user");
        return new ResponseEntity<Map<String, String>>(exception.returnResponse(), HttpStatus.NOT_FOUND);
    }

}
