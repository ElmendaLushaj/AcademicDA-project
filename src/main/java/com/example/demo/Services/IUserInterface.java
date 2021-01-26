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

    Folder getFolderByName(String name);

    Optional <Professor> getByUsername(String username);

    Optional<Professor> getProfById(int appId);

    List<Document> listAll(String keyword);

    List<Document>sortDoc(String sortType);

    List<Document> searchDoc(String searchType, String keyword);

    List<Professor> existsUser(String username);
}
