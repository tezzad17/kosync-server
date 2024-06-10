package com.tezzad.kosync.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KosyncGetProgressResponseBean {

    private String username;
    private String document;
    private String progress;
    private String percentage;
    private String device;
    private String device_id;
    private String timestamp;

    public void setTimestamp(Date date) {
        this.timestamp = new StringBuilder().append(date.getTime()).toString();
    }

}
