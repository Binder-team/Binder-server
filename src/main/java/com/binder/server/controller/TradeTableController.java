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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class TradeTableController {
    private final TradeTableRepository tradeTableRepository;
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;

    public TradeTableController(TradeTableRepository tradeTableRepository, UserRepository userRepository, MatchRepository matchRepository) {
        this.tradeTableRepository = tradeTableRepository;
        this.userRepository = userRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("trade_table")
    public List<TradeTable> getAllTradeTables() {
        return this.tradeTableRepository.findAll();
    }

    @GetMapping("trade_table/{id}")
    public ResponseEntity<TradeTable> getUserTableById(@PathVariable(value = "id") Long tradeTableId)
     throws ResourceNotFoundException {
        TradeTable tradeTable = tradeTableRepository.findById(tradeTableId)
                .orElseThrow(() -> new ResourceNotFoundException("TradeTable not found for this id :: " + tradeTableId));
        return ResponseEntity.ok().body(tradeTable);
    }
    @PostMapping("trade_table/user/{username}")
    public int createTradeTable(@RequestBody UserBooks book, @PathVariable(value = "username") String username){
        //Should have bookID(which has book owner ID) and token
        User sender = userRepository.findUserByUsername(username);
        TradeTable trade = new TradeTable();
        trade.setSender(sender.getId());
        trade.setReceiver(book.getUserId());
        trade.setBook_id(book.getId());
        trade.setIs_accepted(false);
        trade.setIs_exchanged(false);
        trade.setIs_matched(false);
        this.tradeTableRepository.save(trade);

        List<TradeTable> matchList = tradeTableRepository.findBySenderAndReceiver(book.getUserId(), sender.getId());
        if(matchList.size() != 0) {
            for (int i = 0; i < matchList.size(); i++) {
                Match match = new Match();
                TradeTable matchTrade = matchList.get(i);
                User receiver = userRepository.findUserById(matchTrade.getReceiver());
                match.setUser1Id(sender.getId());
                match.setUsername1(sender.getUsername());
                match.setUser2Id((receiver.getId()));
                match.setUsername2(receiver.getUsername());
                match.setBook1Id(matchTrade.getBook_id());
                match.setBook2Id(book.getId());
                this.matchRepository.save(match);
            }
        }
        return matchList.size();
    }
    @PutMapping("trade_table/{id}")
    public ResponseEntity<TradeTable> updateTradeTable(@PathVariable(value ="id") Long tradeTableId,
                                                       @Validated @RequestBody TradeTable tradeTableDetails) throws ResourceNotFoundException {
        TradeTable tradeTable = tradeTableRepository.findById(tradeTableId)
                .orElseThrow(() -> new ResourceNotFoundException("TradeTable not found for this id :: " + tradeTableId));
        tradeTable.setSender(tradeTableDetails.getSender());
        tradeTable.setReceiver(tradeTableDetails.getReceiver());
        tradeTable.setBook_id(tradeTableDetails.getBook_id());
        tradeTable.setIs_matched(tradeTableDetails.isIs_matched());
        tradeTable.setIs_accepted(tradeTableDetails.isIs_accepted());
        tradeTable.setIs_exchanged(tradeTableDetails.isIs_exchanged());
        return ResponseEntity.ok(this.tradeTableRepository.save(tradeTable));
    }

    @DeleteMapping("trade_table/{id}")
    public Map<String, Boolean> deleteTradeTable(@PathVariable(value = "id") Long tradeTableId) throws ResourceNotFoundException {
        TradeTable tradeTable = tradeTableRepository.findById(tradeTableId)
                .orElseThrow(() -> new ResourceNotFoundException("TradeTables not found for this id ::" + tradeTableId));
        this.tradeTableRepository.delete(tradeTable);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    @GetMapping("trade_table/liked/{username}")
    public List<TradeTable> findLikedBooksByUsername(@PathVariable(value = "username") String username){
        User user = userRepository.findUserByUsername(username);
        return this.tradeTableRepository.findBySender(user.getId());
    }

}
