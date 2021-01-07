package com.example.demo.DAL;

import com.example.demo.Model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProfessorDAO extends JpaRepository<Professor , Integer> {
    @Query("select p from Professor p where p.username=?1 and p.password=?2")
    Optional<Professor> findProfessorByUsernameAndPassword(String username, String pass);
}
