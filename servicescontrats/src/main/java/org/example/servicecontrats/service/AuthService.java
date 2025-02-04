package org.example.servicecontrats.service;

import org.example.servicecontrats.Dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}

