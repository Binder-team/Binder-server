package com.binder.server.repository;

import com.binder.server.model.DummyAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationRepository extends JpaRepository<DummyAuth, Long> {
}
