package bg.sofia.uni.fmi.theatre.cli;

import bg.sofia.uni.fmi.theatre.config.AppLogger;
import bg.sofia.uni.fmi.theatre.config.TheatreProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class StartupEnvironmentLogger implements ApplicationRunner {
    private final AppLogger logger;
    private final TheatreProperties properties;
    private final ApplicationContext context;

    public StartupEnvironmentLogger(AppLogger logger, TheatreProperties properties, ApplicationContext context) {
        this.logger = logger;
        this.properties = properties;
        this.context = context;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("=== Theatre App Started ===");
        logger.info("Log level     : " + properties.getLogLevel());
        logger.info("Page size     : " + properties.getDefaultPageSize());
        logger.info("Reservation   : " + properties.getReservationHoldMinutes() + " min hold");
        logger.info("Log file      : " + properties.getLogFile());
        logger.debug("--- Project beans (bg.uni.fmi.theatre) ---");

        Arrays.stream(context.getBeanDefinitionNames())
            .filter(name -> {
                    try {
                        return context.getBean(name).getClass()
                            .getPackageName().startsWith("bg.sofia.uni.fmi.theatre");
                    } catch (Exception e) {
                        return false;
                    }
                }
            )
            .forEach(name -> {
                logger.debug(" bean: " + name);
            });
    }
}
