package com.example.demo.DAL;

import com.example.demo.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO extends JpaRepository<Comment, Integer>{

}
