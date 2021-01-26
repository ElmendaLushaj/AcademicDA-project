package com.example.demo.DAL;
import com.example.demo.Model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorDAO extends JpaRepository<Professor , Integer> {

    @Query("select p from Professor p where p.Username=?1 and p.password=?2")
    Optional<Professor> findProfessorByUsernameAndPassword(String username, String pass);

    @Query("select p from Professor p where p.Username=?1")
    Optional<Professor> findProfessorByUsername(String username);

    @Query("select p from Professor p where p.Username=?1")
    List<Professor> existsByUsername2(String username);

}


