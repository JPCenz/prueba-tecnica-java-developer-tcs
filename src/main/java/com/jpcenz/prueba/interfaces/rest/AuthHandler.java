package com.jpcenz.prueba.interfaces.rest;

import com.jpcenz.prueba.domain.model.User;
import com.jpcenz.prueba.domain.service.UserService;
import com.jpcenz.prueba.domain.validation.ObjectValidator;
import com.jpcenz.prueba.infraestructure.security.dto.LoginDTO;
import com.jpcenz.prueba.infraestructure.security.dto.TokenDTO;
import com.jpcenz.prueba.infraestructure.security.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthHandler {

    private final UserService userService;

    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> login(ServerRequest request) {
        Mono<LoginDTO> dtoMono = request.bodyToMono(LoginDTO.class).doOnNext(objectValidator::validate);
        return dtoMono
                .flatMap(dto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userService.login(dto), TokenDTO.class));
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        Mono<UserDTO> dtoMono = request.bodyToMono(UserDTO.class).doOnNext(objectValidator::validate);
        return dtoMono
                .flatMap(dto -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(userService.create(dto), User.class));
    }
}