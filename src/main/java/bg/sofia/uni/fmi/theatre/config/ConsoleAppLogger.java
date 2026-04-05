package bg.sofia.uni.fmi.theatre.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Profile("dev")
public class ConsoleAppLogger implements AppLogger {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final LogLevel configuredLevel;

    public ConsoleAppLogger(TheatreProperties properties) {
        this.configuredLevel = properties.getLogLevel();
    }

    @Override
    public void trace(String message) {
        if (LogLevel.TRACE.isEnabled(configuredLevel)) {
            print("TRACE", message);
        }
    }

    @Override
    public void debug(String message) {
        if (LogLevel.DEBUG.isEnabled(configuredLevel)) {
            print("DEBUG", message);
        }
    }

    @Override
    public void info(String message) {
        if (LogLevel.INFO.isEnabled(configuredLevel)) {
            print("INFO", message);
        }
    }

    @Override
    public void error(String message) {
        print("ERROR", message);
    }

    @Override
    public void error(String message, Throwable cause) {
        print("ERROR", message + " - " + cause.getMessage());
    }

    private void print(String level, String message) {
        System.out.printf("[%s] [%s] %s%n", LocalDateTime.now().format(FMT), level, message);
    }
}
