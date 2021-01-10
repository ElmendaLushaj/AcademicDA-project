package com.example.demo.Services;

import com.example.demo.Model.Document;
import com.example.demo.Model.Professor;

import java.util.List;

public interface IProfessorService {
    List<Document> getAllDocuments();
    void addDocument(Document d);
}
