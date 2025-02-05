package org.example.servicecontrats.service;

import org.example.common.models.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}

