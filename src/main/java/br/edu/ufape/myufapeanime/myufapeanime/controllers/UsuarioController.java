package br.edu.ufape.myufapeanime.myufapeanime.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioUsuarios;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private InterfaceRepositorioUsuarios repositorioUsuarios;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
    List<Usuario> result = repositorioUsuarios.findAll();
    return ResponseEntity.ok(result);
    }
}
