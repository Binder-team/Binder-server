package com.binder.server.repository;

import com.binder.server.model.TradeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeTableRepository extends JpaRepository<TradeTable, Long> {

}
