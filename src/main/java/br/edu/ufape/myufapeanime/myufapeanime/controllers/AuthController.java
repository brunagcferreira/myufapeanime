package br.edu.ufape.myufapeanime.myufapeanime.controllers;
import br.edu.ufape.myufapeanime.myufapeanime.dto.login.LoginDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.usuario.UsuarioDTO;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioSenhaInvalidaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada.GerenciadorAnimes;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private GerenciadorAnimes gerenciadorAnimes;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        try{
            Usuario user = gerenciadorAnimes.login(loginDTO.getEmail(), loginDTO.getSenha());
            session.setAttribute("user", user);
            return ResponseEntity.ok(user);

        }catch (UsuarioInexistenteException | UsuarioSenhaInvalidaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    /*
    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrar(@RequestBody UsuarioDTO usuarioDTO, HttpSession session) {


    }

     */



}
