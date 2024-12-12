package vttp.ssf.batch4_assessment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vttp.ssf.batch4_assessment.models.Event;
import vttp.ssf.batch4_assessment.models.Information;
import vttp.ssf.batch4_assessment.services.DatabaseService;

@Controller
@RequestMapping
public class RegistrationController {

    @Autowired
    private DatabaseService dbServ;

    @GetMapping("/register/{id}")
    public String displayRegistration(Model model, @PathVariable int id) {
        Event e = dbServ.getEvent(id);
        Information info = new Information();
        model.addAttribute("event", e);
        model.addAttribute("information", info);
        return "eventregister";

    }

    @PostMapping("/process")
    public ModelAndView processRegistration(@RequestParam int id,
            @ModelAttribute("information") Information info, BindingResult bindings) {
        ModelAndView mav = new ModelAndView();
        Event e = dbServ.getEvent(id);
        // if (bindings.hasErrors())
        // mav.setViewName("eventregister");
        // else {
        // if (age < 21 ) {
        // mav.setViewName("errorregistration");
        // mav.addObject("reason", "Your request has failed as only 21 year old and
        // above can register");
        // }
        if ((info.getTickets() + e.getParticipants()) > e.getEventSize() || info.getTickets() > e.getEventSize()) {
            mav.setViewName("errorregistration");
            mav.addObject("reason", "Your request for tickets exceed the event size");
        } else {
            e.setParticipants(e.getParticipants() + info.getTickets());
            dbServ.saveInfo(info);
            dbServ.updateEvent(e);
            mav.setViewName("successregistration");
            mav.addObject("event", e);
        }
        // }
        return mav;
    }
}
