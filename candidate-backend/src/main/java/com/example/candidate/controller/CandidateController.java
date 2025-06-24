package com.example.candidate.controller;

import com.example.candidate.dto.CandidateRequest;
import com.example.candidate.entity.Candidate;
import com.example.candidate.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin(origins = "http://localhost:4200")
public class CandidateController {

   private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public ResponseEntity<Page<Candidate>> getAll(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(candidateService.getAllCandidates(page, size));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CandidateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            result.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("; "));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
        }

        try {
            Candidate savedCandidate = candidateService.createCandidate(request);
            System.out.println(savedCandidate);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCandidate);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            candidateService.deleteCandidate(id);
            return ResponseEntity.ok(Collections.singletonMap("message", "Candidate deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
