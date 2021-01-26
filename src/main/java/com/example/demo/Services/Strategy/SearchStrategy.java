package com.example.demo.Services.Strategy;
import com.example.demo.Model.Document;
import java.util.List;

public interface SearchStrategy {
    List<Document> search(String keyword);
}
