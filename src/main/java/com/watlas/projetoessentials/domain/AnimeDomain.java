package com.watlas.projetoessentials.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;

@Entity(name = "ANIME")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimeDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}
