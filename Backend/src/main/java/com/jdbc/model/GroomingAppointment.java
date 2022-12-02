package model;

import java.util.List;

public class GroomingAppointment {
    int aptId;
    String notes;
    String location;
    String date;
    int groomerId;
    int petId;
<<<<<<<< HEAD:backend/src/model/GroomingAppointment.java
    List<Image> photos;
========
    Blob pic;
>>>>>>>> 0410d5ab94c7959a29844bd7e44361227f1c1091:Backend/src/main/java/com/jdbc/model/GroomingAppointment.java

    public GroomingAppointment() {}


    public GroomingAppointment(int aptId, String notes, String location, String date, int petId, int groomerId) {
        this.aptId = aptId;
        this.notes = notes;
        this.location = location;
        this.date = date;
        this.petId = petId;
        this.groomerId = groomerId;
    }

    public int getAptId() {
        return this.aptId;
    }

    public void setAptId(int aptId) {
        this.aptId = aptId;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return this.date;
    }

    public void setAptDate(String date) {
        this.date = date;
    }

    public int getGroomerId() {
        return this.groomerId;
    }

    public void setGroomerId(int groomerId) {
        this.groomerId = groomerId;
    }

    public int getPetId() {
        return this.petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

<<<<<<<< HEAD:backend/src/model/GroomingAppointment.java
========
    public Blob getPic() {
        return this.pic;
    }

    public void setPic(Blob pic) {
        this.pic = pic;
    }

>>>>>>>> 0410d5ab94c7959a29844bd7e44361227f1c1091:Backend/src/main/java/com/jdbc/model/GroomingAppointment.java
    @Override
    public String toString() {
        return "{" +
            " notes='" + getNotes() + "'" +
            ", location='" + getLocation() + "'" +
            ", date='" + getDate() + "'" +
            ", groomerId='" + getGroomerId() + "'" +
            ", petId='" + getPetId() + "'" +
            "}";
    }
    

}
