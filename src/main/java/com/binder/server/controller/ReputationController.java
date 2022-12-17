package com.binder.server.controller;

import com.binder.server.exception.ResourceNotFoundException;
import com.binder.server.model.Match;
import com.binder.server.model.Reputation;
import com.binder.server.model.User;
import com.binder.server.repository.MatchRepository;
import com.binder.server.repository.ReputationRepository;
import com.binder.server.repository.UserRepository;
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
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;
    public ReputationController(ReputationRepository reputationRepository, MatchRepository matchRepository, UserRepository userRepository) {
        this.reputationRepository = reputationRepository;
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
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

    @PostMapping("reputation/user/{username}/{score}")
        public ResponseEntity<Reputation> createReview(@PathVariable(value = "score")int score, @PathVariable(value = "username")String username, @RequestBody Match matchDetails) {
        User user = userRepository.findUserByUsername(username);
        Reputation review = new Reputation();
        review.setReviewer(user.getId());
        review.setReview_target(matchDetails.getId());
        review.setScore(score);
        if (user.getId() == matchDetails.getUser1Id()) {
            review.setRecipient(matchDetails.getUser2Id());
        } else {
            review.setRecipient(matchDetails.getUser1Id());
        }
        return ResponseEntity.ok().body(this.reputationRepository.save(review));
    }

    @GetMapping("reputation/user/average/{username}")
        public ResponseEntity<List<Reputation>> getReviews(@PathVariable(value = "username") String username) {
        User user = userRepository.findUserByUsername(username);
        List<Reputation> reputationList = reputationRepository.findReputationByRecipient(user.getId());

        return ResponseEntity.ok().body(reputationList);
    }

}
