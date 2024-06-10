package com.jpcenz.prueba.domain.service.impl;


import com.jpcenz.prueba.domain.model.Role;
import com.jpcenz.prueba.domain.model.User;
import com.jpcenz.prueba.domain.repository.UserRepository;
import com.jpcenz.prueba.domain.service.UserService;
import com.jpcenz.prueba.infraestructure.security.JwtProvider;
import com.jpcenz.prueba.infraestructure.security.dto.LoginDTO;
import com.jpcenz.prueba.infraestructure.security.dto.TokenDTO;
import com.jpcenz.prueba.infraestructure.security.dto.UserDTO;
import com.jpcenz.prueba.interfaces.rest.exception.CustomErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

    @Service
    @Slf4j
    @RequiredArgsConstructor
    public class UserServiceImpl implements UserService {

        private final UserRepository userRepository;

        private final JwtProvider jwtProvider;

        private final PasswordEncoder passwordEncoder;

        public Mono<TokenDTO> login(LoginDTO dto) {
            var user = userRepository.findByUsernameOrEmail(dto.getUsername(), dto.getUsername());
            var usermono = Mono.just(user);
            return usermono
                .filter(u -> passwordEncoder.matches(dto.getPassword(), u.get().getPassword()))
                        .map(u -> new TokenDTO(jwtProvider.generateToken(u.get())))
                        .switchIfEmpty(Mono.error(new CustomErrorException( "bad credentials")));




        }

        public Mono<User> create(UserDTO dto) {
            User user = User.builder()
                    .username(dto.getUsername())
                    .email(dto.getEmail())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .roles(Role.ROLE_USER.name())
                    .build();
            var userExists = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail()).isPresent();
            Mono<Boolean> exist = Mono.just(userExists);
            return exist
                    .flatMap(exists -> exists ?
                            Mono.error(new CustomErrorException("username or email already in use"))
                            : Mono.just(userRepository.save(user)));
        }

}
