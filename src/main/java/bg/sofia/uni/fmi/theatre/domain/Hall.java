package bg.sofia.uni.fmi.theatre.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Hall {
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private final Long id;
    private String name;

    public Hall(Long id, String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name is required");

        this.id = id;
        this.name = name;
    }
}
