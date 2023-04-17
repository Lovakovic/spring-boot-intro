package hr.tvz.lovakovic.studapp.controller;

import hr.tvz.lovakovic.studapp.command.FakultetCommand;
import hr.tvz.lovakovic.studapp.dto.FakultetDTO;
import hr.tvz.lovakovic.studapp.service.FakultetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fakulteti")
public class FakultetController {
    private final FakultetService fakultetService;

    @Autowired
    public FakultetController (FakultetService fakultetService) { this.fakultetService = fakultetService; }

    @GetMapping
    public List<FakultetDTO> getAllFakulteti() { return this.fakultetService.findAll(); }

    @GetMapping("/{name}")
    public ResponseEntity<FakultetDTO> getFakultetByName(@PathVariable String name) {
        FakultetDTO fakultetDTO = fakultetService.findFakultetByName(name);
        if(fakultetDTO != null) {
            return ResponseEntity.ok(fakultetDTO);
        } else  {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> addFakultet(@Valid @RequestBody FakultetCommand fakultetCommand) {
        boolean added = fakultetService.addFakultet(fakultetCommand);
        if(added) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteFakultet(@PathVariable String name) {
        boolean deleted = fakultetService.deleteFakultetByName(name);
        if(deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
