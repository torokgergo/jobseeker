package hu.gergo.jobseeker.controller;

import hu.gergo.jobseeker.model.Client;
import hu.gergo.jobseeker.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("client")
public class ClientController {

    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> registerClient(@Valid @RequestBody Client client) {
        String apiKey = service.registerClient(client);

        return ResponseEntity.ok(apiKey);
    }
}
