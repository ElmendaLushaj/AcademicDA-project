package com.example.demo.Controller;

import com.example.demo.Helpers.FolderHelper;
import com.example.demo.Helpers.getModelHelper;
import com.example.demo.Helpers.getModelHelper2;
import com.example.demo.Helpers.saveDocumentHelper;
import com.example.demo.Model.*;
import com.example.demo.Services.IProfessorService;
import com.example.demo.Services.IUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping( "/api/professor")
public class ProfServController {
    @Autowired
    private IProfessorService profServ;
    @Autowired
    private IUserInterface userInterface;

    @GetMapping
    public ResponseEntity getAllDocuments(){
        List<Document> documentList=this.profServ.getAllDocuments();
        return ResponseEntity.ok(documentList);

    }


    @PostMapping("/deleteDoc")
    public void deleteDoc(@RequestBody getModelHelper gtm){
         profServ.deleteDoc(gtm.getModelId());
    }



    @PostMapping("/addFolder3")
    public  void addFolder2(@RequestBody FolderHelper folderh){
       Optional<Professor> pr=this.userInterface.getProfById(folderh.getProfessorID());
        Professor p = pr.get();

        Folder f=new Folder(folderh.getName(),p);
        profServ.addFolder(f);

    }

    @PostMapping("/addDoc")
    public void addDoc(@RequestBody saveDocumentHelper sdH){
        Optional<Folder> fl = this.profServ.getFoldById(sdH.getFoldId());
        Folder f = fl.get();
        Optional<Professor> pr=this.userInterface.getProfById(sdH.getProfID());
        Professor p = pr.get();



        Document d = new Document(sdH.getCreationD() , sdH.getDocPath() , sdH.getEditedD(), sdH.getFileSize(),sdH.getName(),sdH.getType() ,p , f);
        profServ.addDocument(d);
    }


    @PostMapping("/deleteFold")
    public void deleteFold(@RequestBody getModelHelper gtm){
        profServ.deleteFold(gtm.getModelId());
    }

    @GetMapping("/getDoc")
    public ResponseEntity getDocument(@RequestBody getModelHelper gtm){

        Optional<Document> d =this.profServ.getDocById(gtm.getModelId());
        return ResponseEntity.ok(d);

    }
    @GetMapping("/getFold")
    public ResponseEntity getFolder(@RequestBody getModelHelper gtm){

        Optional<Folder> f =this.profServ.getFoldById(gtm.getModelId());
        return ResponseEntity.ok(f);

    }




    @GetMapping("/getComm")
    public ResponseEntity getComment(@RequestBody getModelHelper gtm){

        Optional<Comment> c =this.profServ.getCommById(gtm.getModelId());
        return ResponseEntity.ok(c);

    }

    @GetMapping("/getApprove")
    public ResponseEntity getApprovement(@RequestBody getModelHelper gtm){

        Optional<Approvement> a =this.profServ.getAppById(gtm.getModelId());
        return ResponseEntity.ok(a);

    }
    @GetMapping("/getFoldByUser")
    public ResponseEntity getFoldByUser(@RequestBody getModelHelper2 gtm2){
        Optional<Professor> profOp = this.userInterface.getByUsername(gtm2.getUsername());
        Professor p = profOp.get();
        int profId =p.getProfId();
        Optional<Folder> f =this.profServ.getFoldByUser(profId);
        return ResponseEntity.ok(f);

    }
}
