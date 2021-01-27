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

    List<Folder> getFoldByUser(Professor profId);

    void deleteDoc(int docId);

    void deleteFold(int foldId);

    List<Document> docByFolder(Folder foldid);

    List<Comment> getAllComments();

    List<Comment> getComByDoc(Document docId);

    List<Approvement> getAppByDoc(Document docId);

    List<Document> getDocByName(String name);

    List<Document> getDocByProf(Professor proofId);

}
