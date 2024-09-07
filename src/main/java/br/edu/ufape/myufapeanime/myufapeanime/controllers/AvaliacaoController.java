package br.edu.ufape.myufapeanime.myufapeanime.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada.GerenciadorAnimes;


@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private GerenciadorAnimes gerenciador;

}
