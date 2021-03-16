package com.watlas.projetoessentials.util;

import com.watlas.projetoessentials.domain.AnimeDomain;

public class AnimeCreator {

    public static AnimeDomain createAnimToSavede(){
        return AnimeDomain.builder().name("narutinho").build();
    }
    public static AnimeDomain createValidAnime(){
        return AnimeDomain.builder().name("narutinho").id(1L).build();
    }
    public static AnimeDomain createValidUpdateAnime(){
        return AnimeDomain.builder().name("narutinho ").id(1L).build();
    }


}
