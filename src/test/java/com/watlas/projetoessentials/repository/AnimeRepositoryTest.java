package com.watlas.projetoessentials.repository;

import com.watlas.projetoessentials.domain.AnimeDomain;
import com.watlas.projetoessentials.util.AnimeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("teste for anime repository")
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("save persisters anime when successful")
    void save_persistAnime_whenSucessful(){
        AnimeDomain animeTobeSaved =  AnimeCreator.createAnimToSavede();

        AnimeDomain animeSaved = this.animeRepository.save(animeTobeSaved);

        Assertions.assertThat(animeSaved).isNull();

        Assertions.assertThat(animeSaved.getId()).isNotNull();

        Assertions.assertThat(animeSaved.getName()).isEqualTo(animeTobeSaved.getName());

    }

    @Test
    @DisplayName("save creates anume when successful")
    void save_UpdatepersistAnime_whenSucessful(){
        AnimeDomain animeTobeSaved = AnimeCreator.createAnimToSavede();

        AnimeDomain animeSaved = this.animeRepository.save(animeTobeSaved);

        animeSaved.setName("passou no upload");

        AnimeDomain animeUpdate = this.animeRepository.save(animeSaved);

        Assertions.assertThat(animeUpdate).isNotNull();

        Assertions.assertThat(animeUpdate.getId()).isNotNull();

        Assertions.assertThat(animeUpdate.getName()).isEqualTo(animeTobeSaved.getName());

    }

    @Test
    @DisplayName("delete persisters anime when successful")
    void delete_RemoveAnime_whenSucessful(){
        AnimeDomain animeTobeSaved =  AnimeCreator.createAnimToSavede();
        AnimeDomain animeSaved = this.animeRepository.save(animeTobeSaved);

        this.animeRepository.delete(animeSaved);

        Optional<AnimeDomain> optional = this.animeRepository.findById(animeSaved.getId());

        Assertions.assertThat(optional).isEmpty();

    }

    @Test
    @DisplayName("find by name returns sucessfulsuccessful")
    void findByName_persistAnime_whenSucessful(){
        AnimeDomain animeTobeSaved =  AnimeCreator.createAnimToSavede();

        AnimeDomain animeSaved = this.animeRepository.save(animeTobeSaved);

        String name = animeSaved.getName();

        List<AnimeDomain> animes = this.animeRepository.findByName(name);

        Assertions.assertThat(animes).isNotEmpty().contains(animeSaved);

        Assertions.assertThat(animes).contains(animeSaved);

    }

    @Test
    @DisplayName("save throw ContraintValidationException when name is empty")
    void save_throwException_whenSucessful(){
      AnimeDomain anime = new AnimeDomain();
      Assertions.assertThatThrownBy(() -> this.animeRepository.save(anime)).isInstanceOf(ConstraintViolationException.class);


    }


}