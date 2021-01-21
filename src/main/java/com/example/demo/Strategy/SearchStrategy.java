package com.example.demo.Strategy;

import com.example.demo.Model.Document;

import javax.print.Doc;
import java.util.List;

public interface SearchStrategy {
    List<Document> search(String keyword);
}
