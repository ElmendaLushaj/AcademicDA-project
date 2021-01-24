package com.example.demo.Controller;

import com.example.demo.Helpers.*;
import com.example.demo.Model.Document;
import com.example.demo.Model.Folder;
import com.example.demo.Model.Professor;
import com.example.demo.Services.IUserInterface;
import com.example.demo.Strategy.NameSearch;
import com.example.demo.Strategy.SearchStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/user")
public class UserServController {



  @Autowired
    private IUserInterface userInterface;


/* @GetMapping("/getALLProf")
   public ResponseEntity<List<Professor>> getAllProfessors(){
       List<Professor> professorList=this.userInterface.getAllProfessors();
       if(professorList ==null){
           return null;
       }

       return ResponseEntity.ok(professorList);

   }*/

    @GetMapping("/getALLProf")
    public ProfessorResponse getAllProfessors(){
        List<Professor> professorList=this.userInterface.getAllProfessors();
        if(professorList.size() == 0){
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("Nuk ekziston nje list me Profesor").build();
            System.out.println(pr.getErrori()+" me status "+pr.getStatusi());
            return pr;
        }ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(professorList).build();
        System.out.println(pr.getMesazhi()+""+pr.getStatusi());
          return pr;

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
        Professor p = new Professor.ProfessorBuilder(registerh.getPassword(), registerh.getUsername())
                .setName(registerh.getName()).setDegree(registerh.getDegree()).setEmail(registerh.getEmail()).build();
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

   /*@PostMapping("/{sortType}")
    public void sorting(@PathVariable String sortType,  @RequestBody SortHelper sortHelper){
        this.userInterface.sort(sortType,sortHelper);
    }*/

    @GetMapping("/searchDoc/{searchType}")
    public ResponseEntity searchDocument(@PathVariable String searchType, @RequestBody searchHelper sch){
        List<Document> searchDoc =  this.userInterface.searchDoc(searchType, sch.getName()) ;

     return ResponseEntity.ok(searchDoc);
    }



}

