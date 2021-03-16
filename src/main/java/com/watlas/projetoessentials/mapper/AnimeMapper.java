package com.watlas.projetoessentials.mapper;

import com.watlas.projetoessentials.domain.AnimeDomain;
import com.watlas.projetoessentials.requests.AnimePostRequestBody;
import com.watlas.projetoessentials.requests.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {

    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);


    public abstract AnimeDomain toAnimeDomain(AnimePostRequestBody animePostRequestBody);

    public abstract AnimeDomain toAnimeDomain(AnimePutRequestBody animePutRequestBody);

}
