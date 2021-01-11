package com.example.demo.Services;

import com.example.demo.Model.Document;
import com.example.demo.Model.Folder;
import com.example.demo.Model.Professor;

import java.util.List;

public interface IProfessorService {
    List<Document> getAllDocuments();
    List<Folder> getAllFolders();
    void addDocument(Document d);
    void addFolder (Folder f);
}
