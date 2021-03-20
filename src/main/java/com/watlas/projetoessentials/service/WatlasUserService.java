package com.watlas.projetoessentials.service;

import com.watlas.projetoessentials.repository.WatlasUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WatlasUserService implements UserDetailsService {

    private final WatlasUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) {

        return Optional.ofNullable(repository.findByUsername(s)).orElseThrow(
                () -> new UsernameNotFoundException("Usuario nao encontrado")
        );
    }
}
