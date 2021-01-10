package com.example.demo.Services;

import com.example.demo.Model.Professor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserInterface {


    List<Professor> getAllProfessors();
    Professor login(String username, String pass);
    void register(Professor p);
   /*void register(String name, String email , String password, String degree, String username);
*/
}
