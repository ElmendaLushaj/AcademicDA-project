package com.example.demo.DAL;
import com.example.demo.Model.Document;
import com.example.demo.Model.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DocumentDAO extends JpaRepository<Document , Integer> {

    @Query("select d from Document d where d.name= ?1")
    public List<Document> search(String keyword);

    @Query("select d from Document d where d.type= ?1")
    public List<Document> searchT(String keyword);

    @Query("select d from Document d where d.folder= ?1")
    public List<Document> docByFolder(Folder f);

    @Query("SELECT d from Document d order by d.name ASC")
    public  List<Document>sortByName();

    @Query("SELECT  d from  Document d order by d.creationD ASC ")
    public List<Document>sortByCreationDate();


}
