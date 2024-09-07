package br.edu.ufape.myufapeanime.myufapeanime.controllers;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Adm;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAdm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import java.security.Principal;

@RestController
@RequestMapping("/adm")
public class AdmController {

    private final InterfaceRepositorioAdm interfaceRepositorioAdm;

    public AdmController(InterfaceRepositorioAdm interfaceRepositorioAdm) {
        this.interfaceRepositorioAdm = interfaceRepositorioAdm;
    }

    @GetMapping("/test")
    public ResponseEntity<String> registration(Principal principal) throws UsuarioInexistenteException {
        Usuario usuario = (Usuario) interfaceRepositorioAdm.findByEmail(principal.getName()).orElseThrow(UsuarioInexistenteException::new);


        if (usuario instanceof Adm) {
            return ResponseEntity.ok("Welcome, come to registration");
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
    }
}
