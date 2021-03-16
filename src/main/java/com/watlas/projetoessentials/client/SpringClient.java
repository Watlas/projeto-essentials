package com.watlas.projetoessentials.client;

import com.watlas.projetoessentials.domain.AnimeDomain;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        ResponseEntity<AnimeDomain> e = new RestTemplate().getForEntity("http://localhost:8080/animes/1", AnimeDomain.class);
        log.info(e);

        AnimeDomain forObject = new RestTemplate().getForObject("http://localhost:8080/animes/1", AnimeDomain.class);

        //lista
        AnimeDomain[] forObjectList = new RestTemplate().getForObject("http://localhost:8080/animes/", AnimeDomain[].class);

        log.info(Arrays.toString(forObjectList));

        //listagem do jeito correto
        ResponseEntity<List<AnimeDomain>> animeList = new RestTemplate().exchange("http://localhost:8080/animes/", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<AnimeDomain>>() {});

        log.info(animeList.getBody());

//        AnimeDomain kingdom = AnimeDomain.builder().name("kingdom").build();
//        AnimeDomain animeDomain = new RestTemplate().postForObject("http://localhost:8080/animes/", kingdom, AnimeDomain.class);

        AnimeDomain naruto = AnimeDomain.builder().name("naruto").build();
        ResponseEntity<AnimeDomain> narutoSaved = new RestTemplate().exchange("http://localhost:8080/animes/",
                HttpMethod.POST,
                new HttpEntity<>(naruto, createJsonHeader()),
                AnimeDomain.class);

        //log.info("saved anime {}", animeDomain);
        log.info("saved anime {}", narutoSaved);


        

    }
    private static HttpHeaders createJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
