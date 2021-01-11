package com.example.demo.Controller;

import com.example.demo.Helpers.FolderHelper;
import com.example.demo.Model.Folder;
import com.example.demo.Services.IProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/folder")
public class FolderServController {
    @Autowired
    private IProfessorService professorInterface;
    @PostMapping("/addFolder")
    public  void addFolder(@RequestBody FolderHelper folderh){
        Folder f=new Folder( folderh.getName(),folderh.getProfessor());
        professorInterface.addFolder(f);
    }

}
