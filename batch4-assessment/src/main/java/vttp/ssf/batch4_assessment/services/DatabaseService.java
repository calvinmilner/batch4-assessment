package vttp.ssf.batch4_assessment.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.ssf.batch4_assessment.models.Event;
import vttp.ssf.batch4_assessment.models.Information;
import vttp.ssf.batch4_assessment.repositories.RedisRepo;

@Service
public class DatabaseService {
    
    @Autowired
    private RedisRepo repo;

    public List<Event> readFile(String fileName) {
        List<Event> events = new LinkedList<>();
        // Change FileReader to InputStreamReader to prevent containerization errors
        // .getInputStream() for the resource
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String eventsJson = "";
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    line = br.readLine();
                }
                eventsJson = sb.toString();
            } finally {
                br.close();
            }
            JsonReader eventsReader = Json.createReader(new StringReader(eventsJson));
            JsonArray eventsArr = eventsReader.readArray();
            for (int i = 0; i < eventsArr.size(); i++) {
                JsonObject e = eventsArr.getJsonObject(i);
                Event event = new Event(e.getInt("eventId"), e.getString("eventName"), e.getInt("eventSize"),
                        e.getJsonNumber("eventDate").longValue(), e.getInt("participants"));
                events.add(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }

    public void saveRecord(Event event) {
        repo.saveRecord(event);
    }

    public long getNumberofEvents() {
        return repo.getNumberofEvents();
    }
    public Event getEvent(Integer id) {
        return repo.getEvent(id);
    }
    public void saveInfo(Information info) {
        repo.saveInfo(info);
    }
    public void updateEvent(Event event) {
        repo.updateEvent(event);
    }
}
