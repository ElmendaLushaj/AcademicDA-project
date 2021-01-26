package com.example.demo.Strategy;

import com.example.demo.DAL.DocumentDAO;
import com.example.demo.Model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NameSort implements SortStrategy{

    @Autowired
    private DocumentDAO documentDAO;

    public NameSort(){

    }

    @Override
    public List<Document> sort() {
        List<Document>list=this.documentDAO.sortByName();
    return list;
    }
}
