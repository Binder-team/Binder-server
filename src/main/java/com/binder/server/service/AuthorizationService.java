package com.binder.server.service;

import com.binder.server.model.Authorization;
import com.binder.server.repository.AuthorizationRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    AuthorizationRepository authorizationRepository;
    public AuthorizationService (AuthorizationRepository authorizationRepository){
        this.authorizationRepository = authorizationRepository;
    }

    public void insertAuthID(Long user_id, String username, Double token) {
        Authorization userTokenSystem = new Authorization(user_id, username, token);
        authorizationRepository.save(userTokenSystem);

    }
}
