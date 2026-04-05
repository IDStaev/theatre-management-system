package bg.sofia.uni.fmi.theatre.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "theatre")
@Getter
@Setter
public class TheatreProperties {
    // Default values
    private int reservationHoldMinutes = 15;
    private int defaultPageSize = 10;
    private LogLevel logLevel = LogLevel.INFO;
    private String logFile = "logs/theatre.log";
}
