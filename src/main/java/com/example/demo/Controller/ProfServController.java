package com.example.demo.Controller;

import com.example.demo.Helpers.FolderHelper;
import com.example.demo.Helpers.RegisterHelper;
import com.example.demo.Helpers.saveDocumentHelper;
import com.example.demo.Model.Document;
import com.example.demo.Model.Folder;
import com.example.demo.Model.Professor;
import com.example.demo.Services.IProfessorService;
import com.example.demo.Services.IUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void register(@RequestBody saveDocumentHelper sdH){


        Document d = new Document(sdH.getCreationD() , sdH.getDocPath() , sdH.getEditedD(), sdH.getFileSize(),sdH.getName(),sdH.getType(),sdH.getProfID());
        profServ.addDocument(d);
    }

    @PostMapping("/addFolder")
    public  void addFolder(@RequestBody FolderHelper folderh){
        Folder f=new Folder( folderh.getName(),folderh.getProfessorID());
        profServ.addFolder(f);
    }
}
