package vttp.ssf.batch4_assessment.bootstrap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import vttp.ssf.batch4_assessment.models.Event;
import vttp.ssf.batch4_assessment.services.DatabaseService;

@Component
public class AppBootStrap implements CommandLineRunner {
    
    @Autowired
    private DatabaseService dbServ;
    
    @Value("${json.file.path}")
    private String eventsFile = "events.json";

    @Override
    public void run(String... args) {
        List<Event> events = dbServ.readFile(eventsFile);
        for(int i = 0; i < events.size(); i++) {
            System.out.println(events.get(i).toString());
            dbServ.saveRecord(events.get(i));
        }
    }

}
