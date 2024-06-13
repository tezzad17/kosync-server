package com.tezzad.kosync.document.models.exceptions;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class BookNotFoundException extends Exception {

    private String username;
    private String document;

    public Map<String, String> returnResponse(){
        Map<String, String> response = new HashMap<>();
        response.put("username", username);
        response.put("document", document);

        return response;
    }

}
