package com.example.demo.Services.Strategy;
import com.example.demo.DAL.DocumentRepository;
import com.example.demo.Model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NameSort implements SortStrategy{

    @Autowired
    private DocumentRepository documentRepo;

    public NameSort(){

    }

    @Override
    public List<Document> sort() {
        List<Document>list=this.documentRepo.sortByName();
    return list;
    }
}
