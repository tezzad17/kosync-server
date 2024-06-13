package com.tezzad.kosync.document.entity;

import java.io.Serializable;

import lombok.ToString;

@ToString
public class BookEntityKey implements Serializable {
    private String username;
    private String document;

    public BookEntityKey() {

    }

    public BookEntityKey(String username, String document) {
        this.username = username;
        this.document = document;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((document == null) ? 0 : document.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BookEntityKey other = (BookEntityKey) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (document == null) {
            if (other.document != null)
                return false;
        } else if (!document.equals(other.document))
            return false;
        return true;
    }

}
