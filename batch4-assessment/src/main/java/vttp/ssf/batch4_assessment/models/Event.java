package vttp.ssf.batch4_assessment.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Event {
    private int eventId;
    private String eventName;
    private int eventSize;
    private long eventDate;
    private int participants;

    public Event() {
    }

    public Event(int eventId, String eventName, int eventSize, long eventDate, int participants) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventSize = eventSize;
        this.eventDate = eventDate;
        this.participants = participants;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventSize() {
        return eventSize;
    }

    public void setEventSize(int eventSize) {
        this.eventSize = eventSize;
    }

    public long getEventDate() {
        return eventDate;
    }

    public void setEventDate(long eventDate) {
        this.eventDate = eventDate;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "Event [eventId=" + eventId + ", eventName=" + eventName + ", eventSize=" + eventSize + ", eventDate="
                + eventDate + ", participants=" + participants + "]";
    }

    public String toJsonString() {
        JsonObject j = Json.createObjectBuilder()
                .add("eventId", this.eventId)
                .add("eventName", this.eventName)
                .add("eventSize", this.eventSize)
                .add("eventDate", this.eventDate)
                .add("participants", this.participants)
                .build();
        return j.toString();
    }

    public static Event toJson(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject j = reader.readObject();
        Event event = new Event(j.getInt("eventId"), j.getString("eventName"), j.getInt("eventSize"), j.getJsonNumber("eventDate").longValue(), j.getInt("participants"));
        return event;
    }
}
