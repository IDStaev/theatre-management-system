package bg.sofia.uni.fmi.theatre.service;

import bg.sofia.uni.fmi.theatre.domain.Performance;
import bg.sofia.uni.fmi.theatre.repository.PerformanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerformanceService {
    private final PerformanceRepository performanceRepository;

    public PerformanceService(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }

    public Performance save(Performance performance) {
        return performanceRepository.save(performance);
    }

    public Optional<Performance> findById(Long id) {
        return performanceRepository.findById(id);
    }

    public List<Performance> findAll() {
        return performanceRepository.findAll();
    }

    public List<Performance> findByShowId(Long showId) {
        return performanceRepository.findByShowId(showId);
    }

    public void deleteById(Long id) {
        performanceRepository.deleteById(id);
    }
}
