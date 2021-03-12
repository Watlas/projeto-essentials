package com.watlas.projetoessentials.service;
//classe para regra de negocio


import com.watlas.projetoessentials.domain.AnimeDomain;
import com.watlas.projetoessentials.exception.BadResquestException;
import com.watlas.projetoessentials.mapper.AnimeMapper;
import com.watlas.projetoessentials.repository.AnimeRepository;
import com.watlas.projetoessentials.requests.AnimePostRequestBody;
import com.watlas.projetoessentials.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository repository;

    public List<AnimeDomain> listAll() {
        return repository.findAll();

    }
    public List<AnimeDomain> findByName(String name) {
        return repository.findByName(name);

    }


    public AnimeDomain findByIdOrThrowRequestException(Long id) {
        return repository.findById(id).orElseThrow(() -> new BadResquestException("anime not fould"));
    }

    public AnimeDomain save(AnimePostRequestBody animePostRequestBody) {
        return repository.save(AnimeMapper.INSTANCE.toAnimeDomain(animePostRequestBody));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        AnimeDomain savedAnime = findByIdOrThrowRequestException(animePutRequestBody.getId());
        AnimeDomain animeDomain = AnimeMapper.INSTANCE.toAnimeDomain(animePutRequestBody);
        animeDomain.setId(savedAnime.getId());
        repository.save(animeDomain);

    }
}
