package ru.ageev.econference.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.ageev.econference.domain.Conference;
import ru.ageev.econference.domain.User;
import ru.ageev.econference.repository.ConferenceRepository;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ConferenceService {
    private final ConferenceRepository conferenceRepository;

    private String uploadPath = "/C:/Users/Marat.Marat-ПК/Downloads/econference/src/main/webapp/WEB-INF/images/";

    public ConferenceService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }


    public boolean addListener(String idConference, User listener) {
        Long id = Long.parseLong(idConference);
        Conference conference = conferenceRepository.findById(id).get();
        if (conference.getListeners().stream().anyMatch(a -> a.getId().equals(listener.getId()))) {
            return false;
        } else {
            conference.addListener(listener);
            conferenceRepository.save(conference);
            return true;
        }
    }

    public void addNewConference(User user, String text, String conferenceName, String fullDescription,
                                 MultipartFile file, Date date) throws IOException {

        if (conferenceName != null && !conferenceName.isEmpty()) {
            Conference conference = new Conference(user, conferenceName, text, fullDescription, date);

            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists())
                    uploadDir.mkdir();
                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + resultFilename));

                conference.setFileName(resultFilename);
            }
            conferenceRepository.save(conference);
        }
    }
    public void removeConference(Long id){
        conferenceRepository.deleteById(id);
    }

    public Iterable<Conference> getAllConference() {
        return conferenceRepository.findAll();
    }

    public List<Conference> getAllSortConference() {
        return conferenceRepository.findAllByOrderByConferenceDate();
    }

    public Iterable<Conference> findByFilter(String filter) {
        return conferenceRepository.findByTextContains(filter);
    }

    public void saveConference(Conference conference) {
        conferenceRepository.save(conference);
    }

    public Conference findById(Long id) {
        return conferenceRepository.findById(id).get();
    }
}




