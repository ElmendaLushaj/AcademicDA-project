package com.example.demo.Strategy;

import com.example.demo.Helpers.SortHelper;
import com.example.demo.Model.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

public interface SortStrategy {

List<Document> sort(List<Document>documents);
}
