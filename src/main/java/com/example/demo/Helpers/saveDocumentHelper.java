package com.example.demo.Helpers;

import com.example.demo.Model.Professor;

import java.io.Serializable;
import java.util.Date;

public class saveDocumentHelper implements Serializable {
    private Date creationD;
    private String docPath;
    private Date editedD;
    private double fileSize;
    private String name;
    private String type;
    private int profID;

    public saveDocumentHelper(Date creationD, String docPath, Date editedD, double fileSize, String name, String type, int profID) {
        this.creationD = creationD;
        this.docPath = docPath;
        this.editedD = editedD;
        this.fileSize = fileSize;
        this.name = name;
        this.type = type;
        this.profID = profID;
    }

    public Date getCreationD() {
        return creationD;
    }

    public void setCreationD(Date creationD) {
        this.creationD = creationD;
    }

    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public Date getEditedD() {
        return editedD;
    }

    public void setEditedD(Date editedD) {
        this.editedD = editedD;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProfID() {
        return profID;
    }

    public void setProfID(int profID) {
        this.profID = profID;
    }

}
