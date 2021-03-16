package com.watlas.projetoessentials.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AnimePutRequestBody {
    private Long id;
    private final String name;
}
