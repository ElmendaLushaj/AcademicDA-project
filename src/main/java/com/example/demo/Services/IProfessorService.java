package com.example.demo.Services;

import com.example.demo.Model.*;

import java.util.List;
import java.util.Optional;

public interface IProfessorService {
    List<Document> getAllDocuments();
    List<Folder> getAllFolders();
    void addDocument(Document d);
    void addFolder (Folder f);
    Optional<Document> getDocById(int docId);
    Optional<Folder> getFoldById(int folId);
    Optional<Comment> getCommById(int commId);
    Optional <Approvement> getAppById(int appId);
    void deleteDoc(int docId);
    void deleteFold(int foldId);
}
