package com.example.demo.Services;

import com.example.demo.DAL.FolderDao;
import com.example.demo.Model.Document;
import com.example.demo.Model.Folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderService implements IProfessorService {
@Autowired
private FolderDao folderDao;

    @Override
    public List<Document> getAllDocuments() {
        return null;
    }

    @Override
    public void addDocument(Document d) {

    }

    @Override
    public void addFolder(Folder d) {
        folderDao.save(d);
    }
}
