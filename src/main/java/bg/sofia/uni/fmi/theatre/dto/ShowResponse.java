package bg.sofia.uni.fmi.theatre.dto;

import bg.sofia.uni.fmi.theatre.domain.Show;
import bg.sofia.uni.fmi.theatre.vo.AgeRating;
import bg.sofia.uni.fmi.theatre.vo.Genre;
import lombok.Getter;

@Getter
public class ShowResponse {
    private Long id;
    private String title;
    private String description;
    private Genre genre;
    private int durationMinutes;
    private AgeRating ageRating;

    // Can only be created through Show
    public static ShowResponse from(Show show) {
        ShowResponse r = new ShowResponse();
        r.id = show.getId();
        r.title = show.getTitle();
        r.description = show.getDescription();
        r.genre = show.getGenre();
        r.durationMinutes = show.getDurationMinutes();
        r.ageRating = show.getAgeRating();
        return r;
    }
}
