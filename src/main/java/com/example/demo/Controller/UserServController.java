package com.example.demo.Controller;

import com.example.demo.DAL.SearchHelper;
import com.example.demo.Helpers.LoginHelpers;
import com.example.demo.Helpers.RegisterHelper;
import com.example.demo.Model.Folder;
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


   @GetMapping
   public ResponseEntity getAllProfessors(){
       List<Professor> professorList=this.userInterface.getAllProfessors();
       return ResponseEntity.ok(professorList);

   }

    @GetMapping("/searchFold")
    public ResponseEntity searchFolders(@RequestBody SearchHelper sh){
        List<Folder> folderListList=this.userInterface.getAllFoldersByName(sh.getName());
        return ResponseEntity.ok(folderListList);

    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginHelpers login){
        Professor professor=this.userInterface.login(login.getUsername(),login.getPassword());

        if(professor==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(professor);
    }
    /*
   @PostMapping("/register",value="/save")
    public void register(@RequestBody RegisterHelper register ) {
        userInterface.register(register.getName(), register.getEmail(), register.getPassword(), register.getDegree(), register.getUsername());

    }*/
    @PostMapping("/registre")
    public void register(@RequestBody RegisterHelper registerh){
        Professor p = new Professor(registerh.getName(),registerh.getDegree(),registerh.getEmail(),registerh.getPassword(), registerh.getUsername());
         userInterface.register(p);
    }
}

