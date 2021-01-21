package com.example.demo.Strategy;

import com.example.demo.Model.Document;

import java.util.List;

public class NameSortStrategy implements SortStrategy{
private String name;
public NameSortStrategy(String name){
    this.name=name;
}

   /*public NameSortStrategy() {

    }


    @Override
    public List<Document> sort(List<Document> documents) {

        return documents;
    }*/


}
