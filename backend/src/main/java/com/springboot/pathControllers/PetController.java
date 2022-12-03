package com.springboot.pathControllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.jdbc.dao.PetImp;
import com.jdbc.model.*;

@RestController
public class PetController {

    PetImp pDAO = new PetImp();


    @GetMapping("/pet/{id}")
    ResponseEntity<Pet> getGroup(@PathVariable int id) throws Exception {
        return ResponseEntity.ok().body(pDAO.getPet(id));
    }
    
    

}
