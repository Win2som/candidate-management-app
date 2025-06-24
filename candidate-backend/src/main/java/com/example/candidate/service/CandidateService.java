package com.example.candidate.service;

import com.example.candidate.dto.CandidateRequest;
import com.example.candidate.entity.Candidate;
import org.springframework.data.domain.Page;

public interface CandidateService {

     Candidate createCandidate(CandidateRequest request);
     Page<Candidate> getAllCandidates(int page, int size);
     void deleteCandidate(Long id);
}
