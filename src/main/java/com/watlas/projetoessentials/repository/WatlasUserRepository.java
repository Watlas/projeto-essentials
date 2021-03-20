package com.watlas.projetoessentials.repository;

import com.watlas.projetoessentials.domain.WatlasUserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatlasUserRepository extends JpaRepository<WatlasUserDomain, Long> {

    WatlasUserDomain findByUsername(String username);
}
