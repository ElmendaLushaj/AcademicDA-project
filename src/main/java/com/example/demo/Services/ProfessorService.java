package com.example.demo.Services;

import com.example.demo.DAL.DocumentDAO;
import com.example.demo.DAL.FolderDao;
import com.example.demo.Model.Document;
import com.example.demo.Model.Folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService implements IProfessorService{
    @Autowired
    private DocumentDAO docDao;
    private FolderDao folDao;
    @Override
    public List<Document> getAllDocuments() {
        return this.docDao.findAll();
    }

    @Override
    public void addDocument(Document d) {
        docDao.save(d);
    }
    @Override
    public  List<Folder>getAllFolders(){
        return this.folDao.findAll();
    }
    @Override
    public void addFolder(Folder d) {
        folDao.save(d);
    }
}
