package com.example.demo.Helpers;

import com.example.demo.Model.Professor;

import java.io.Serializable;

public class FolderHelper implements Serializable {
    private String name;
    private int professor;
    public FolderHelper(String name,int p){

        this.name=name;
        this.professor=professor;
    }

    public String getName() {
        return name;
    }

    public int getProfessorID() {

        return professor;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setProfessor(int professor) {

        this.professor = professor;
    }
}
