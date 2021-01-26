package com.example.demo.DAL;
import com.example.demo.Model.Approvement;
import com.example.demo.Model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ApprovementRepository extends JpaRepository<Approvement , Integer> {
    @Query("select a from Approvement a where a.document=?1")
    List<Approvement> findApprovementByDoc(Document docid);

}
