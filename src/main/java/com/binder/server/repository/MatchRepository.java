package com.binder.server.repository;

import com.binder.server.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository <Match, Long>{
    List<Match> findMatchByBook1IdOrBook2Id(Long id1, Long id2);
}
