package com.watlas.projetoessentials.repository;

import com.watlas.projetoessentials.domain.AnimeDomain;
import com.watlas.projetoessentials.util.AnimeCreator;
import com.watlas.projetoessentials.util.DateUtil;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

//@ExtendWith(SpringExtension.class)
@DataJpaTest
@DisplayName("Anime Repository Tests")
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AnimeRepositoryTest {

    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save creates anime when successful")
    public void save_PersistAnime_WhenSuccessful() {
        AnimeDomain anime = AnimeCreator.createAnimeToBeSaved();

        AnimeDomain savedAnime = this.animeRepository.save(anime);
        Assertions.assertThat(savedAnime.getId()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isEqualTo(anime.getName());

    }

    @Test
    @DisplayName("Save updates anime when successful")
    public void save_UpdateAnime_WhenSuccessful() {
        AnimeDomain anime = AnimeCreator.createAnimeToBeSaved();

        AnimeDomain savedAnime = this.animeRepository.save(anime);

        savedAnime.setName("That time I got reincarnated as a slime");

        AnimeDomain updatedAnime = this.animeRepository.save(savedAnime);

        Assertions.assertThat(savedAnime.getId()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isEqualTo(updatedAnime.getName());

    }

    @Test
    @DisplayName("Delete removes anime when successful")
    public void delete_RemoveAnime_WhenSuccessful() {
        AnimeDomain anime = AnimeCreator.createAnimeToBeSaved();

        AnimeDomain savedAnime = this.animeRepository.save(anime);

        this.animeRepository.delete(anime);

        Optional<AnimeDomain> animeOptional = this.animeRepository.findById(savedAnime.getId());

        Assertions.assertThat(animeOptional.isEmpty()).isTrue();

    }

    @Test
    @DisplayName("Find by name returns anime when successful")
    public void findByName_ReturnAnimes_WhenSuccessful() {
        AnimeDomain anime = AnimeCreator.createAnimeToBeSaved();

        AnimeDomain savedAnime = this.animeRepository.save(anime);

        String name = savedAnime.getName();

        List<AnimeDomain> animeList = this.animeRepository.findByName(name);

        Assertions.assertThat(animeList).isNotEmpty();

        Assertions.assertThat(animeList).contains(savedAnime);

    }

    @Test
    @DisplayName("Find by name returns empty list when no anime is found")
    public void findByName_ReturnEmptyList_WhenAnimeNotFound() {
        String name = "fake-name";

        List<AnimeDomain> animeList = this.animeRepository.findByName(name);

        Assertions.assertThat(animeList).isEmpty();
    }


    
}