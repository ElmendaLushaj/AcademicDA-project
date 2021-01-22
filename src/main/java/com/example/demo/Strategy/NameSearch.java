package com.example.demo.Strategy;

import com.example.demo.DAL.DocumentDAO;
import com.example.demo.Model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

public class NameSearch implements SearchStrategy{

    private DocumentDAO documentDAO;


    public NameSearch() {

    }
    @Override
    public List<Document> search(String keyword){
       // String nameSearch="";
       List<Document> list =  this.documentDAO.search(keyword);
       return list;

    }
}
