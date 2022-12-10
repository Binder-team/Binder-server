package com.binder.server.repository;

import com.binder.server.model.TradeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeTableRepository extends JpaRepository<TradeTable, Long> {

    List<TradeTable> findBySender(int sender);
    List<TradeTable> findBySenderAndReceiver(int sender, int receiver);
}
