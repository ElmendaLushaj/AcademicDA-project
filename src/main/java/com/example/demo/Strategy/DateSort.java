package com.example.demo.Strategy;

import com.example.demo.DAL.DocumentDAO;
import com.example.demo.Helpers.SortHelper;
import com.example.demo.Model.Document;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class DateSort implements SortStrategy{
@Autowired

   private DocumentDAO documentDAO;
  /* public DateSort(Date creationD){
       this.creationD=creationD;
   }*/



    @Override
    public List<Document> sort() {
List<Document> list=this.documentDAO.sortByCreationDate();
return list;
    }




}
