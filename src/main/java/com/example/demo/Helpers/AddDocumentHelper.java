package com.example.demo.Helpers;

import java.io.Serializable;
import java.util.Date;

public class AddDocumentHelper implements Serializable {
    private String name;
    private String type;
    private String path;
    private Date creationD;
    private Date editedD;
    private double fileSize;
    private int folder;

    public AddDocumentHelper(String name, String type, String path, Date creationD, Date editedD, double fileSize, int folder) {
        this.name = name;
        this.type = type;
        this.path = path;
        this.creationD = creationD;
        this.editedD = editedD;
        this.fileSize = fileSize;
        this.folder = folder;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getCreationD() {
        return creationD;
    }

    public void setCreationD(Date creationD) {
        this.creationD = creationD;
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

    public int getFolder() {
        return folder;
    }

    public void setFolder(int folder) {
        this.folder = folder;
    }
}
