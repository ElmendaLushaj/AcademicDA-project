package com.example.demo.Helpers;

import com.example.demo.Model.Professor;

import java.io.Serializable;

public class FolderHelper implements Serializable {
    private String name;
    private Professor professor;
    public FolderHelper(String name,Professor p){

        this.name=name;
        this.professor=professor;
    }

    public String getName() {
        return name;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
