package com.binder.server.repository;

import com.binder.server.model.Reputation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReputationRepository extends JpaRepository<Reputation, Long> {

}

