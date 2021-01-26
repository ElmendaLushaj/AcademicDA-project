package com.example.demo.Services;
import com.example.demo.DAL.DocumentRepository;
import com.example.demo.DAL.FolderRepository;
import com.example.demo.DAL.ProfessorRepository;
import com.example.demo.Model.Document;
import com.example.demo.Model.Folder;
import com.example.demo.Model.Professor;
import com.example.demo.Services.Strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserInterface {

    @Autowired
    private ProfessorRepository professorRepo;
    @Autowired
    private DocumentRepository documentRepo;
    @Autowired
    private FolderRepository folderRepo;
    @Autowired
    private NameSearch nameSearch;
    @Autowired
    private TypeSearch typeSearch;
    @Autowired
    private NameSort nameSort;
    @Autowired
    private DateSort dateSort;


    @Override
    public List<Professor> getAllProfessors() {
        return this.professorRepo.findAll();
    }

    @Override
    public Professor login(String username , String pass){
        Optional<Professor> profOptional = this.professorRepo.findProfessorByUsernameAndPassword(username , pass
        );

        if(profOptional == null){
            return null;
        }
        return profOptional.get();
    }

    @Override
    public Optional <Professor> getByUsername(String username){
        Optional<Professor> profUsername = this.professorRepo.findProfessorByUsername(username);
        if(profUsername == null){
            return null;
        }
        return profUsername;

    }
    @Override
    public void register(Professor p){
        professorRepo.save(p);
    }

    @Override
    public List<Document>listAll(String keyword){
        if(keyword !=null){
            return documentRepo.search(keyword);
        }
        return documentRepo.findAll();
    }

    @Override
    public List<Folder> listAllFolder(String name){
        if(name !=null){
            return folderRepo.findFoldersByName(name);
        }
        return folderRepo.findAll();
    }

    @Override
    public Optional<Professor> getProfById(int folId){
        Optional<Professor> p= professorRepo.findById(folId);

            return p;
    }

   @Override
    public List<Document> searchDoc(String searchType , String name){
        if(searchType.equalsIgnoreCase("nameSearch")){

            List<Document> d =searchDocument(this.nameSearch,name);
            return d;

        }else if(searchType.equalsIgnoreCase("typeSearch")){

            List<Document> d =searchDocument(this.typeSearch,name);
            return d;
        }
        List<Document> all = documentRepo.findAll();
        return all;

    }
    private List<Document> searchDocument(SearchStrategy sch , String name){
       List<Document> list =  sch.search(name);
       return  list;
    }


    @Override
    public List<Document> sortDoc(String sortType){
        if(sortType.equalsIgnoreCase("nameSort")){
            List<Document> documentList=sortDocument(this.nameSort);
            return documentList;
        }
        else if (sortType.equalsIgnoreCase("dateSort")){
            List<Document>documentList=sortDocument(this.dateSort);
            return documentList;
        }
        List<Document>all=documentRepo.findAll();
        return all;
    }



    private  List<Document> sortDocument(SortStrategy st){
        List<Document>list=st.sort();
        return list;
    }

    @Override
         public Folder getFolderByName(String name){
        Folder f =this.folderRepo.findFolderByName(name);
        return f;
    }

    @Override
    public List<Professor> existsUser(String username){
        List <Professor> profa = professorRepo.existsByUsername2(username);
        return profa;
    }
}





