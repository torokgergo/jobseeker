package hu.gergo.jobseeker.service;

import hu.gergo.jobseeker.helper.PositionHelper;
import hu.gergo.jobseeker.model.Position;
import hu.gergo.jobseeker.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PositionService {

    private final PositionRepository repository;

    @Autowired
    public PositionService(PositionRepository repository) {
        this.repository = repository;
    }

    public void createPosition(Position position) {
        repository.save(position);
    }

    public Optional<Position> findById(Long id) {
        return repository.findById(id);
    }

    public List<String> searchPositions(String keyword, String location) {
        return repository.findByNameContainingIgnoreCaseAndLocationContainingIgnoreCase(keyword, location)
                .stream()
                .map(PositionHelper::getPositionUrl)
                .collect(Collectors.toList());
    }
}
