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
import java.util.Optional;

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
    public ProfessorResponse login(@RequestBody LoginHelpers login){

        List<Professor> lista = userInterface.existsUser(login.getUsername());
      //  Professor professor=this.userInterface.login(login.getUsername(),login.getPassword());
//||!userInterface.exists(login.getUsername())
        if(lista.size() == 0){
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("Nuk ekziston perdorues me nje username te till").build();
            System.out.println(pr.getErrori()+" me status "+pr.getStatusi());
            return pr;

        } else {
            Optional<Professor> p2 = userInterface.getByUsername(login.getUsername());
            Professor p = p2.get();
            if(!p.getPassword().equals(login.getPassword())){
                ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("Password i gabuar").build();
                System.out.println(pr.getErrori()+" me status "+pr.getStatusi());
                return pr;

            }else {
                Professor professor = this.userInterface.login(login.getUsername(), login.getPassword());
                ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(professor).build();
                System.out.println(pr.getMesazhi() + "" + pr.getStatusi());
                return pr;
            }
        }
    }




    /*
   @PostMapping("/register",value="/save")
    public void register(@RequestBody RegisterHelper register ) {
        userInterface.register(register.getName(), register.getEmail(), register.getPassword(), register.getDegree(), register.getUsername());

    }*/
    @PostMapping("/registre")
    public ProfessorResponse register(@RequestBody RegisterHelper registerh){
        List<Professor> useri =userInterface.existsUser(registerh.getUsername());
        if(useri.size() ==0) {
            Professor p = new Professor.ProfessorBuilder(registerh.getPassword(), registerh.getUsername())
                    .setName(registerh.getName()).setDegree(registerh.getDegree()).setEmail(registerh.getEmail()).build();
            userInterface.register(p);
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(p).build();
            System.out.println(pr.getMesazhi() + "" + pr.getStatusi());
            return pr;
        }else{
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("Ky username eshte i nxene. Ju lutem zgjedhni nje tjeter!").build();
            System.out.println(pr.getErrori()+" me status "+pr.getStatusi());
            return pr;
        }
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

