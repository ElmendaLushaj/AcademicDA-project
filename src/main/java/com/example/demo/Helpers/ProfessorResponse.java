package com.example.demo.Helpers;



import com.example.demo.Model.Professor;


import java.io.Serializable;
import java.util.List;

public class ProfessorResponse implements Serializable {
    private Professor professor;

    public ProfessorResponse(Professor professor) {
        this.professor = professor;
    }


}
