package vttp.ssf.batch4_assessment.models;

import java.io.StringReader;
// import java.util.Date;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Information {

    private String fullName;
    private long birthDate;
    private String email;
    private String phone;
    private int tickets;
    
    public Information() {}
    
    public Information(String fullName, long birthDate, String email, String phone, int tickets) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.tickets = tickets;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public long getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getTickets() {
        return tickets;
    }
    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public String toJsonString() {
        JsonObject j = Json.createObjectBuilder()
                .add("fullName", this.fullName)
                .add("birthDate", this.birthDate)
                .add("email", this.email)
                .add("phone", this.phone)
                .add("tickets", this.tickets)
                .build();
        return j.toString();
    }

    public static Information toJson(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject j = reader.readObject();
        Information info = new Information(j.getString("fullName"), j.getJsonNumber("birthDate").longValue(), j.getString("email"), j.getString("phone"), j.getInt("tickets"));
        return info;
    }
}
