package com.dousnl.domain.fdds;

import java.io.*;

public class BookPanGuTagBO implements Serializable {
    private static final long serialVersionUID = -8469193976047223558L;
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
