package com.watlas.projetoessentials.controler;

import com.watlas.projetoessentials.domain.AnimeDomain;
import com.watlas.projetoessentials.service.AnimeService;
import com.watlas.projetoessentials.util.AnimeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
class AnimeControllerTest {

    @InjectMocks //classe que vou testar
    private AnimeController animeController;

    @Mock
    private AnimeService animeService; // classes utilizadas dentro do injectmocks

    @BeforeEach
    void setUp(){
        List<AnimeDomain> animePage = new ArrayList<>(List.of(AnimeCreator.createValidAnime()));
        BDDMockito.when(animeService.listAll()).thenReturn(animePage);
    }


    @DisplayName("list return lis of anime list object when successful")
    @Test
    void list_ReturnsListOfAnimes(){
        String name = AnimeCreator.createValidAnime().getName();
        ResponseEntity<List<AnimeDomain>> list = animeController.list();

        Assertions.assertThat(list).isNotNull();

    }

}