package com.example.candidate.service;

import com.example.candidate.dto.CandidateRequest;
import com.example.candidate.entity.Candidate;
import com.example.candidate.repo.CandidateRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository repository;

    public CandidateServiceImpl(CandidateRepository repository) {
        this.repository = repository;
    }

    public Candidate createCandidate(CandidateRequest candidateRequest) {
        Optional<Candidate> existingCandidate = repository.findByEmail(candidateRequest.getEmail());
        if (existingCandidate.isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        Candidate candidate = Candidate.builder()
                .name(candidateRequest.getName())
                .email(candidateRequest.getEmail())
                .position(candidateRequest.getPosition())
                .build();
        return repository.save(candidate);
    }

    public Page<Candidate> getAllCandidates(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    public void deleteCandidate(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Candidate not found");
        }
        repository.deleteById(id);
    }
}
