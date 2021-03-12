package com.watlas.projetoessentials.controler;

import com.watlas.projetoessentials.domain.AnimeDomain;
import com.watlas.projetoessentials.requests.AnimePostRequestBody;
import com.watlas.projetoessentials.requests.AnimePutRequestBody;
import com.watlas.projetoessentials.service.AnimeService;
import com.watlas.projetoessentials.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/animes")
@RequiredArgsConstructor
public class AnimeController {

    private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<List<AnimeDomain>> list(){
        List<AnimeDomain> list = animeService.listAll();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimeDomain> findById(@PathVariable Long id){
        return ResponseEntity.ok(animeService.findByIdOrThrowRequestException(id));
    }

    @GetMapping("/find")  //   /find?name={oque voce digitou para procurar}  - name é a variavel aqui em baixo
    public ResponseEntity<List<AnimeDomain>> findByName(@RequestParam String name){
        return ResponseEntity.ok(animeService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<AnimeDomain> save(@Valid @RequestBody AnimePostRequestBody obj){
        return new ResponseEntity<>(animeService.save(obj), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        animeService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody obj){
        animeService.replace(obj);
        return ResponseEntity.noContent().build();
    }

}
