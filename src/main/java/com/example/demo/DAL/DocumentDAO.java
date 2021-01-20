package com.example.demo.DAL;

import com.example.demo.Model.Document;
import com.example.demo.Model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentDAO extends JpaRepository<Document , Integer> {
    //search
    @Query("select d from Document d where d.name= ?1")
    public List<Document> search(String keyword);

    @Query("select d from Document d where d.folder= ?1")
    public List<Document> docByFolder(Folder f);
}
