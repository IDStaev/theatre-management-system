package bg.sofia.uni.fmi.theatre.service;

import bg.sofia.uni.fmi.theatre.config.AppLogger;
import bg.sofia.uni.fmi.theatre.config.TheatreProperties;
import bg.sofia.uni.fmi.theatre.domain.Show;
import bg.sofia.uni.fmi.theatre.dto.PageResponse;
import bg.sofia.uni.fmi.theatre.dto.ShowRequest;
import bg.sofia.uni.fmi.theatre.dto.ShowResponse;
import bg.sofia.uni.fmi.theatre.exception.NotFoundException;
import bg.sofia.uni.fmi.theatre.exception.ValidationException;
import bg.sofia.uni.fmi.theatre.repository.ShowRepository;
import bg.sofia.uni.fmi.theatre.vo.AgeRating;
import bg.sofia.uni.fmi.theatre.vo.Genre;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    private final ShowRepository showRepository;
    private final AppLogger logger;
    private final int defaultPageSize;

    public ShowService(ShowRepository showRepository, AppLogger logger, TheatreProperties properties) {
        this.showRepository = showRepository;
        this.logger = logger;
        this.defaultPageSize = properties.getDefaultPageSize();
    }

    public ShowResponse addShow(ShowRequest request) {
        if (request == null) throw new ValidationException("Show request must not be null");

        Show show = new Show(null, request.getTitle(), request.getDescription(),
            request.getGenre(), request.getDurationMinutes(), request.getAgeRating());

        logger.debug("Adding show: " + show.getTitle());
        Show saved = showRepository.save(show);
        logger.info("Show saved: " + show.getTitle());

        return ShowResponse.from(saved);
    }

    public ShowResponse getShowById(Long id) {
        if (id == null) throw new ValidationException("Id must not be null");

        logger.debug("Fetching show by id: " + id);
        return showRepository.findById(id).map(ShowResponse::from).orElseThrow(() -> {
            logger.error("Show not found: id=" + id);
            return new NotFoundException("Show", id);
        });
    }

    public Optional<ShowResponse> findShowById(Long id) {
        if (id == null) throw new ValidationException("Id must not be null");

        return showRepository.findById(id).map(ShowResponse::from);
    }

    public PageResponse<ShowResponse> searchShows(String titleQuery, Genre genre, int page, int size) {
        if (page < 0) throw new ValidationException("page must not be negative");
        if (size <= 0) throw new ValidationException("size must be positive");

        logger.debug("Searching shows — title='" + titleQuery + "', genre=" + genre + ", page=" + page);

        List<ShowResponse> allResults = showRepository.findAll().stream()
            .filter(show -> titleQuery == null || titleQuery.isBlank() ||
                show.getTitle().toLowerCase().contains(titleQuery.toLowerCase()))
            .filter(show -> genre == null || show.getGenre().equals(genre))
            .sorted(Comparator.comparing(Show::getTitle))
            .map(ShowResponse::from)
            .toList();

        int total = allResults.size();
        logger.info("Search returned " + total + " total results");

        int fromIndex = page * size;
        List<ShowResponse> pageContent = fromIndex >= total ?
            List.of() :
            allResults.subList(fromIndex, Math.min(fromIndex + size, total));

        return new PageResponse<>(pageContent, page, size, total);
    }

    public PageResponse<ShowResponse> searchShows(String titleQuery, Genre genre) {
        return searchShows(titleQuery, genre, 0, defaultPageSize);
    }

    public List<ShowResponse> getAllShows() {
        logger.trace("getAllShows called");

        return showRepository.findAll().stream()
            .map(ShowResponse::from)
            .toList();
    }

    public ShowResponse updateShow(Long id, ShowRequest request) {
        if (id == null) throw new ValidationException("Id must not be null");
        if (request == null) throw new ValidationException("Show request must not be null");

        logger.debug("Fetching show with id=" + id);
        Show existing = showRepository.findById(id).orElseThrow(() -> {
            logger.error("Show not found id=" + id);
            return new NotFoundException("Show", id);
        });

        logger.debug("Modifying show with id=" + id);
        existing.setTitle(request.getTitle());
        existing.setDescription(request.getDescription());
        existing.setGenre(request.getGenre());
        existing.setDurationMinutes(request.getDurationMinutes());
        existing.setAgeRating(request.getAgeRating());

        logger.info("Show updated: id=" + id);

        return ShowResponse.from(showRepository.save(existing));
    }

    public void deleteById(Long id) {
        if (id == null) throw new ValidationException("Id must not be null");

        logger.debug("Fetching show with id=" + id);
        showRepository.findById(id).orElseThrow(() -> {
            logger.error("Show not found with id=" + id);
            return new NotFoundException("Show", id);
        });

        showRepository.deleteById(id);

        logger.info("Show deleted: id=" + id);
    }
}
