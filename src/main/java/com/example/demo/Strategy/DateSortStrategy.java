package com.example.demo.Strategy;

import com.example.demo.Helpers.SortHelper;
import com.example.demo.Model.Document;
import net.bytebuddy.TypeCache;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public class DateSortStrategy implements SortStrategy{

   private Date creationD;
   public DateSortStrategy(Date creationD){
       this.creationD=creationD;
   }



    @Override
    public List<Document> sort(List<Document> documents) {
        return documents;
    }
}
