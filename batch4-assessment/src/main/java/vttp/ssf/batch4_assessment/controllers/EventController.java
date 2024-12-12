package vttp.ssf.batch4_assessment.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vttp.ssf.batch4_assessment.models.Event;
import vttp.ssf.batch4_assessment.services.DatabaseService;



@Controller
@RequestMapping
public class EventController {
    
    @Autowired
    private DatabaseService dbServ;

    @GetMapping("")
    public String displayEvents(Model model) {
        List<Event> eventList = new LinkedList<>();
        for(int i = 1; i <= dbServ.getNumberofEvents(); i++) {
            Event e = dbServ.getEvent(i);
            eventList.add(e);
        }
        model.addAttribute("eventList", eventList);
        return "index";
    }
}
