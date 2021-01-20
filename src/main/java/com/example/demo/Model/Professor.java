package com.example.demo.Model;

import com.example.demo.Helpers.RegisterHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    public Set<Document> getDoc() {
        return doc;
    }
    @JsonIgnore
    public void setDocuments(Set<Document> docs) {
        this.doc = docs;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
    private Set<Folder> fold;
    @JsonIgnore
    public Set<Folder> getFold() {
        return fold;
    }
    @JsonIgnore
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






/*    public Professor(Professor.ProfessorBuilder professorBuilder) {
        this.Name=professorBuilder.Name;
        this.email=professorBuilder.email;
        this.degree=professorBuilder.degree;
        this.Username=professorBuilder.Username;
        this.password=professorBuilder.password;
        this.ProfId=professorBuilder.ProfId;


    }*/

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


    /*public static class ProfessorBuilder{
        private String Name;
        private String email;
        private String degree;
        private String Username;
        private String password;
        private int ProfId;

        public ProfessorBuilder(String password,String username){
            this.password=password;
            this.Username=Username;
        }

        public Professor.ProfessorBuilder setProfId(int ProfId){
            this.ProfId=ProfId;
            return this;
        }


        public Professor.ProfessorBuilder setName(String Name){
            this.Name=Name;
            return this;

        }
        public Professor.ProfessorBuilder setEmail(String email){
            this.email=email;
            return this;
        }
        public Professor.ProfessorBuilder setDegree(String degree) {
            this.degree = degree;
            return this;
        }
        public Professor.ProfessorBuilder setUsername(String Username){
            this.Username=Username;
            return this;
        }
        public Professor.ProfessorBuilder setPassword(String password){
            this.password=password;
            return this;
        }
        public Professor build(){
            return  new Professor(this);
        }

    }*/
}

