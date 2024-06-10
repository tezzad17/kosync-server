package com.tezzad.kosync.business;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.tezzad.kosync.models.KosyncGetProgressResponseBean;

@Service
public class KosyncService {

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

}
