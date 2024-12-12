package vttp.ssf.batch4_assessment.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp.ssf.batch4_assessment.models.Event;
import vttp.ssf.batch4_assessment.models.Information;

@Repository
public class RedisRepo {
    @Autowired @Qualifier("redis-0")
    private RedisTemplate<String, String> template;

    public void saveRecord(Event event) {
        String value = event.toJsonString();
        template.opsForHash().put("events", String.valueOf(event.getEventId()), value);
    }

    public long getNumberofEvents() {
        return template.opsForHash().size("events");
    }

    public Event getEvent(Integer index) {
        String value = template.opsForHash().get("events", String.valueOf(index)).toString();
        Event event = Event.toJson(value);
        return event;
    }
    public void saveInfo(Information info) {
        String value = info.toJsonString();
        template.opsForHash().put("info", info.getFullName(), value);
    }
    public void updateEvent(Event event) {
        String value = event.toJsonString();
        template.opsForHash().put("events", String.valueOf(event.getEventId()), value);
    }
}
