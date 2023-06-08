package hu.gergo.jobseeker.controller;

import hu.gergo.jobseeker.helper.PositionHelper;
import hu.gergo.jobseeker.model.Position;
import hu.gergo.jobseeker.service.ClientService;
import hu.gergo.jobseeker.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("position")
public class PositionController {

    private final PositionService positionService;

    private final ClientService clientService;

    @Autowired
    public PositionController(PositionService service, ClientService clientService) {
        this.positionService = service;
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<String> createPosition(
            @Valid @RequestBody Position position,
            @RequestHeader("Authorization") String apiKey) {

        if (!clientService.isValidApiKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        positionService.createPosition(position);

        return ResponseEntity.ok(PositionHelper.getPositionUrl(position));
    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> searchPositions(
            @RequestParam("keyword") @Size(max = 50) String keyword,
            @RequestParam("location") @Size(max = 50) String location,
            @RequestHeader("Authorization") String apiKey) {

        if (!clientService.isValidApiKey(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.emptyList());
        }

        List<String> positionUrls = positionService.searchPositions(keyword, location);

        return !positionUrls.isEmpty()
                ? ResponseEntity.ok(positionUrls)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> findPosition(@PathVariable Long id) {
        Optional<Position> position = positionService.findById(id);

        return position.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
