package com.example.demo.Helpers;
import com.example.demo.Model.Professor;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

public class ProfessorResponse implements Serializable {
    private int ProfId;
    private String email;
    private String password;
    private String degree;
    private String Username;
    private String Name;

    public ProfessorResponse(ProfessorResponse.ProfessorResponseBuilder professorResponseBuilder) {
        this.Name = professorResponseBuilder.Name;
        this.email = professorResponseBuilder.email;
        this.degree = professorResponseBuilder.degree;
        this.Username = professorResponseBuilder.Username;
        this.password = professorResponseBuilder.password;
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

    public static class ProfessorResponseBuilder{
        private String Name;
        private String email;
        private String degree;
        private String Username;
        private String password;
        private int ProfID;

        public  ProfessorResponseBuilder(String Username,String password){
            this.Username=Username;
            this.password=password;

        }
        public ProfessorResponse.ProfessorResponseBuilder setName(String Name){
            this.Name=Name;
            return this;
        }
        public ProfessorResponse.ProfessorResponseBuilder setEmail(String email){
            this.email=email;
            return this;
        }
        public ProfessorResponse.ProfessorResponseBuilder setDegree(String degree){
            this.degree=degree;
            return this;
        }
        public ProfessorResponse.ProfessorResponseBuilder setProfID(int ProfID){
            this.ProfID=ProfID;
            return this;
        }
        public ProfessorResponse build(){
            return  new ProfessorResponse(this);
        }


    }


}