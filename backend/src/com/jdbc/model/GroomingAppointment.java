package Backend.src.com.jdbc.model;

import java.sql.Blob;

public class GroomingAppointment {
    int aptId;
    String notes;
    String location;
    String date;
    int groomerId;
    int petId;
    int ownerId;
    Blob pic;

    public GroomingAppointment() {}


    public GroomingAppointment(int aptId, String notes, String location, String date, int petId, int ownerId) {
        this.aptId = aptId;
        this.notes = notes;
        this.location = location;
        this.date = date;
        this.petId = petId;
        this.ownerId = ownerId;
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

    public void setDate(String date) {
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

    public int getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Blob getPic() {
        return this.pic;
    }

    public void setPic(Blob pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "{" +
            " notes='" + getNotes() + "'" +
            ", location='" + getLocation() + "'" +
            ", date='" + getDate() + "'" +
            ", groomerId='" + getGroomerId() + "'" +
            ", petId='" + getPetId() + "'" +
            ", ownerId='" + getOwnerId() + "'" +
            "}";
    }
    

}
