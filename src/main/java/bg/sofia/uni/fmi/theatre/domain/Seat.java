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
public class Seat {
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private final Long id;
    @Setter(AccessLevel.NONE)
    private final Long hallId;
    private String rowLabel;
    private int seatNumber;
    private String zoneCode;

    public Seat(Long id, Long hallId, String rowLabel, int seatNumber, String zoneCode) {
        if (hallId == null) throw new IllegalArgumentException("hallId is required");
        if (rowLabel == null || rowLabel.isBlank()) throw new IllegalArgumentException("rowLabel is required");
        if (seatNumber <= 0) throw new IllegalArgumentException("seatNumber must be positive");

        this.id = id;
        this.hallId = hallId;
        this.rowLabel = rowLabel;
        this.seatNumber = seatNumber;
        this.zoneCode = zoneCode;
    }
}