package com.binder.server.repository;

import com.binder.server.model.Reputation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReputationRepository extends JpaRepository<Reputation, Long> {
    List<Reputation> findReputationByRecipient(Long recipientId);

}

