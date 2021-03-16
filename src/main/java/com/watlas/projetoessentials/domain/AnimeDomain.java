package com.watlas.projetoessentials.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimeDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "the anime name cannot be empty")
    private String name;


}
