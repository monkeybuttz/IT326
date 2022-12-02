package dao;

import java.sql.SQLException;
import java.util.List;

import model.GroomingAppointment;

public interface GroomingAppointmentDAO {
    
    public int add(GroomingAppointment apt) throws SQLException;

    public void delete(int id) throws SQLException;

    public GroomingAppointment getGroomingAppointment(int id) throws SQLException;

    public List<GroomingAppointment> getAppointments() throws SQLException;
    
    public void update(GroomingAppointment apt) throws SQLException;

    public void favoriteGroom(GroomingAppointment apt) throws SQLException;

    public void unfavoriteGroom(GroomingAppointment apt) throws SQLException;
}
