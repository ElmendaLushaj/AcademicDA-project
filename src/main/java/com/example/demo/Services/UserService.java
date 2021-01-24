package com.example.demo.Services;

import com.example.demo.DAL.DocumentDAO;
import com.example.demo.DAL.FolderDao;
import com.example.demo.DAL.ProfessorDAO;
import com.example.demo.Helpers.getModelHelper2;
import com.example.demo.Helpers.searchHelper;
import com.example.demo.Model.Document;
import com.example.demo.Model.Folder;
import com.example.demo.Model.Professor;
import com.example.demo.Strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserInterface {

    @Autowired
    private ProfessorDAO professorDao;
    @Autowired
    private DocumentDAO documentDao;
    @Autowired
    private FolderDao folderDao;

    @Override
    public List<Professor> getAllProfessors() {
        return this.professorDao.findAll();
    }

    @Override
    public Professor login(String username , String pass){
        Optional<Professor> profOptional = this.professorDao.findProfessorByUsernameAndPassword(username , pass
        );

        if(profOptional == null){
            return null;
        }
        return profOptional.get();
    }
    @Override
    public Optional <Professor> getByUsername(String username){
        Optional<Professor> profUsername = this.professorDao.findProfessorByUsername(username);
        if(profUsername == null){
            return null;
        }
        return profUsername;

    }
  /* @Override
    public void register(String name, String email , String password, String degree, String username){
       Optional registerProf = this.professorDao.registerProfessor(name,email,password, degree,username);

     }*/

    public void register(Professor p){
        professorDao.save(p);
    }
    @Override
    public List<Document>listAll(String keyword){
        if(keyword !=null){
            return documentDao.search(keyword);
        }
        return documentDao.findAll();
    }

  /* @Override
    public void sort(String sortType, SortHelper sortHelper) {

            if(sortType.toLowerCase().equals("NameSort")){
               Sorting(new NameSortStrategy(){
                @Override
                public List<Document> sort(List<Document> documents) {
                    return documentDao.sortByCreationDate();
                }
            });
            }
            else if(sortType.toLowerCase().equals("DateSort")) {

                Sorting(new SortStrategy() {

                    @Override
                    public List<Document> sort(List<Document> documents) {
                        return documentDao.sortByCreationDate();
                    }
                });


            }

        }*/

  /* private void Sorting(SortStrategy nameSortStrategy) {
       nameSortStrategy.sort()
    }*/




    @Override
    public List<Folder> listAllFolder(String name){
        if(name !=null){
            return folderDao.findFoldersByName(name);
        }
        return folderDao.findAll();
    }

    @Override
    public Optional<Professor> getProfById(int folId){
        Optional<Professor> p= professorDao.findById(folId);

            return p;

    }
   @Override
    public List<Document> searchDoc(String searchType , String name){
        if(searchType.equalsIgnoreCase("nameSearch")){
        /* SearchStrategy ns = new NameSearch();
         List<Document> d =ns.search(name);
         if(d ==null){
             return null;
         }
         return d*;/
         */
            List<Document> d =searchDocument(new NameSearch(),name);
            return d;



        }else if(searchType.equalsIgnoreCase("typeSearch")){
           // searchDocument(new TypeSearch(gtm.getUsername()), name);
          /*  SearchStrategy ns = new TypeSearch();
            List<Document> d =ns.search(name);
            if(d==null){
                return null;
            }
            return d;*/
            List<Document> d =searchDocument(new TypeSearch(),name);
            return d;
        }
        List<Document> all = documentDao.findAll();
        return all;

    }
private List<Document> searchDocument(SearchStrategy sch , String name){
       List<Document> list =  sch.search(name);
       return  list;
    }

    @Override
         public Folder getFolderByName(String name){
        Folder f =this.folderDao.findFolderByName(name);
        return f;
    }
  /*  @Override
    public boolean exists(String username){
        if(professorDao.existsByUsername(username)){
            return true;
        }
        else
            return false;
    }

*/

}





