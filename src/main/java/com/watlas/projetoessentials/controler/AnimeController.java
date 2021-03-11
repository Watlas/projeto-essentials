package com.watlas.projetoessentials.controler;

import com.watlas.projetoessentials.domain.AnimeDomain;
import com.watlas.projetoessentials.service.AnimeService;
import com.watlas.projetoessentials.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping
@RestController("/animes")
@RequiredArgsConstructor
public class AnimeController {

    private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping
    public List<AnimeDomain> list(){
        return ResponseEntity.ok(animeService.listAll()).getBody();
    }
}
