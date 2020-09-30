package ru.ageev.econference.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ageev.econference.domain.Conference;
import ru.ageev.econference.domain.User;

import java.util.Set;

@Controller
@RequestMapping("/conferences")

public class ConferenceController {

    @GetMapping("{conference}")
    public String description(@PathVariable Conference conference, Model model) {
        Set<User> listeners = conference.getListeners();
        model.addAttribute("confDesc", conference);
        model.addAttribute("conference", conference);
        model.addAttribute("listeners", listeners);

        return "confDescription";
    }
}
