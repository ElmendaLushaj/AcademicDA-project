package com.example.demo.Services.Strategy;

import com.example.demo.DAL.DocumentRepository;
import com.example.demo.Model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class NameSearch implements SearchStrategy{
    @Autowired
    private DocumentRepository documentRepo;


    public NameSearch() {}

    @Override

    public List<Document> search(String keyword){
       String nameSearch="";
       List<Document> list =  this.documentRepo.search(keyword);
       return list;

    }
}
