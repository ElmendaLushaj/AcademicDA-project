package com.example.demo.Controller;

import com.example.demo.Helpers.FolderHelper;
import com.example.demo.Helpers.RegisterHelper;
import com.example.demo.Helpers.getModelHelper;
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

    @GetMapping
    public ResponseEntity getAllDocuments(){
        List<Document> documentList=this.profServ.getAllDocuments();
        return ResponseEntity.ok(documentList);

    }
    @PostMapping("/addDoc")
    public void addDoc(@RequestBody saveDocumentHelper sdH){


        Document d = new Document(sdH.getCreationD() , sdH.getDocPath() , sdH.getEditedD(), sdH.getFileSize(),sdH.getName(),sdH.getType(),sdH.getProfID());
        profServ.addDocument(d);
    }
    @PostMapping("/deleteDoc")
    public void deleteDoc(@RequestBody getModelHelper gtm){
         profServ.deleteDoc(gtm.getModelId());
    }

    @PostMapping("/addFolder")
    public  void addFolder(@RequestBody FolderHelper folderh){
        Folder f=new Folder( folderh.getName(),folderh.getProfessorID());
        profServ.addFolder(f);
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
}
