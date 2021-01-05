package com.example.demo.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Professor {



        @Id
        @Column
        private int ProfId;
    @Column
        private String Name;
    @Column
        private String email;
    @Column
        private String password;
    @Column
        private String degree;


        public Professor(){}



        public Professor(int profId, String name, String email, String password, String degree) {
            ProfId = profId;
            Name = name;
            this.email = email;
            this.password = password;
            this.degree = degree;

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
