package com.watlas.projetoessentials.repository;

import com.watlas.projetoessentials.domain.AnimeDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<AnimeDomain, Long> {
}
