package com.example.demo.Strategy;

import com.example.demo.DAL.DocumentDAO;
import com.example.demo.Model.Document;

import java.util.List;

public class TypeSearch implements SearchStrategy{
    private DocumentDAO documentDAO;


    public TypeSearch() {

    }
    @Override
    public List<Document> search(String keyword){
        String typeSearch="";
      List<Document> list = documentDAO.searchT(keyword);
      return list;

    }
}
