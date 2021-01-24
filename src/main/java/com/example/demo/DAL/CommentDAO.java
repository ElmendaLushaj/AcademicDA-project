package com.example.demo.DAL;

import com.example.demo.Model.Comment;
import com.example.demo.Model.Document;
import com.example.demo.Model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentDAO extends JpaRepository<Comment, Integer>{
    @Query("select c from Comment c where c.document=?1")
    List<Comment> findCommentByDoc(Document docid);
}
