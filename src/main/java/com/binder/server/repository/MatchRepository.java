package com.binder.server.repository;

import com.binder.server.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository <Match, Long>{
    List<Match> findByUser1IdOrUser2Id(Long id1, Long id2);
    Match findMatchById (Long id);

    List<Match> findByBook1IdOrBook2Id(Long book1Id, Long book2Id);
}
