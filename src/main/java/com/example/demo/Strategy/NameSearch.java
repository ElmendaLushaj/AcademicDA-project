package com.example.demo.Strategy;

import com.example.demo.DAL.DocumentDAO;
import com.example.demo.Model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.print.Doc;
import java.util.List;
@Service
public class NameSearch implements SearchStrategy{
    @Autowired
    private DocumentDAO documentDAO;


    public NameSearch() {}

    @Override

    public List<Document> search(String keyword){
       String nameSearch="";
       List<Document> list =  this.documentDAO.search(keyword);
       return list;

    }
}
