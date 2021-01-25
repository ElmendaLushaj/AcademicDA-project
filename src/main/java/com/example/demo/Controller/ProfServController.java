package com.example.demo.Controller;

import com.example.demo.Helpers.*;
import com.example.demo.Model.*;
import com.example.demo.Services.IProfessorService;
import com.example.demo.Services.IUserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
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
    public ResponseEntity getAllDocuments() {
        List<Document> documentList = this.profServ.getAllDocuments();
        return ResponseEntity.ok(documentList);

    }

    @PostMapping("/deleteDoc")
    public ProfessorResponse deleteDoc(@RequestBody getModelHelper gtm) {
        Optional <Document> getDoc = this.profServ.getDocById(gtm.getModelId());
        if(getDoc.isPresent()) {
            profServ.deleteDoc(gtm.getModelId());
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(getDoc).build();
            System.out.println(pr2.getMesazhi() + "" + pr2.getStatusi());
            return pr2;

        }else {
            ProfessorResponse pr3 = new ProfessorResponse.ProfessorResponseBuilder(402).setErrorin("Nuk ekziston nje dokument me id te till! ").build();
            System.out.println(pr3.getErrori() + " me status " + pr3.getStatusi());
            return pr3;
        }
    }

    @PostMapping("/addFolder3")
    public ProfessorResponse addFolder2(@RequestBody FolderHelper folderh) {
        Optional<Professor> pr = this.userInterface.getProfById(folderh.getProfessorID());
        List<Folder> fol = this.userInterface.listAllFolder(folderh.getName());
        if (pr.isPresent()) {
            if (fol.size() != 0) {
                ProfessorResponse pr3 = new ProfessorResponse.ProfessorResponseBuilder(402).setErrorin("Ekziston nje foler me emer te till, ju lutem zgjedhni nje emer tjeter! ").build();
                System.out.println(pr3.getErrori() + " me status " + pr3.getStatusi());
                return pr3;
            } else {
                Professor p = pr.get();
                Folder f = new Folder(folderh.getName(), p);
                profServ.addFolder(f);
                ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(f).build();
                System.out.println(pr2.getMesazhi() + "" + pr2.getStatusi());
                return pr2;
            }
        } else {

            ProfessorResponse pr3 = new ProfessorResponse.ProfessorResponseBuilder(402).setErrorin("Nuk ekziston je profesor me id te till ").build();
            System.out.println(pr3.getErrori() + " me status " + pr3.getStatusi());
            return pr3;


        }
    }

    @PostMapping("/addDoc")
    public void addDoc(@RequestBody AddDocumentHelper sdH) {
        Optional<Folder> fl = this.profServ.getFoldById(sdH.getFolder());
                Folder f = fl.get();
                Professor p = f.getProfessor();
                Document d = new Document(sdH.getCreationD(), sdH.getPath(), sdH.getEditedD(), sdH.getFileSize(), sdH.getName(), sdH.getType(), p, f);
                profServ.addDocument(d);

    }





    @GetMapping("/getFoldByUser/{username}")
    public ProfessorResponse getFoldByUser(@PathVariable String username){

        List<Professor> lista = this.userInterface.existsUser(username);
        if(username == null){
            ProfessorResponse pr3 = new ProfessorResponse.ProfessorResponseBuilder(402).setErrorin("Nuk keni specifikuar emrin e perdoruesit ").build();
            System.out.println(pr3.getErrori()+" me status "+pr3.getStatusi());
            return pr3;
        }else if(lista.size() == 0){
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("Nuk ekziston nje profesor i regjistruar me username: "+username).build();
            System.out.println(pr2.getErrori()+" me status "+pr2.getStatusi());
            return pr2;
        }else{
            Optional<Professor> profOp = this.userInterface.getByUsername(username);
        Professor p = profOp.get();
      //  int profId =p.getProfId();
        List<Folder> f =this.profServ.getFoldByUser(p);
        if(f.size() == 0){
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("Nuk ekziston nje list me Folder te profesorit me username "+username).build();
            System.out.println(pr2.getErrori()+" me status "+pr2.getStatusi());
            return pr2;
        }else {
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(f).build();
            System.out.println(pr.getMesazhi() + "" + pr.getStatusi());
            return pr;

        }
        }


    }

    @GetMapping("/numberOfDoc/{useri}")
    public ProfessorResponse totalDocuments(@PathVariable String  useri){
        List<Professor> lista =this.userInterface.existsUser(useri);
        if(lista.size() == 0){
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("Nuk ekziston profesor me username te till: "+useri).build();
            System.out.println(pr2.getErrori()+" me status "+pr2.getStatusi());
            return pr2;
        }else {
            Optional<Professor> profOp = this.userInterface.getByUsername(useri);
            Professor p = profOp.get();
            int nrDoc = p.getDoc().size();
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(nrDoc).build();
            System.out.println(pr.getMesazhi() + "" + pr.getStatusi());
            return pr;
        }

    }


    @GetMapping("/numberOfFolders/{useri}")
    public ProfessorResponse totalFolders(@PathVariable String useri){
        List<Professor> lista = this.userInterface.existsUser(useri);
        if(lista.size() == 0){
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("Nuk ekziston profesor me username te till: "+useri).build();
            System.out.println(pr2.getErrori()+" me status "+pr2.getStatusi());
            return pr2;
        }
        else{
        Optional<Professor> profOp = this.userInterface.getByUsername(useri);
        Professor p =profOp.get();
        int nrFold =p.getFold().size();
        ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(nrFold).build();
        System.out.println(pr.getMesazhi() + "" + pr.getStatusi());
        return pr;
        }

    }

    @PostMapping("/deleteFold")
    public ProfessorResponse deleteFold(@RequestBody getModelHelper gtm){
        Optional<Folder> getf = this.profServ.getFoldById(gtm.getModelId());
        if(getf.isPresent()){
            profServ.deleteFold(gtm.getModelId());
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(getf).build();
            System.out.println(pr.getMesazhi() + "" + pr.getStatusi());
            return pr;

        }else {
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("Nuk ekziston folder me id te till").build();
            System.out.println(pr2.getErrori()+" me status "+pr2.getStatusi());
            return pr2;
        }

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




    @GetMapping("/getDocByFolder/{Foldname}")
    public ProfessorResponse getDocByFold(@PathVariable String Foldname){
        Folder f = userInterface.getFolderByName(Foldname);
        int id = f.getFolderID();
        Optional <Folder> f2 = profServ.getFoldById(id);
        Folder fo =f2.get();
        List<Document> doc = profServ.docByFolder(fo);
        if(doc.size() == 0){
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("Folderi me emrin: "+Foldname+" eshte i zbrazet").build();
            System.out.println(pr2.getErrori()+" me status "+pr2.getStatusi());
            return pr2;
        }else {
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(doc).build();
            System.out.println(pr.getMesazhi() + "" + pr.getStatusi());
            return pr;

        }
    }



    @GetMapping("/getALLComm")
    public ProfessorResponse getAllComments(){
        List<Comment> listCom=this.profServ.getAllComments();
        if(listCom.size() == 0){
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("Nuk ekziston nje list me Komente").build();
            System.out.println(pr2.getErrori()+" me status "+pr2.getStatusi());
            return pr2;
        }else {
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(listCom).build();
            System.out.println(pr.getMesazhi() + "" + pr.getStatusi());
            return pr;
        }

    }
    @GetMapping("/commentByDoc/{docId}")
    public ProfessorResponse getCommOfDoc(@PathVariable int docId) {
        Optional<Document> d = profServ.getDocById(docId);
        Document dd =d.get();
        List<Comment> listaC = profServ.getComByDoc(dd);
        if (listaC.size() == 0) {
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("Nuk ekziston nje list me Komente per kete dokument").build();
            System.out.println(pr2.getErrori() + " me status " + pr2.getStatusi());
            return pr2;

        }else {
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(listaC).build();
            System.out.println(pr.getMesazhi() + "" + pr.getStatusi());
            return pr;
        }
    }

    @GetMapping("/approveByDoc/{docId}")
    public ProfessorResponse getApproveOfDoc(@PathVariable int docId){
        Optional<Document> a = profServ.getDocById(docId);
        Document aa = a.get();
        List<Approvement> listaA =profServ.getAppByDoc(aa);

        if (listaA.size() == 0) {
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("Dokumenti ende nuk eshte aprovuar/refuzuar").build();
            System.out.println(pr2.getErrori() + " me status " + pr2.getStatusi());
            return pr2;

        }else {
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(listaA).build();
            System.out.println(pr.getMesazhi() + "" + pr.getStatusi());
            return pr;
        }

    }


}
