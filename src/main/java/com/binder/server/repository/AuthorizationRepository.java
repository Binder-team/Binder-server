package com.binder.server.repository;

import com.binder.server.model.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {
}
