package com.example.demo.DAL;

import com.example.demo.Model.Approvement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovementDAO extends JpaRepository<Approvement , Integer> {


}
