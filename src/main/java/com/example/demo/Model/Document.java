package com.example.demo.Model;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int DocId;

    @Column(nullable=false)
    private String name;
    @Column(nullable=false)
    private double fileSize;
    @Column(nullable=false)
    private String type;
    @Column
    private Date creationD;
    @Column
    private Date editD;
    @Column(nullable=false)
    private String docPath;

    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Doc_Fold",
            joinColumns = { @JoinColumn(name = "DocId")},
            inverseJoinColumns = {@JoinColumn(name = "folderID")}
    )
    Set<Folder>folders = new HashSet<>();
     */
    @ManyToOne
    @JoinColumn(name="professorId")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name="foldeId")
    private Folder folder;

    public Document(String name, double fileSize, String type, Date creationD, Date editD, String docPath , Professor professor , Folder folder) {
        this.name = name;
        this.fileSize = fileSize;
        this.type = type;
        this.creationD = creationD;
        this.editD = editD;
        this.docPath = docPath;
        this.professor=professor;
        this.folder = folder;

    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "document")
    private Set<Comment>  com;

    public Set<Comment> getComm() {
        return com;
    }

    public void setComments(Set<Comment> coms) {
        this.com = coms;
    }

   /* @OneToMany(cascade = CascadeType.ALL, mappedBy = "document")
    private Set<Approvement>  app;

    public Set<Approvement> getApp() {
        return app;
    }

    public void setApprovements(Set<Approvement> apps) {
        this.app = apps;
    }*/



    @OneToOne (fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name= "ApprovementId")
    private Approvement approve;




    public Document(int docId, String name, double fileSize, String type, Date creationD, Date editD, String docPath, Professor professor) {
        DocId = docId;
        this.name = name;
        this.fileSize = fileSize;
        this.type = type;
        this.creationD = creationD;
        this.editD = editD;
        this.docPath = docPath;
        this.professor = professor;
    }
    public Document(){}


    public Document(Date creationD,String docPath, Date editD, double fileSize,String name,  String type, Professor professor , Folder folder ) {
        this.name = name;
        this.fileSize = fileSize;
        this.type = type;
        this.creationD = creationD;
        this.editD = editD;
        this.docPath = docPath;
        this.professor = professor;
        this.folder = folder;

    }

    public int getDocId() {
        return DocId;
    }

    public String getName() {
        return name;
    }

    public double getFileSize() {
        return fileSize;
    }

    public String getType() {
        return type;
    }

    public Date getCreationD() {
        return creationD;
    }

    public Date getEditD() {
        return editD;
    }

    public String getDocPath() {
        return docPath;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCreationD(Date creationD) {
        this.creationD = creationD;
    }

    public void setEditD(Date editD) {
        this.editD = editD;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void addAtribute(String documentList, List<Document> documentList1) {
    }
}
