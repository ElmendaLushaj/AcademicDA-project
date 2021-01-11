package com.example.demo.Services;

import com.example.demo.DAL.DocumentDAO;
import com.example.demo.Model.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService implements IProfessorService{
    private DocumentDAO docDao;
    @Override
    public List<Document> getAllDocuments() {
        return this.docDao.findAll();
    }

    @Override
    public void addDocument(Document d) {
        docDao.save(d);
    }
}
