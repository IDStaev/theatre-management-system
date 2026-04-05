package bg.sofia.uni.fmi.theatre.dto;

import bg.sofia.uni.fmi.theatre.vo.AgeRating;
import bg.sofia.uni.fmi.theatre.vo.Genre;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class ShowRequest {
    @NotBlank
    private String title;
    private String description;
    private Genre genre;
    private int durationMinutes;
    private AgeRating ageRating;
}
