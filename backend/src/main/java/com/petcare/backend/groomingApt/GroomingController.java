package com.petcare.backend.groomingApt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

import com.jdbc.dao.GroomingAppointmentImp;
import com.jdbc.model.GroomingAppointment;

@RestController
public class GroomingController {
    
    
    @GetMapping("/groomingapt/{id}")
    private String getApt(@PathVariable("id")int id) throws SQLException
    {
        GroomingAppointmentImp aptDAO = new GroomingAppointmentImp();
        GroomingAppointment e = aptDAO.getGroomingAppointment(id);
        return e.toString();
    }

    @GetMapping("/groomingapt")
    private String getA() throws SQLException {

        return "hello";
    }
	
}
