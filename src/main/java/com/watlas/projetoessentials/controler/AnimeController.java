package com.watlas.projetoessentials.controler;

import com.watlas.projetoessentials.domain.AnimeDomain;
import com.watlas.projetoessentials.requests.AnimePostRequestBody;
import com.watlas.projetoessentials.requests.AnimePutRequestBody;
import com.watlas.projetoessentials.service.AnimeService;
import com.watlas.projetoessentials.util.DateUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/animes")
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Log4j2
public class AnimeController {

    private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping("/page")
    @Operation(summary = "List all animes paginaned")
    public ResponseEntity<Page<AnimeDomain>> listPage(@Parameter(hidden = true) Pageable pageable){
        return ResponseEntity.ok(animeService.listAll(pageable)); //animes?size=5&page=2 - 2 pode mudar
    }
    @GetMapping
    public ResponseEntity<List<AnimeDomain>> list(){
        return ResponseEntity.ok(animeService.listAll());
    }

//    @GetMapping("/{id}")
//  //  @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<AnimeDomain> findById(@PathVariable Long id){
//        return ResponseEntity.ok(animeService.findByIdOrThrowRequestException(id));
//    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AnimeDomain> findById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        log.info("User logged in {}", userDetails);
        return ResponseEntity.ok(animeService.findByIdOrThrowRequestException(id));
    }

    //metodo para verificar se  usuario esta autenticado
    @GetMapping("by-id/{id}")
    public ResponseEntity<AnimeDomain> findByIdAuthenticationPrincipal(@PathVariable Long id,
                                                                       @AuthenticationPrincipal UserDetails userDetails){
        log.info(userDetails);
        return ResponseEntity.ok(animeService.findByIdOrThrowRequestException(id));
    }

    @GetMapping("/find")  //   /find?name={oque voce digitou para procurar}  - name é a variavel aqui em baixo
    public ResponseEntity<List<AnimeDomain>> findByName(@RequestParam String name){
        return ResponseEntity.ok(animeService.findByName(name));
    }

    @PostMapping
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AnimeDomain> save(@Valid @RequestBody AnimePostRequestBody obj){
        return new ResponseEntity<>(animeService.save(obj), HttpStatus.CREATED);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        animeService.delete(id);
        return ResponseEntity.noContent().build();
    }
    //usando DTO
//    @PutMapping
//    public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody obj){
//        animeService.replace(obj);
//        return ResponseEntity.noContent().build();
//    }
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AnimeDomain obj){
        animeService.replace(obj);
        return ResponseEntity.noContent().build();
    }

}
