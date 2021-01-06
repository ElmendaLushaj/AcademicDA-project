package com.example.demo.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int folderID;
    @Column(nullable=false)
    private String name;

    @ManyToMany(mappedBy = "folders")
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="professorId")
    private Professor professor;


    public Folder(int folderID, String name, Professor professor) {
        this.folderID = folderID;
        this.name = name;
        this.professor = professor;
    }
    public Folder(){}

    public int getFolderID() {
        return folderID;
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
