package com.example.demo.Services;

import com.example.demo.DAL.ApprovementDAO;
import com.example.demo.DAL.CommentDAO;
import com.example.demo.DAL.DocumentDAO;
import com.example.demo.DAL.FolderDao;
import com.example.demo.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService implements IProfessorService{
    @Autowired
    private DocumentDAO docDao;
    @Autowired
    private FolderDao folDao;
    @Autowired
    private CommentDAO commDao;
    @Autowired
    private ApprovementDAO appDao;

    private Document doc;
    @Override
    public List<Document> getAllDocuments() {

        return this.docDao.findAll();
    }
    @Override
    public List<Comment> getAllComments() {

        return this.commDao.findAll();
    }


    @Override
    public void addDocument(Document d) {

        docDao.save(d);
    }
    @Override
    public  List<Folder>getAllFolders(){
        return this.folDao.findAll();
    }

    @Override
    public void addFolder(Folder d) {

        folDao.save(d);
    }

    @Override
    public Optional<Document> getDocById(int docId){
       Optional<Document> d= docDao.findById(docId);
       return d;

    }

    @Override
    public Optional<Folder> getFoldById(int folId){
        Optional<Folder> f= folDao.findById(folId);
        return f;

    }

    @Override
    public Optional<Comment> getCommById(int comId){
        Optional<Comment> c= commDao.findById(comId);
        return c;

    }

    @Override
    public Optional<Approvement> getAppById(int appId){
        Optional<Approvement> a= appDao.findById(appId);
        return a;

    }
    @Override
    public List<Folder> getFoldByUser(Professor profId){
        List<Folder> f= folDao.getFolderByUser(profId);
        return f;

    }
    @Override
    public void deleteDoc(int id){
        docDao.deleteById(id);
    }
    @Override
    public void deleteFold(int id){
        folDao.deleteById(id);
    }

    @Override
    public List<Document> docByFolder(Folder foldid){
        List<Document> docFolder = docDao.docByFolder(foldid);
        return docFolder;
    }
}
