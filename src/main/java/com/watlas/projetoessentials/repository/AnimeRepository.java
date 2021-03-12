package com.watlas.projetoessentials.repository;

import com.watlas.projetoessentials.domain.AnimeDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.List;

public interface AnimeRepository extends JpaRepository<AnimeDomain, Long> {

    List<AnimeDomain> findByName(String name);

}
