package hu.gergo.jobseeker.repository;

import hu.gergo.jobseeker.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

    List<Position> findByNameContainingIgnoreCaseAndLocationContainingIgnoreCase(String keyword, String location);
}
