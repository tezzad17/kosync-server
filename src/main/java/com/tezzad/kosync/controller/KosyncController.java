package com.tezzad.kosync.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tezzad.kosync.business.KosyncService;
import com.tezzad.kosync.entity.KosyncDocumentBean;
import com.tezzad.kosync.entity.KosyncGetProgressResponseBean;
import com.tezzad.kosync.entity.KosyncSaveProgressResponseBean;

@RestController
@RequestMapping("/syncs")
public class KosyncController {

    @Autowired
    private KosyncService kosyncService;

    @PutMapping("progress")
    public ResponseEntity<KosyncSaveProgressResponseBean> putMethodName(@RequestBody KosyncDocumentBean document,
            @RequestHeader("x-auth-user") String user, @RequestHeader("x-auth-key") String password) {

        System.out.println(document.toString() + " " + user + " " + password);

        return ResponseEntity.ok().body(new KosyncSaveProgressResponseBean(document.getDocument(), new Date()));
    }

    @GetMapping("/progress/{document}")
    public KosyncGetProgressResponseBean getProgressDocument( @PathVariable("document") String documentId,
            @RequestHeader("x-auth-user") String user, @RequestHeader("x-auth-key") String password) {

                System.out.println(documentId + " " + user + " " + password);

        return kosyncService.getDocumentProgress();
    }

}
