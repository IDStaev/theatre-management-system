package bg.sofia.uni.fmi.theatre.service;

import bg.sofia.uni.fmi.theatre.domain.Show;
import bg.sofia.uni.fmi.theatre.repository.ShowRepository;
import bg.sofia.uni.fmi.theatre.vo.AgeRating;
import bg.sofia.uni.fmi.theatre.vo.Genre;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public Show addShow(String title, String description,
                        Genre genre, int duration, AgeRating ageRating) {
        return showRepository.save(new Show(
            null,
            title,
            description,
            genre,
            duration,
            ageRating
        ));
    }

    public Show updateShow(Long id, String title, String description,
                           Genre genre, int duration, AgeRating ageRating) {
        return showRepository.save(new Show(
            id,
            title,
            description,
            genre,
            duration,
            ageRating
        ));
    }

    public Optional<Show> findById(Long id) {
        return showRepository.findById(id);
    }

    public List<Show> findAll() {
        return showRepository.findAll();
    }

    public void deleteById(Long id) {
        showRepository.deleteById(id);
    }
}
