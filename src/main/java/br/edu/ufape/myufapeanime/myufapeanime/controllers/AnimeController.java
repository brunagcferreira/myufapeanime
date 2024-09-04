package br.edu.ufape.myufapeanime.myufapeanime.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;

@RestController
@RequestMapping("/animes")
public class AnimeController {
    @Autowired
    private InterfaceRepositorioAnimes repositorioAnimes;

    @GetMapping
    public ResponseEntity<List<Anime>> findAll() {
    List<Anime> result = repositorioAnimes.findAll();
    return ResponseEntity.ok(result);
    }
}

