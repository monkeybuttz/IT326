package com.jdbc.main;

import java.sql.SQLException;
import java.util.List;

import com.jdbc.dao.GroomingAppointmentImp;
import com.jdbc.model.GroomingAppointment;

public class Driver {
    public static void main(String[] args) throws SQLException {
        GroomingAppointment groom = new GroomingAppointment();
        groom.setGroomerId(001);
        groom.setPetId(002);
        groom.setLocation("Grayslake, IL");
        groom.setAptDate("5/16/2022");
        groom.setNotes("Crazy ahh dawg");
        GroomingAppointmentImp aptDAO = new GroomingAppointmentImp();
        aptDAO.add(groom);
        GroomingAppointment e = aptDAO.getGroomingAppointment(1);
        System.out.println(e.getAptId() + " " + e.getLocation() + " " + e.getDate() + " " + e.getNotes());
        List<GroomingAppointment> ls = aptDAO.getAppointments();
        for (GroomingAppointment allApts : ls) {
            System.out.println(allApts);
        }

        GroomingAppointment tempApt = aptDAO.getGroomingAppointment(1);
        tempApt.setLocation("Bloomington, IL");
        aptDAO.update(tempApt);
    }
}