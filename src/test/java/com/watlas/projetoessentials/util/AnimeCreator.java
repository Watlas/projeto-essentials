package com.watlas.projetoessentials.util;

import com.watlas.projetoessentials.domain.AnimeDomain;


public class AnimeCreator {

    public static AnimeDomain createAnimeToBeSaved() {
        return AnimeDomain.builder()
            .name("Tensei Shitara Slime Datta Ken")
            .build();
    }

    public static AnimeDomain createValidAnime() {
        return AnimeDomain.builder()
            .name("Tensei Shitara Slime Datta Ken")
            .id(1L)
            .build();
    }

    public static AnimeDomain createValidUpdatedAnime() {
        return AnimeDomain.builder()
            .name("Tensei Shitara Slime Datta Ken 2")
            .id(1L)
            .build();
    }
}
