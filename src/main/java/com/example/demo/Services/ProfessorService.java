package com.example.demo.Services;
import com.example.demo.DAL.*;
import com.example.demo.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService implements IProfessorService{
    @Autowired
    private DocumentRepository docRepo;
    @Autowired
    private FolderRepository folRepo;
    @Autowired
    private CommentRepository commRepo;
    @Autowired
    private ApprovementRepository appRepo;


    @Override
    public List<Document> getAllDocuments() {

        return this.docRepo.findAll();
    }

    @Override
    public List<Comment> getAllComments() {

        return this.commRepo.findAll();
    }

    @Override
    public void addDocument(Document d) {

        docRepo.save(d);
    }

    @Override
    public  List<Folder>getAllFolders(){
        return this.folRepo.findAll();
    }

    @Override
    public void addFolder(Folder d) {

        folRepo.save(d);
    }

    @Override
    public Optional<Document> getDocById(int docId){
       Optional<Document> d= docRepo.findById(docId);
       return d;

    }

    @Override
    public Optional<Folder> getFoldById(int folId){
        Optional<Folder> f= folRepo.findById(folId);
        return f;

    }

    @Override
    public Optional<Comment> getCommById(int comId){
        Optional<Comment> c= commRepo.findById(comId);
        return c;

    }

    @Override
    public Optional<Approvement> getAppById(int appId){
        Optional<Approvement> a= appRepo.findById(appId);
        return a;

    }
    @Override
    public List<Folder> getFoldByUser(Professor profId){
        List<Folder> f= folRepo.getFolderByUser(profId);
        return f;

    }
    @Override
    public void deleteDoc(int id){
        docRepo.deleteById(id);
    }

    @Override
    public void deleteFold(int id){
        folRepo.deleteById(id);
    }

    @Override
    public List<Document> docByFolder(Folder foldid){
        List<Document> docFolder = docRepo.docByFolder(foldid);
        return docFolder;
    }
    @Override
    public List<Comment> getComByDoc(Document docId){
        List <Comment> comDoc = commRepo.findCommentByDoc(docId);
        return comDoc;
    }

    @Override
    public List<Approvement> getAppByDoc(Document docId){
        List<Approvement> appDoc = appRepo.findApprovementByDoc(docId);
        return appDoc;
    }
    @Override
    public List<Document> getDocByName(String name){
        List<Document> lista = docRepo.search(name);
        return lista;
    }
}
