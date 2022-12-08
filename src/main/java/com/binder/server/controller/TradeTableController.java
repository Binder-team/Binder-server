package com.binder.server.controller;

import com.binder.server.exception.ResourceNotFoundException;
import com.binder.server.model.TradeTable;
import com.binder.server.repository.TradeTableRepository;
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

    public TradeTableController(TradeTableRepository tradeTableRepository) {
        this.tradeTableRepository = tradeTableRepository;
    }

    @GetMapping("trade_tables")
    public List<TradeTable> getAllTradeTables() {
        return this.tradeTableRepository.findAll();
    }

    @GetMapping("trade_tables/{id}")
    public ResponseEntity<TradeTable> getUserTableById(@PathVariable(value = "id") Long tradeTableId)
     throws ResourceNotFoundException {
        TradeTable tradeTable = tradeTableRepository.findById(tradeTableId)
                .orElseThrow(() -> new ResourceNotFoundException("TradeTable not found for this id :: " + tradeTableId));
        return ResponseEntity.ok().body(tradeTable);
 }
    @PostMapping("user_table")
    public TradeTable createTradeTable(@RequestBody TradeTable tradeTable) {
        return this.tradeTableRepository.save(tradeTable);
 }
    @PutMapping("trade_tables/{id}")
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

    @DeleteMapping("trade_tables/{id}")
    public Map<String, Boolean> deleteTradeTable(@PathVariable(value = "id") Long tradeTableId) throws ResourceNotFoundException {
        TradeTable tradeTable = tradeTableRepository.findById(tradeTableId)
                .orElseThrow(() -> new ResourceNotFoundException("TradeTables not found for this id ::" + tradeTableId));
        this.tradeTableRepository.delete(tradeTable);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    @GetMapping("trade_tables/sender/{id}")
    public List<TradeTable> findByBooksNotByUserId(@PathVariable(value = "id") int id){
        return this.tradeTableRepository.findBySender(id);
    }


}
