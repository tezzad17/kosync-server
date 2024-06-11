package com.tezzad.kosync.document.business;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tezzad.kosync.document.entity.BookEntity;
import com.tezzad.kosync.document.entity.BookEntityKey;
import com.tezzad.kosync.document.models.KosyncDocumentBean;
import com.tezzad.kosync.document.models.KosyncGetProgressResponseBean;
import com.tezzad.kosync.document.models.KosyncSaveProgressResponseBean;
import com.tezzad.kosync.document.repositories.BookRepository;
import com.tezzad.kosync.users.business.UserService;
import com.tezzad.kosync.users.entity.UserEntity;
import com.tezzad.kosync.users.models.UserBean;
import com.tezzad.kosync.users.models.exceptions.AuthUserException;

@Service
public class KosyncService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserService userService;

    public KosyncGetProgressResponseBean getDocumentProgress() {

        KosyncGetProgressResponseBean response = new KosyncGetProgressResponseBean();
        response.setDocument("155ba81857a08da681f453ec9a6ca012");
        response.setProgress("/body/DocFragment[8]/body/div/section/p[15]/span/text().54");
        response.setPercentage("0.0452");
        response.setDevice("dm3qxxx");
        response.setDevice_id("F14830E2635C4C7FA3496CFC4B56430C");
        response.setTimestamp(new Date());
        response.setUsername("drakos");

        return response;
    }

    public KosyncSaveProgressResponseBean saveDocumentProgress(KosyncDocumentBean documentBean, UserBean userBean) {

        try {
            userService.authUser(userBean);

            BookEntity bookEntity = getBookEntity(documentBean, userBean);

            if (bookEntity == null) {
                bookEntity = saveDocument(documentBean, userBean);
            } else {
                bookEntity = updateDocument(documentBean, userBean);
            }

            KosyncSaveProgressResponseBean responseBean = new KosyncSaveProgressResponseBean();
            responseBean.setDocument(bookEntity.getDocument());;
            responseBean.setTimestamp(bookEntity.getTimestamp());

            return responseBean;

        } catch (AuthUserException e) {
            e.printStackTrace();
        }

        return null;
    }

    public BookEntity getBookEntity(KosyncDocumentBean documentBean, UserBean userBean) {

        List<BookEntity> bookEntityList = bookRepository.findByUsernameAndDocument(userBean.getUsername(),
                documentBean.getDocument());

        if (bookEntityList == null || bookEntityList.isEmpty()) {
            return null;
        }

        return bookEntityList.get(0);
    }

    public BookEntity saveDocument(KosyncDocumentBean documentBean, UserBean userBean) {

        BookEntity bookEntity = new BookEntity();
        bookEntity.setUsername(userBean.getUsername());
        bookEntity.setDocument(documentBean.getDocument());
        bookEntity.setProgress(documentBean.getProgress());
        bookEntity.setPercentage(documentBean.getPercentage());
        bookEntity.setDevice(documentBean.getDevice());
        bookEntity.setDeviceId(documentBean.getDevice_id());
        bookEntity.setTimestamp(new Date());

        return bookRepository.save(bookEntity);
    }

    public BookEntity updateDocument(KosyncDocumentBean documentBean, UserBean userBean) {
        return null;
    }

}
