package bg.sofia.uni.fmi.theatre.domain;

import bg.sofia.uni.fmi.theatre.vo.PerformanceStatus;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Performance {
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private final Long id;
    @Setter(AccessLevel.NONE)
    private final Long showId;
    @Setter(AccessLevel.NONE)
    private final Long hallId;
    private LocalDateTime startTime;
    private PerformanceStatus status;

    public Performance(Long id, Long showId, Long hallId, LocalDateTime startTime) {
        if (showId == null) throw new IllegalArgumentException("showId is required");
        if (hallId == null) throw new IllegalArgumentException("hallId is required");
        if (startTime == null) throw new IllegalArgumentException("startTime is required");
        this.id = id;
        this.showId = showId;
        this.hallId = hallId;
        this.startTime = startTime;
        this.status = PerformanceStatus.SCHEDULED;
    }
}