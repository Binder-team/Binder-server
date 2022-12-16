package com.binder.server.controller;

import com.binder.server.exception.ResourceNotFoundException;
import com.binder.server.model.Match;
import com.binder.server.model.TradeTable;
import com.binder.server.model.User;
import com.binder.server.model.UserBooks;
import com.binder.server.repository.MatchRepository;
import com.binder.server.repository.TradeTableRepository;
import com.binder.server.repository.UserBooksRepository;
import com.binder.server.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class MatchController {

    private final MatchRepository matchRepository;
    private final UserRepository userRepository;
    private final UserBooksRepository userBooksRepository;
    private final TradeTableRepository tradeTableRepository;

    public MatchController(MatchRepository matchRepository, UserRepository userRepository, UserBooksRepository userBooksRepository, TradeTableRepository tradeTableRepository) {
        this.matchRepository = matchRepository;
        this.userRepository = userRepository;
        this.userBooksRepository = userBooksRepository;
        this.tradeTableRepository = tradeTableRepository;
    }

    @GetMapping("matches")
    public List<Match> getAllMatches() {
        return this.matchRepository.findAll();
    }

    @GetMapping("matches/{username}")
    public List<Match> findUserMatches (@PathVariable(value = "username") String username) {
        User user = userRepository.findUserByUsername(username);
        List<Match> matches = matchRepository.findByUser1IdOrUser2Id(user.getId(), user.getId());
        return matches;
    }

    @DeleteMapping("matches/{id}")
    public Map<String, Boolean> deleteTradeTable(@PathVariable(value = "id") Long matchId) throws ResourceNotFoundException {
        Match match = matchRepository.findById(matchId)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found for this id ::" + matchId));
        this.matchRepository.delete(match);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    @PutMapping("matches/accept/user/{username}")
    public ResponseEntity<Match> acceptTrade(@PathVariable(value = "username") String username, @RequestBody Match matchDetails) {
        User user = userRepository.findUserByUsername(username);
        UserBooks book1 = userBooksRepository.findUserBooksById(matchDetails.getBook1Id());
        UserBooks book2 = userBooksRepository.findUserBooksById(matchDetails.getBook2Id());
        book1.setIsAvailable(false);
        book2.setIsAvailable(false);
        this.userBooksRepository.save(book1);
        this.userBooksRepository.save(book2);
        if (user.getId() == matchDetails.getUser1Id()){
            matchDetails.setDidUser1Accept(true);
        } else if (user.getId() == matchDetails.getUser2Id()){
            matchDetails.setDidUser2Accept(true);
        }

        return ResponseEntity.ok().body(this.matchRepository.save(matchDetails));
    }

    @PutMapping("matches/deny/user/{username}")
    public ResponseEntity<Match> denyTrade(@PathVariable(value = "username") String username, @RequestBody Match matchDetails) {
        UserBooks book1 = userBooksRepository.findUserBooksById(matchDetails.getBook1Id());
        UserBooks book2 = userBooksRepository.findUserBooksById(matchDetails.getBook1Id());
        Match matchRecord = matchRepository.findMatchById(matchDetails.getId());
        TradeTable trade1 = tradeTableRepository.findBySenderAndBookId(matchDetails.getUser1Id(), matchDetails.getBook2Id());
        TradeTable trade2 = tradeTableRepository.findBySenderAndBookId(matchDetails.getUser2Id(), matchDetails.getBook1Id());
        book1.setIsAvailable(true);
        book2.setIsAvailable(true);
        this.tradeTableRepository.delete(trade1);
        this.tradeTableRepository.delete(trade2);
        this.userBooksRepository.save(book1);
        this.userBooksRepository.save(book2);
        this.matchRepository.delete(matchRecord);

        return ResponseEntity.ok().body(matchRecord);
    }
    
    @PutMapping("matches/exchange/user/{username}")
    public ResponseEntity<Match> booksExchanged(@PathVariable(value = "username") String username, @RequestBody Match matchDetails) {
        User user = userRepository.findUserByUsername(username);
        if (user.getId() == matchDetails.getUser1Id()){
            matchDetails.setDidUser1Exchange(true);
        } else if (user.getId() == matchDetails.getUser2Id()){
            matchDetails.setDidUser2Exchange(true);
        }
        this.matchRepository.save(matchDetails);

        if(matchDetails.getDidUser1Exchange() && matchDetails.getDidUser2Accept()) {
            TradeTable trade1 = tradeTableRepository.findBySenderAndBookId(matchDetails.getUser1Id(), matchDetails.getBook2Id());
            TradeTable trade2 = tradeTableRepository.findBySenderAndBookId(matchDetails.getUser2Id(), matchDetails.getBook1Id());
            UserBooks book1 = userBooksRepository.findUserBooksById(matchDetails.getBook1Id());
            UserBooks book2 = userBooksRepository.findUserBooksById(matchDetails.getBook1Id());
            this.tradeTableRepository.delete(trade1);
            this.tradeTableRepository.delete(trade2);
            this.userBooksRepository.delete(book1);
            this.userBooksRepository.delete(book2);
            this.matchRepository.delete(matchDetails);
        }
        return ResponseEntity.ok().body(matchDetails);
    }
}
