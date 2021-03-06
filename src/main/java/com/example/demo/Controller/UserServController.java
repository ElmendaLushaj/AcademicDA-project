package com.example.demo.Controller;

import com.example.demo.Helpers.*;
import com.example.demo.Model.Document;
import com.example.demo.Model.Professor;
import com.example.demo.Services.IUserInterface;
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


     //shfaq te gjith perdoruesit/profesoret
    @GetMapping("/getALLProf")
    public ProfessorResponse getAllProfessors(){
        List<Professor> professorList=this.userInterface.getAllProfessors();
        if(professorList.size() == 0){
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("There is not a list with Professor's").build();
            System.out.println(pr.getErrori()+" me status "+pr.getStatusi());
            return pr;
        }ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(professorList).build();
        System.out.println(pr.getMesazhi()+""+pr.getStatusi());
          return pr;

    }
    //kyqja ne llogari e perdorusve
    @PostMapping("/login")
    public ProfessorResponse login(@RequestBody LoginHelpers login){
        List<Professor> lista = userInterface.existsUser(login.getUsername());
        if(lista.size() == 0){
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("There is no user with this username!").build();
            System.out.println(pr.getErrori()+" me status "+pr.getStatusi());
            return pr;

        } else {
            Optional<Professor> p2 = userInterface.getByUsername(login.getUsername());
            Professor p = p2.get();
            if(!p.getPassword().equals(login.getPassword())){
                ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("Incorrect password!").build();
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

    //shtimi i perdoruesve
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
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("This username is not available, please choose another one!!").build();
            System.out.println(pr.getErrori()+" me status "+pr.getStatusi());
            return pr;
        }
    }
   /*  //kerkimi i foldereve
     @GetMapping("/searchFolder")
    public ResponseEntity listAllFolder(@RequestBody searchHelper sh){
        List<Folder> folders = this.userInterface.listAllFolder(sh.getName());
        return ResponseEntity.ok(folders);
     }
    @GetMapping("/searchDocument")
    public ResponseEntity listAllDocuments(@RequestBody searchHelper sh){
        List<Document> documents = this.userInterface.listAll(sh.getName());
        return ResponseEntity.ok(documents);
    }*/


    //kerkimi i dokumenteve ne baze te emrit ose tipit/llojit
    @GetMapping("/searchDoc/{searchType}/{name}")
    public ResponseEntity searchDocument(@PathVariable String searchType, @PathVariable String name){
        List<Document> searchDoc =  this.userInterface.searchDoc(searchType, name) ;
        return ResponseEntity.ok(searchDoc);
    }

    //sortimi i dokumenteve ne baze te emrit ose dates se krijimit (A-Z)
    @GetMapping("/sortDoc/{sortType}")
    public  ResponseEntity sortDocument(@PathVariable String sortType){
        List<Document>sortDoc=this.userInterface.sortDoc(sortType);
        return ResponseEntity.ok(sortDoc);
    }
}

