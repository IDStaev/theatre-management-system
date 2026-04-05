package bg.sofia.uni.fmi.theatre.repository.inmemory;

import bg.sofia.uni.fmi.theatre.domain.Show;
import bg.sofia.uni.fmi.theatre.repository.ShowRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryShowRepository implements ShowRepository {
    private final Map<Long, Show> store = new HashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    @Override
    public Show save(Show show) {
        if (show.getId() == null) {
            // Save
            Show saved = new Show(
                nextId(),
                show.getTitle(),
                show.getDescription(),
                show.getGenre(),
                show.getDurationMinutes(),
                show.getAgeRating()
                );

            store.put(saved.getId(), saved);
            return saved;
        } else {
            // Update
            store.put(show.getId(), show);
            return show;
        }
    }

    @Override
    public Optional<Show> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Show> findAll() {
        return List.copyOf(store.values());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return store.containsKey(id);
    }

    private long nextId() {
        return idSequence.getAndIncrement();
    }
}