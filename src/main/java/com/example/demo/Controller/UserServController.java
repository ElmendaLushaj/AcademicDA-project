package com.example.demo.Controller;

import com.example.demo.Helpers.LoginHelpers;
import com.example.demo.Model.Professor;
import com.example.demo.Services.IUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/user")
public class UserServController {



  @Autowired
    private IUserInterface userInterface;


   @GetMapping()
   public ResponseEntity getAllEmployees(){
       List<Professor> proffesorList=this.userInterface.getAllProfessors();
       return ResponseEntity.ok(proffesorList);

   }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginHelpers login){
        Professor professor=this.userInterface.login(login.getUsername(),login.getPassword());

        if(professor==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(professor);
    }
}

