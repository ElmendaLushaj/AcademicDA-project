package com.example.demo.Services;

import com.example.demo.Model.Professor;

import java.util.List;

public interface IUserInterface {
    List<Professor> getAllProfessors();
    Professor login(String username, String pass);
}
