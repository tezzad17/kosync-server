package com.tezzad.kosync.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KosyncSaveProgressResponseBean {

    private String document;
    private String timestamp;

    public KosyncSaveProgressResponseBean(){
    }

    public KosyncSaveProgressResponseBean(String document, Date date){
        this.document = document;
        this.timestamp = new StringBuilder().append(date.getTime()).toString();
    }

    public void setTimestamp(Date date){
        this.timestamp = new StringBuilder().append(date.getTime()).toString();
    }

}
