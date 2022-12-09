package com.binder.server.service;

import com.binder.server.model.DummyAuth;
import com.binder.server.repository.AuthorizationRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    AuthorizationRepository authorizationRepository;
    public AuthorizationService (AuthorizationRepository authorizationRepository){
        this.authorizationRepository = authorizationRepository;
    }

    public void insertAuthID(Long user_id, String username, String token) {
        DummyAuth userTokenSystem = new DummyAuth(user_id, username, token);
        authorizationRepository.save(userTokenSystem);

    }
}
