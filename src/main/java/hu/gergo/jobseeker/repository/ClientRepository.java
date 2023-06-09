package hu.gergo.jobseeker.repository;

import hu.gergo.jobseeker.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByEmail(String email);

    boolean existsByApiKey(UUID apiKey);
}
