package com.example.demo.Model;

import javax.persistence.*;
import javax.print.Doc;
import java.util.Set;

@Entity
public class Professor {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        private int ProfId;
    @Column(nullable=false)
        private String Name;
    @Column(nullable=false)
        private String email;
    @Column(nullable=false)
        private String password;
    @Column
        private String degree;
    @Column
        private String Username;



    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
    private Set<Document> doc;

    public Set<Document> getDoc() {
        return doc;
    }

    public void setDocuments(Set<Document> docs) {
        this.doc = docs;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
    private Set<Folder> fold;

    public Set<Folder> getFold() {
        return fold;
    }

    public void setFolders(Set<Folder> folds) {
        this.fold = folds;
    }


        public Professor(){}
        public Professor(String Username , String password){
        this.Username = Username;
        this.password = password;
        }
        //String name, String degree, String email, String password, String username
        public Professor(String name, String degree, String email,String password, String Username){
            Name = name;
            this.email = email;
            this.password = password;
            this.degree = degree;
            this.Username = Username;
        }

        public Professor(int profId, String name, String email, String password, String degree, String Username) {
            ProfId = profId;
            Name = name;
            this.email = email;
            this.password = password;
            this.degree = degree;
            this.Username = Username;

        }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public int getProfId() {
        return ProfId;
    }

    public String getName() {
        return Name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDegree() {
        return degree;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
