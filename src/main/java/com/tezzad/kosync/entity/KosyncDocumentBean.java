package com.tezzad.kosync.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class KosyncDocumentBean implements Serializable {

    private String document;
    private String progress;
    private String percentage;
    private String device;
    private String device_id;
}
