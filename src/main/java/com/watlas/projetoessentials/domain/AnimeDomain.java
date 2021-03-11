package com.watlas.projetoessentials.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimeDomain {

    @Id
    private Long id;

    private String nome;

}
