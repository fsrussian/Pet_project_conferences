package ru.ageev.econference.controller;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.ageev.econference.domain.Conference;
import ru.ageev.econference.domain.User;
import ru.ageev.econference.repository.UserRepository;
import ru.ageev.econference.service.ConferenceService;

import java.io.IOException;
import java.util.*;

@Controller
public class MainController {
    private final ConferenceService conferenceService;
    private final UserRepository userRepo;

    public MainController(ConferenceService conferenceService, UserRepository userRepo) {
        this.conferenceService = conferenceService;
        this.userRepo = userRepo;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Conference> messages;

        if (filter != null && !filter.isEmpty())
            messages = conferenceService.findByFilter(filter);
        else
            messages = conferenceService.getAllConference();

        model.addAttribute("conferences", messages);
        model.addAttribute("filter", filter);
        model.addAttribute("registr", 0);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) String text,
            @RequestParam(required = false) String conferenceName,
            @RequestParam(required = false, defaultValue = "") String fullDescription,
            Map<String, Object> model,
            @RequestParam(required = false, defaultValue = "0") String idconference,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date conferenceDate
    ) throws IOException {

        int confRegistrationId = Integer.parseInt(idconference);
        if (confRegistrationId != 0)
            conferenceService.addListener(idconference, user);
        conferenceService.addNewConference(user, text, conferenceName, fullDescription, file, conferenceDate);
        Iterable<Conference> conferences = conferenceService.getAllConference();
        conferences = conferenceService.getAllConference();
        model.put("conferences", conferences);
        model.put("registr", confRegistrationId);
        return "main";
    }
}
