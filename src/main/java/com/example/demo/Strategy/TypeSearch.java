package com.example.demo.Strategy;

import com.example.demo.DAL.DocumentDAO;
import com.example.demo.Model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeSearch implements SearchStrategy{
    @Autowired
    private DocumentDAO documentDAO;


    public TypeSearch() {
    }

    @Override
    public List<Document> search(String keyword){
        String typeSearch="";
      List<Document> list = this.documentDAO.searchT(keyword);
      return list;

    }
}
