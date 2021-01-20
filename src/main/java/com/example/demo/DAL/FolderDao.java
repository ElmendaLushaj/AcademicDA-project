package com.example.demo.DAL;

import com.example.demo.Model.Folder;
import com.example.demo.Model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FolderDao extends JpaRepository<Folder, Integer> {
    @Query("select f from Folder f where f.name=?1")
    List<Folder> findFoldersByName(String name);

    @Query("select f from Folder f where f.professor=?1")
    List<Folder> getFolderByUser(Professor professor);
}
