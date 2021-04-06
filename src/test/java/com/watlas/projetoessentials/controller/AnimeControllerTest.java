package com.watlas.projetoessentials.controller;

import java.util.List;

import com.watlas.projetoessentials.controler.AnimeController;
import com.watlas.projetoessentials.domain.AnimeDomain;
import com.watlas.projetoessentials.service.AnimeService;
import com.watlas.projetoessentials.util.AnimeCreator;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class AnimeControllerTest {

    @InjectMocks
    private AnimeController animeController;
    @Mock
    private AnimeService animeServiceMock;

    @BeforeEach
    public void setUp() {
        BDDMockito.when(animeServiceMock.findByIdOrThrowRequestException((long) ArgumentMatchers.anyInt()))
            .thenReturn(AnimeCreator.createValidAnime());

        BDDMockito.when(animeServiceMock.findByName(ArgumentMatchers.anyString()))
            .thenReturn(List.of(AnimeCreator.createValidAnime()));

        BDDMockito.when(animeServiceMock.save(AnimeCreator.createAnimeToBeSaved()))
            .thenReturn(AnimeCreator.createValidAnime());

        BDDMockito.doNothing().when(animeServiceMock).delete(ArgumentMatchers.anyLong());

        BDDMockito.when(animeServiceMock.save(AnimeCreator.createValidAnime()))
            .thenReturn(AnimeCreator.createValidUpdatedAnime());
    }


//    @Test
//    @DisplayName("findById returns an anime when successful")
//    public void findById_ReturnListOfAnimesInsidePageObject_WhenSuccessful() {
//        Long expectedId = AnimeCreator.createValidAnime().getId();
//
//        AnimeDomain anime = animeController.findById(1L).getBody();
//
//        Assertions.assertThat(anime).isNotNull();
//
//        Assertions.assertThat(anime.getId()).isNotNull();
//
//        Assertions.assertThat(anime.getId()).isEqualTo(expectedId);
//    }

    @Test
    @DisplayName("findByName returns a list of animes when successful")
    public void findByName_ReturnListOfAnimes_WhenSuccessful() {
        String expectedName = AnimeCreator.createValidAnime().getName();

        List<AnimeDomain> animeList = animeController.findByName("DBZ").getBody();

        Assertions.assertThat(animeList).isNotNull();

        Assertions.assertThat(animeList).isNotEmpty();

        Assertions.assertThat(animeList.get(0).getName()).isEqualTo(expectedName);
    }



    @Test
    @DisplayName("delete removes the anime when successful")
    public void delete_RemovesAnime_WhenSuccessful() {

        ResponseEntity<Void> responseEntity = animeController.delete(1L);

        Assertions.assertThat(responseEntity).isNotNull();

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThat(responseEntity.getBody()).isNull();
    }

    @Test
    @DisplayName("update save updated anime when successful")
    public void update_SaveUpdatedAnime_WhenSuccessful() {

        ResponseEntity<Void> responseEntity = animeController.replace(AnimeCreator.createValidAnime());

        Assertions.assertThat(responseEntity).isNotNull();

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Assertions.assertThat(responseEntity.getBody()).isNull();
    }
}