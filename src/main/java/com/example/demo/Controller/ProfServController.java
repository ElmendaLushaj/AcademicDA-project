package com.example.demo.Controller;

import com.example.demo.Helpers.AddDocumentHelper;
import com.example.demo.Helpers.FolderHelper;
import com.example.demo.Helpers.getModelHelper;
import com.example.demo.Helpers.getModelHelper2;
import com.example.demo.Model.*;
import com.example.demo.Services.IProfessorService;
import com.example.demo.Services.IUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
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
    public void addDoc(@RequestBody AddDocumentHelper sdH){
       Optional<Folder> fl = this.profServ.getFoldById(sdH.getFolder());
        Folder f = fl.get();
       //Optional<Professor> pr=this.userInterface.getProfById(sdH.getProfID());
        // professor p = pr.get();
       // Folder f = getFold(sdH.getFoldId());
        Professor p = f.getProfessor();


        Document d = new Document(sdH.getCreationD() , sdH.getPath() , sdH.getEditedD(), sdH.getFileSize(),sdH.getName(),sdH.getType(), p , f);
        profServ.addDocument(d);
    }

    @GetMapping("/getFoldByUser/{username}")
    public ResponseEntity getFoldByUser(@PathVariable String username){
        Optional<Professor> profOp = this.userInterface.getByUsername(username);
        Professor p = profOp.get();
      //  int profId =p.getProfId();
        List<Folder> f =this.profServ.getFoldByUser(p);
        return ResponseEntity.ok(f);

    }

    @GetMapping("/numberOfDoc/{useri}")
    public ResponseEntity totalDocuments(@PathVariable String  useri){
        Optional<Professor> profOp = this.userInterface.getByUsername(useri);
        Professor p =profOp.get();
        int nrDoc =p.getDoc().size();
        return  ResponseEntity.ok(nrDoc);

    }


    @GetMapping("/numberOfFolders/{useri}")
    public ResponseEntity totalFolders(@PathVariable String useri){
        Optional<Professor> profOp = this.userInterface.getByUsername(useri);
        Professor p =profOp.get();
        int nrFold =p.getFold().size();
        return  ResponseEntity.ok(nrFold);

    }

    @PostMapping("/deleteFold")
    public void deleteFold(@RequestBody getModelHelper gtm){
        profServ.deleteFold(gtm.getModelId());
    }

    @GetMapping("/getDoc/{docId}")
    public ResponseEntity getDocument(@PathVariable int docId){

        Optional<Document> d =this.profServ.getDocById(docId);
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
    //per front



    @GetMapping("/getDocByFolder/{Foldname}")
    public ResponseEntity getDocByFold(@PathVariable String Foldname){
        Folder f = userInterface.getFolderByName(Foldname);
        int id = f.getFolderID();
        Optional <Folder> f2 = profServ.getFoldById(id);
        Folder fo =f2.get();
        List<Document> doc = profServ.docByFolder(fo);
        return ResponseEntity.ok(doc);
    }
}
