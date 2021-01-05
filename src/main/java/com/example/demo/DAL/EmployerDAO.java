package com.example.demo.DAL;

import com.example.demo.Model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerDAO extends JpaRepository<Professor, Integer> {
}
