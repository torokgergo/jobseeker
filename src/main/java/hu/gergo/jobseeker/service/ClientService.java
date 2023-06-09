package hu.gergo.jobseeker.service;

import hu.gergo.jobseeker.exception.EmailAlreadyExists;
import hu.gergo.jobseeker.model.Client;
import hu.gergo.jobseeker.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public String registerClient(Client client) {
        if (repository.existsByEmail(client.getEmail())) {
            throw new EmailAlreadyExists("Az email már foglalt.");
        }

        repository.save(client);

        return client.getApiKey().toString();
    }

    public boolean isValidApiKey(String apiKey) {
        return repository.existsByApiKey(UUID.fromString(apiKey));
    }
}
