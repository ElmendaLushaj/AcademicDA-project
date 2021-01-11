package com.example.demo.DAL;

import com.example.demo.Model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FolderDao extends JpaRepository<Folder, Integer> {


}
