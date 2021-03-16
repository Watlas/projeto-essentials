package com.watlas.projetoessentials.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnimePostRequestBody {
    @NotEmpty(message = "this anime name cannot be empty")
    @NotNull
    private String name;
}
