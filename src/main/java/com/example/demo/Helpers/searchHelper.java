package com.example.demo.Helpers;

import java.io.Serializable;

public class searchHelper implements Serializable {
    private String name;

    public searchHelper(String name){

        this.name=name;
    }
    public searchHelper(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
