package com.example.demo.Controller;

import com.example.demo.Helpers.LoginHelpers;
import com.example.demo.Helpers.RegisterHelper;
import com.example.demo.Helpers.getModelHelper2;
import com.example.demo.Helpers.searchHelper;
import com.example.demo.Model.Document;
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


   @GetMapping("/getALLProf")
   public ResponseEntity getAllProfessors(){
       List<Professor> professorList=this.userInterface.getAllProfessors();
       return ResponseEntity.ok(professorList);

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

     @GetMapping("/searchFolder")
    public ResponseEntity listAllFolder(@RequestBody searchHelper sh){
        List<Folder> folders = this.userInterface.listAllFolder(sh.getName());
        return ResponseEntity.ok(folders);
     }
    @GetMapping("/searchDocument")
    public ResponseEntity listAllDocuments(@RequestBody searchHelper sh){
        List<Document> documents = this.userInterface.listAll(sh.getName());
        return ResponseEntity.ok(documents);
    }
    @GetMapping("/profByUserName")
    public ResponseEntity profByName(@RequestBody getModelHelper2 gtm){
        Professor professor=this.userInterface.getByUsername(gtm.getUsername());

        if(professor==null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(professor);
    }

}

