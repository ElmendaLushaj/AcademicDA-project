package com.example.demo.Helpers;

import com.sun.org.apache.xpath.internal.objects.XString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SortHelper implements Serializable {
    private String name;
    private Date creationD;

    public SortHelper() {
    }

    public SortHelper(String name, Date creationD) {
        this.name = name;
        this.creationD = creationD;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Date getCreationD() {
        return creationD;
    }

    public void setCreationD(Date creationD) {
        this.creationD = creationD;
    }

}
