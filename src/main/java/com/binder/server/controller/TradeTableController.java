package com.binder.server.controller;

import com.binder.server.exception.ResourceNotFoundException;
import com.binder.server.model.TradeTable;
import com.binder.server.model.User;
import com.binder.server.model.UserBooks;
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
    private final UserBooksRepository userBooksRepository;

    public TradeTableController(TradeTableRepository tradeTableRepository, UserRepository userRepository, UserBooksRepository userBooksRepository) {
        this.tradeTableRepository = tradeTableRepository;
        this.userRepository = userRepository;
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
    public List<TradeTable> createTradeTable(@RequestBody UserBooks book, @PathVariable(value = "username") String username){
        //Should have bookID(which has book owner ID) and token
        User sender = userRepository.findUserByUsername(username);
        TradeTable trade = new TradeTable();
        trade.setSender(sender.getId());
        trade.setReceiver(book.getUser_id());
        trade.setBook_id(book.getId());
        trade.setIs_accepted(false);
        trade.setIs_exchanged(false);
        trade.setIs_matched(false);
        this.tradeTableRepository.save(trade);
        List<TradeTable> match = tradeTableRepository.findBySenderAndReceiver(book.getUser_id(), sender.getId());
        return match;

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

    @GetMapping("trade_table/sender/{username}")
    public List<TradeTable> findByBooksNotByUserId(@PathVariable(value = "username") Long id){
        return this.tradeTableRepository.findBySender(id);
    }

    @GetMapping("trade_table/liked/{username}")
    public List<TradeTable> findLikedBooksByUsername(@PathVariable(value = "username") String username){
        User user = userRepository.findUserByUsername(username);
        return this.tradeTableRepository.findBySender(user.getId());
    }
}
