package com.watlas.projetoessentials.service;
//classe para regra de negocio


import com.watlas.projetoessentials.domain.AnimeDomain;
import com.watlas.projetoessentials.repository.AnimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository repository;

    public List<AnimeDomain> listAll(){
        return repository.findAll();

    }
}
