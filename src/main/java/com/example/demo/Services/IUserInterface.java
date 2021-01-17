package com.example.demo.Services;

import com.example.demo.Model.Document;
import com.example.demo.Model.Folder;
import com.example.demo.Model.Professor;

import java.util.List;
import java.util.Optional;

public interface IUserInterface {


    List<Professor> getAllProfessors();
    Professor login(String username, String pass);
    void register(Professor p);
    List <Folder> listAllFolder(String name);
    Professor getByUsername(String username);
    Optional<Professor> getProfById(int appId);
   /*void register(String name, String email , String password, String degree, String username);
*/
    List<Document> listAll(String keyword);
}
