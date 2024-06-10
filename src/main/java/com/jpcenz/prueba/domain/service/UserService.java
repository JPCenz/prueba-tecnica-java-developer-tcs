package com.jpcenz.prueba.domain.service;

import com.jpcenz.prueba.domain.model.User;
import com.jpcenz.prueba.infraestructure.security.dto.LoginDTO;
import com.jpcenz.prueba.infraestructure.security.dto.TokenDTO;
import com.jpcenz.prueba.infraestructure.security.dto.UserDTO;
import reactor.core.publisher.Mono;

public interface UserService {

    public Mono<TokenDTO> login(LoginDTO dto);
    public Mono<User> create(UserDTO dto);
}
