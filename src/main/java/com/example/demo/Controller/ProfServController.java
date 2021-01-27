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

    //merr te gjitha dokumentet
    @GetMapping
    public ResponseEntity getAllDocuments() {
        List<Document> documentList = this.profServ.getAllDocuments();
        return ResponseEntity.ok(documentList);

    }

    //fshij dokumentin ne baze te id-se
    @PostMapping("/deleteDoc")
    public ProfessorResponse deleteDoc(@RequestBody getModelHelper gtm) {
        Optional <Document> getDoc = this.profServ.getDocById(gtm.getModelId());
        if(getDoc.isPresent()) {
            profServ.deleteDoc(gtm.getModelId());
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(getDoc).build();
            System.out.println(pr2.getMesazhi() + "" + pr2.getStatusi());
            return pr2;

        }else {
            ProfessorResponse pr3 = new ProfessorResponse.ProfessorResponseBuilder(402).setErrorin("There is no document with this ID! ").build();
            System.out.println(pr3.getErrori() + " me status " + pr3.getStatusi());
            return pr3;
        }
    }

    //shto nje folder te ri
    @PostMapping("/addFolder3")
    public ProfessorResponse addFolder2(@RequestBody FolderHelper folderh) {
        Optional<Professor> pr = this.userInterface.getProfById(folderh.getProfessorID());
        List<Folder> fol = this.userInterface.listAllFolder(folderh.getName());
        if (pr.isPresent()) {
            if (fol.size() != 0) {
                ProfessorResponse pr3 = new ProfessorResponse.ProfessorResponseBuilder(402).setErrorin("This folder name is not available, please choose another one! ").build();
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

            ProfessorResponse pr3 = new ProfessorResponse.ProfessorResponseBuilder(402).setErrorin("There is no professor with this ID ").build();
            System.out.println(pr3.getErrori() + " me status " + pr3.getStatusi());
            return pr3;


        }
    }

    public double getProfSpace(Professor p){
        List<Document> lista = this.profServ.getDocByProf(p);
        double hapsira = 0;
        for (int i = 0 ; i < lista.size() ; i++){
            hapsira+=lista.get(i).getFileSize();
        }
        return  hapsira;

    }

    //shto dokument te ri
    @PostMapping("/addDoc")
    public ProfessorResponse addDoc(@RequestBody AddDocumentHelper sdH) {
        if(sdH.getEditedD() == null || sdH.getCreationD() == null || sdH.getFileSize() == 0 || sdH.getName() == ""
        || sdH.getType() == "" || sdH.getPath()== "" || sdH.getFolder() == 0){
            ProfessorResponse pr3 = new ProfessorResponse.ProfessorResponseBuilder(402).setErrorin("You should fill all the below inputs ").build();
            System.out.println(pr3.getErrori() + " me status " + pr3.getStatusi());
            return pr3;
        }else {
            Optional<Folder> fl = this.profServ.getFoldById(sdH.getFolder());
            if(fl.isPresent()) {
                 List<Document> list = this.profServ.getDocByName(sdH.getName());
                 if(list.size() == 0){
                     Folder f = fl.get();
                     Professor p = f.getProfessor();
                     //Prov
                    double hapsiraa = this.getProfSpace(p);

                    if(sdH.getFileSize() <= 15000-hapsiraa){
                     //Prov
                     Document d = new Document(sdH.getCreationD(), sdH.getPath(), sdH.getEditedD(), sdH.getFileSize(), sdH.getName(), sdH.getType(), p, f);
                     profServ.addDocument(d);
                     ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(d).build();
                     System.out.println(pr2.getMesazhi() + "" + pr2.getStatusi());
                     return pr2;}
                    else{
                        ProfessorResponse pr3 = new ProfessorResponse.ProfessorResponseBuilder(402).setErrorin("You don't have enough space to add this document").build();
                        System.out.println(pr3.getErrori() + " me status " + pr3.getStatusi());
                        return pr3;
                    }
                 }else {
                     ProfessorResponse pr3 = new ProfessorResponse.ProfessorResponseBuilder(402).setErrorin("This document name is not available, please choose another one!").build();
                     System.out.println(pr3.getErrori() + " me status " + pr3.getStatusi());
                     return pr3;
                 }

            }
            else {
                ProfessorResponse pr3 = new ProfessorResponse.ProfessorResponseBuilder(402).setErrorin("There is no folder with this ID").build();
                System.out.println(pr3.getErrori() + " me status " + pr3.getStatusi());
                return pr3;
            }
        }
    }



    //shfaq folderat ne baze te perdoruesit
    @GetMapping("/getFoldByUser/{username}")
    public ProfessorResponse getFoldByUser(@PathVariable String username){
        List<Professor> lista = this.userInterface.existsUser(username);
        if(username == ""){
            ProfessorResponse pr3 = new ProfessorResponse.ProfessorResponseBuilder(402).setErrorin("You haven't specify your username ").build();
            System.out.println(pr3.getErrori()+" me status "+pr3.getStatusi());
            return pr3;
        }else if(lista.size() == 0){
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("There is no professor registered with this username: "+username).build();
            System.out.println(pr2.getErrori()+" me status "+pr2.getStatusi());
            return pr2;
        }else{
            Optional<Professor> profOp = this.userInterface.getByUsername(username);
        Professor p = profOp.get();
        List<Folder> f =this.profServ.getFoldByUser(p);
        if(f.size() == 0){
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("There is no folder that belongs to: "+username).build();
            System.out.println(pr2.getErrori()+" me status "+pr2.getStatusi());
            return pr2;
        }else {
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(f).build();
            System.out.println(pr.getMesazhi() + "" + pr.getStatusi());
            return pr;

             }
        }
    }

    //shfaq numrin total te dokumenteve te nje perdoruesi
    @GetMapping("/numberOfDoc/{useri}")
    public ProfessorResponse totalDocuments(@PathVariable String  useri){
        List<Professor> lista =this.userInterface.existsUser(useri);
        if(lista.size() == 0){
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("There is no professor with this  username: "+useri).build();
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

    //shfaq numrin total te foldereve te nje perdoruesi
    @GetMapping("/numberOfFolders/{useri}")
    public ProfessorResponse totalFolders(@PathVariable String useri){
        List<Professor> lista = this.userInterface.existsUser(useri);
        if(lista.size() == 0){
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("There is no professor with this username: "+useri).build();
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

    //fshij nje folder ne baze te id-se se tij
    @PostMapping("/deleteFold")
    public ProfessorResponse deleteFold(@RequestBody getModelHelper gtm){
        Optional<Folder> getf = this.profServ.getFoldById(gtm.getModelId());
        if(getf.isPresent()){
            profServ.deleteFold(gtm.getModelId());
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(getf).build();
            System.out.println(pr.getMesazhi() + "" + pr.getStatusi());
            return pr;
        }else {
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("There is no folder with this ID").build();
            System.out.println(pr2.getErrori()+" me status "+pr2.getStatusi());
            return pr2;
        }
    }

    //shfaq dokumentet ne baze te id-se
    @GetMapping("/getDoc/{docId}")
    public ResponseEntity getDocument(@PathVariable int docId){
        Optional<Document> d =this.profServ.getDocById(docId);
        return ResponseEntity.ok(d);
    }

    //shfaq folderet ne baze te id-se
    @GetMapping("/getFold")
    public ResponseEntity getFolder(@RequestBody getModelHelper gtm){

        Optional<Folder> f =this.profServ.getFoldById(gtm.getModelId());
        return ResponseEntity.ok(f);

    }

    //shfaq komentet ne baze te id-se
    @GetMapping("/getComm")
    public ResponseEntity getComment(@RequestBody getModelHelper gtm){

        Optional<Comment> c =this.profServ.getCommById(gtm.getModelId());
        return ResponseEntity.ok(c);

    }

    //shfaq aprovimet ne baze te id-se
    @GetMapping("/getApprove")
    public ResponseEntity getApprovement(@RequestBody getModelHelper gtm){

        Optional<Approvement> a =this.profServ.getAppById(gtm.getModelId());
        return ResponseEntity.ok(a);

    }

    //shfaq dokumente ne baze te foldereve ku gjenden
    @GetMapping("/getDocByFolder/{Foldname}")
    public ProfessorResponse getDocByFold(@PathVariable String Foldname){
        Folder f = userInterface.getFolderByName(Foldname);
        int id = f.getFolderID();
        Optional <Folder> f2 = profServ.getFoldById(id);
        Folder fo =f2.get();
        List<Document> doc = profServ.docByFolder(fo);
        if(doc.size() == 0){
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("Folder with name: "+Foldname+" is empty").build();
            System.out.println(pr2.getErrori()+" me status "+pr2.getStatusi());
            return pr2;
        }else {
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(doc).build();
            System.out.println(pr.getMesazhi() + "" + pr.getStatusi());
            return pr;

        }
    }


    //shfaq te gjitha komentet si nje list
    @GetMapping("/getALLComm")
    public ProfessorResponse getAllComments(){
        List<Comment> listCom=this.profServ.getAllComments();
        if(listCom.size() == 0){
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("There are no comments").build();
            System.out.println(pr2.getErrori()+" me status "+pr2.getStatusi());
            return pr2;
        }else {
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(listCom).build();
            System.out.println(pr.getMesazhi() + "" + pr.getStatusi());
            return pr;
        }

    }
    //shfaq komentet ne baze te dokumentit perkates
    @GetMapping("/commentByDoc/{docId}")
    public ProfessorResponse getCommOfDoc(@PathVariable int docId) {
        Optional<Document> d = profServ.getDocById(docId);
        Document dd =d.get();
        List<Comment> listaC = profServ.getComByDoc(dd);
        if (listaC.size() == 0) {
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("There are no comments for this document").build();
            System.out.println(pr2.getErrori() + " me status " + pr2.getStatusi());
            return pr2;

        }else {
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(listaC).build();
            System.out.println(pr.getMesazhi() + "" + pr.getStatusi());
            return pr;
        }
    }

    //shfaq aprovimet ne baze te dokumentit perkates
    @GetMapping("/approveByDoc/{docId}")
    public ProfessorResponse getApproveOfDoc(@PathVariable int docId){
        Optional<Document> a = profServ.getDocById(docId);
        Document aa = a.get();
        List<Approvement> listaA =profServ.getAppByDoc(aa);

        if (listaA.size() == 0) {
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("This document has not yet been approved/rejected").build();
            System.out.println(pr2.getErrori() + " me status " + pr2.getStatusi());
            return pr2;

        }else {
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(listaA).build();
            System.out.println(pr.getMesazhi() + "" + pr.getStatusi());
            return pr;
        }
    }

    //shfaq sasin e hapsires qe i ka mbetur nje profe
    @GetMapping("/space/{useri}")
    public ProfessorResponse getSpaceByProf(@PathVariable String useri){
        Optional<Professor> profa = this.userInterface.getByUsername(useri);
        if(profa.isPresent()) {
            Professor p = profa.get();
            List<Document> dokumentet = this.profServ.getDocByProf(p);
            double hapsira = 0;
            for (int i = 0 ; i < dokumentet.size() ; i++){
                hapsira+=dokumentet.get(i).getFileSize();
            }
            ProfessorResponse pr = new ProfessorResponse.ProfessorResponseBuilder<>(201).setMesazhin("List e suksesshme").setData(hapsira).build();
            System.out.println(pr.getMesazhi() + "" + pr.getStatusi());
            return pr;

        }else {
            ProfessorResponse pr2 = new ProfessorResponse.ProfessorResponseBuilder(401).setErrorin("There is no professor with this username").build();
            System.out.println(pr2.getErrori() + " me status " + pr2.getStatusi());
            return pr2;
        }

    }
}
