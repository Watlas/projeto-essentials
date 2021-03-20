package com.watlas.projetoessentials.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AnimePostRequestBody {
    @NotEmpty(message = "this anime name cannot be empty")
    @NotNull
    @Schema(description = "this is the anime anime", example = "naruto")
    private String name;
}
