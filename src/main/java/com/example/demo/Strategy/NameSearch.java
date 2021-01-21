package com.example.demo.Strategy;

import com.example.demo.DAL.DocumentDAO;
import com.example.demo.Model.Document;

import javax.print.Doc;
import java.util.List;

public class NameSearch implements SearchStrategy{
    private DocumentDAO documentDAO;


    public NameSearch() {

    }
    @Override
    public List<Document> search(String keyword){
        String nameSearch="";
       List<Document> list =  documentDAO.search(keyword);
       return list;

    }
}
