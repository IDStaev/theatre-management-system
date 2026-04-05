package bg.sofia.uni.fmi.theatre.runner;

import bg.sofia.uni.fmi.theatre.service.ShowService;
import bg.sofia.uni.fmi.theatre.vo.AgeRating;
import bg.sofia.uni.fmi.theatre.vo.Genre;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class SeedingService implements CommandLineRunner {
    private final ShowService showService;

    public SeedingService(ShowService showService) {
        this.showService = showService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("🚀 Application started successfully!");

        // Add Shows
        showService.addShow("Hamlet", "Drama", Genre.DRAMA, 180, AgeRating.ALL);
        showService.addShow("The Nutcracker", "Ballet", Genre.BALLET, 120, AgeRating.PG_12);

        try {
            showService.addShow("", "Comedy", Genre.COMEDY, 90, AgeRating.PG_16);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        }

        System.out.println("---------------------------------------");
        System.out.println("✅ Shows added successfully!");
        System.out.println("---------------------------------------");

//        // Display All Shows
//        System.out.println("📌 Displaying all shows:");
//        showController.displayAllShows();
//        System.out.println("---------------------------------------");
//
//        System.out.println("🔄 Updating 'Hamlet' duration to 200 minutes...");
//        showController.updateShow(1, "Hamlet", "Drama", 200);
//
//        System.out.println("---------------------------------------");
//        System.out.println("📌 Displaying updated shows:");
//        showController.displayAllShows();
//
//        System.out.println("---------------------------------------");
//        System.out.println("📌 Displaying all shows by genre 'Drama':");
//        List<Show> dramaShows = showController.getShowsByGenre("Drama");
//        dramaShows.stream().forEach(System.out::println);
//        System.out.println("---------------------------------------");
    }
}
