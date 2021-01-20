package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
public class Folder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int folderID;
    @Column(nullable=false)
    private String name;

    /*@ManyToMany(mappedBy = "folders")
    private Set<Document> documents = new HashSet<>();
     */

    @ManyToOne
    @JoinColumn(name="professorId")
    private Professor professor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "folder")
    private Set<Document> doc;
    @JsonIgnore
    public Set<Document> getDoc() {
        return doc;
    }
    @JsonIgnore
    public void setDocument(Set<Document> docs) {
        this.doc = docs;
    }


    public Folder( int folderID, String name, Professor professor) {
        this.folderID = folderID;
        this.name = name;
        this.professor = professor;
    }
    public Folder(String name, Professor professor){
        this.name = name;
        this.professor = professor;
    }
    public Folder(){}

  /*public Folder(String name , Professor professor){
        this.name = name;
        this.professor = professor;

  }*/

    public int getFolderID() {
        return folderID;
    }

    public String getName() {
        return name;
    }
    @JsonIgnore
    public Professor getProfessor() {
        return professor;
    }

    public void setName(String name) {
        this.name = name;
    }
    @JsonIgnore
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    //prvvvv



}
