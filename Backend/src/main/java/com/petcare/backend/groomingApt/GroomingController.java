package com.petcare.backend.groomingApt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.SQLException;

import com.jdbc.dao.GroomingAppointmentImp;
import com.jdbc.model.GroomingAppointment;

@CrossOrigin
@RestController
public class GroomingController {
    
    @GetMapping("/groomingapt/{id}")
    private GroomingAppointment getApt(@PathVariable("id")int id) throws SQLException
    {
        GroomingAppointmentImp aptDAO = new GroomingAppointmentImp();
        return aptDAO.getGroomingAppointment(id);
    }

    @GetMapping("/groomingapt")
    private String getA() {
        return "hello";
    }
	
}
