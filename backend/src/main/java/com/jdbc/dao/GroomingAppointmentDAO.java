package com.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

import com.jdbc.model.GroomingAppointment;

public interface GroomingAppointmentDAO {

    public int add(GroomingAppointment apt) throws SQLException;

    public void delete(int id) throws SQLException;

    public GroomingAppointment getGroomingAppointment(int id) throws SQLException;

    public List<GroomingAppointment> getAppointments() throws SQLException;

    public void update(GroomingAppointment apt) throws SQLException;

    public void favorite(int id) throws SQLException;
    
    public void unfavorite(int id) throws SQLException;
}

