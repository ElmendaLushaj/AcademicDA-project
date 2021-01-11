package com.example.demo.DAL;

import com.example.demo.Model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentDAO extends JpaRepository<Document , Integer> {

}
