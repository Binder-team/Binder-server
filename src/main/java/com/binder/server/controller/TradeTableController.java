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
    private final UserBooksRepository userBooksRepository;

    public TradeTableController(TradeTableRepository tradeTableRepository, UserRepository userRepository, MatchRepository matchRepository, UserBooksRepository userBooksRepository) {
        this.tradeTableRepository = tradeTableRepository;
        this.userRepository = userRepository;
        this.matchRepository = matchRepository;
        this.userBooksRepository = userBooksRepository;
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
        TradeTable likes = tradeTableRepository.findBySenderAndBookId(sender.getId(), book.getId());
        if (likes == null) {
            TradeTable trade = new TradeTable();
            trade.setSender(sender.getId());
            trade.setReceiver(book.getUserId());
            trade.setBookId(book.getId());
            trade.setIsAccepted(false);
            trade.setIsExchanged(false);
            trade.setIsMatched(false);

            List<TradeTable> matchList = tradeTableRepository.findBySenderAndReceiver(book.getUserId(), sender.getId());
            if(matchList.size() != 0) {
                trade.setIsMatched(true);

                for (int i = 0; i < matchList.size(); i++) {
                    Match match = new Match();
                    TradeTable matchTrade = matchList.get(i);
                    User receiver = userRepository.findUserById(matchTrade.getSender());
                    UserBooks book2 = userBooksRepository.getReferenceById(matchTrade.getBookId());
                    matchTrade.setIsMatched(true);

                    match.setUser1Id(sender.getId());
                    match.setUsername1(sender.getUsername());
                    match.setUser2Id((receiver.getId()));
                    match.setUsername2(receiver.getUsername());
                    match.setBook1Id(matchTrade.getBookId());
                    match.setBook2Id(book.getId());
                    match.setThumbnail1(book2.getThumbnail_url());
                    match.setThumbnail2(book.getThumbnail_url());
                    match.setTitle1(book2.getTitle());
                    match.setTitle2(book.getTitle());
                    match.setAuthor1(book2.getAuthor());
                    match.setAuthor2(book.getAuthor());
                    match.setCondition1(book2.getCondition());
                    match.setCondition2(book.getCondition());
                    match.setEmail1(sender.getEmail());
                    match.setEmail2(receiver.getEmail());
                    match.setPhone1(sender.getPhone_number());
                    match.setPhone2(receiver.getPhone_number());
                    match.setDidUser1Accept(false);
                    match.setDidUser2Accept(false);
                    match.setDidUser1Exchange(false);
                    match.setDidUser2Exchange(false);

                    book.setIsAvailable(false);
                    book2.setIsAvailable(false);
                    this.userBooksRepository.save(book);
                    this.userBooksRepository.save(book2);

                    this.matchRepository.save(match);
                }
            }
            this.userBooksRepository.save(book);
            this.tradeTableRepository.save(trade);

            return matchList.size();
        } else {
            return 0;
        }

    }
    @PutMapping("trade_table/{id}")
    public ResponseEntity<TradeTable> updateTradeTable(@PathVariable(value ="id") Long tradeTableId,
                                                       @Validated @RequestBody TradeTable tradeTableDetails) throws ResourceNotFoundException {
        TradeTable tradeTable = tradeTableRepository.findById(tradeTableId)
                .orElseThrow(() -> new ResourceNotFoundException("TradeTable not found for this id :: " + tradeTableId));
        tradeTable.setSender(tradeTableDetails.getSender());
        tradeTable.setReceiver(tradeTableDetails.getReceiver());
        tradeTable.setBookId(tradeTableDetails.getBookId());
        tradeTable.setIsMatched(tradeTableDetails.isIsMatched());
        tradeTable.setIsAccepted(tradeTableDetails.isIsAccepted());
        tradeTable.setIsExchanged(tradeTableDetails.isIsExchanged());
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
