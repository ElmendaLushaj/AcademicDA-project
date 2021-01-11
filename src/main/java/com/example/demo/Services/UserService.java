package com.example.demo.Services;

import com.example.demo.DAL.DocumentDAO;
import com.example.demo.DAL.ProfessorDAO;
import com.example.demo.Model.Document;
import com.example.demo.Model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserInterface {

  @Autowired
    private ProfessorDAO professorDao;
  private DocumentDAO documentDao;

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


}



