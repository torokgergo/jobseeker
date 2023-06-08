package hu.gergo.jobseeker.controller;

import hu.gergo.jobseeker.model.Position;
import hu.gergo.jobseeker.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("position")
public class PositionController {

    @Value("${base.path}")
    private String basePath;

    private final PositionService service;

    @Autowired
    public PositionController(PositionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> createPosition(@Valid @RequestBody Position position) {
        service.createPosition(position);

        String url = basePath + "/position/" + position.getId();

        return ResponseEntity.ok(url);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Position> findPosition(@PathVariable Long id) {
        Optional<Position> position = service.findById(id);

        return position.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
