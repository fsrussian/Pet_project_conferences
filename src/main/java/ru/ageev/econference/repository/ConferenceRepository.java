package ru.ageev.econference.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ageev.econference.domain.Conference;

import java.util.List;

public interface ConferenceRepository extends CrudRepository<Conference, Long> {
    List<Conference> findByText(String tag);
    List<Conference> findByTextContains(String text);

    List<Conference> findAllByOrderByConferenceDate();
}
