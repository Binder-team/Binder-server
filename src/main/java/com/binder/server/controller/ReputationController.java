package com.binder.server.controller;

import com.binder.server.exception.ResourceNotFoundException;
import com.binder.server.model.Reputation;
import com.binder.server.repository.ReputationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class ReputationController {
    private final ReputationRepository reputationRepository;

    public ReputationController(ReputationRepository reputationRepository) {
        this.reputationRepository = reputationRepository;
    }

    //get reputation
    @GetMapping("reputation")
    public List<Reputation> getAllReputation(){
        return this.reputationRepository.findAll();
    }

    //get reputation by id
    @GetMapping("reputation/{id}")
    public ResponseEntity<Reputation> getReputationById(@PathVariable(value = "id") Long reputationId)
        throws ResourceNotFoundException {
        Reputation reputation = reputationRepository.findById(reputationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reputation not found for this id ::" + reputationId));
        return ResponseEntity.ok().body(reputation);
    }

    //save reputation
    @PostMapping("reputations")
    public Reputation createReputation(@RequestBody Reputation reputation) {
        return this.reputationRepository.save(reputation);
    }
    //update reputation
    @PutMapping("reputation/{id}")
    public ResponseEntity<Reputation> updateReputation(@PathVariable(value = "id") Long reputationId,
                                                       @Validated @RequestBody Reputation reputationDetails ) throws ResourceNotFoundException {
        Reputation reputation = reputationRepository.findById(reputationId)
                .orElseThrow(() -> new ResourceNotFoundException("Reputation not found for this id ::" + reputationId));
        reputation.setReview_target(reputationDetails.getReview_target());
        reputation.setReview_target(reputationDetails.getReview_target());
        reputation.setRecipient(reputationDetails.getRecipient());
        reputation.setReviewer(reputationDetails.getReviewer());
        reputation.setScore(reputationDetails.getScore());

        return ResponseEntity.ok(this.reputationRepository.save(reputation));
    }
    //delete reputation
    @DeleteMapping("reputation/{id}")
      public Map<String, Boolean> deleteReputation(@PathVariable(value = "id") Long reputationId) throws ResourceNotFoundException {
          Reputation reputation = reputationRepository.findById(reputationId)
                  .orElseThrow(() -> new ResourceNotFoundException("Reputation not found for this id ::" + reputationId));
          this.reputationRepository.delete(reputation);

          Map<String, Boolean> response =new HashMap<>();
          response.put("deleted", Boolean.TRUE);

          return response;
      }



}
