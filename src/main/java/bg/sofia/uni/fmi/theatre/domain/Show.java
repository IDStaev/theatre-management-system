package bg.sofia.uni.fmi.theatre.domain;

import bg.sofia.uni.fmi.theatre.vo.AgeRating;
import bg.sofia.uni.fmi.theatre.vo.Genre;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Show {
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private final Long id;
    private String title;
    private String description;
    private Genre genre;
    private int durationMinutes;
    private AgeRating ageRating;

    private static final int MAX_TITLE_LENGTH = 100;

    public Show(Long id, String title, String description, Genre genre, int durationMinutes, AgeRating ageRating) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("title is required");
        if (title.length() > MAX_TITLE_LENGTH)
            throw new IllegalArgumentException("title must be at most 100 characters");
        if (durationMinutes <= 0) throw new IllegalArgumentException("durationMinutes must be positive");

        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.durationMinutes = durationMinutes;
        this.ageRating = ageRating;
    }
}
