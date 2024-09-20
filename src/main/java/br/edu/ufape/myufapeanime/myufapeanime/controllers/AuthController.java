package br.edu.ufape.myufapeanime.myufapeanime.controllers;
import br.edu.ufape.myufapeanime.myufapeanime.dto.login.LoginDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.mappers.UsuarioMapper;
import br.edu.ufape.myufapeanime.myufapeanime.dto.usuario.UsuarioDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.usuario.UsuarioResponse;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Adm;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioDuplicadoException;
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
    private GerenciadorAnimes gerenciador;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        try{
            Usuario usuario = gerenciador.login(loginDTO.getEmail(), loginDTO.getSenha());
            session.setAttribute("user", usuario);
            return ResponseEntity.ok(usuario);

        }catch (UsuarioInexistenteException | UsuarioSenhaInvalidaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/cadastrar")
    //TODO: cadastrar adm e usuario aqui de forma diferente
    public ResponseEntity<Object> cadastrar(@RequestBody UsuarioDTO usuarioDTO, HttpSession session) {
        try {
            Adm usuario = UsuarioMapper.convertToAdm(usuarioDTO);
            Adm novoUsuario = (Adm) gerenciador.createUsuario(usuario);
            UsuarioDTO novoUsuarioDTO = UsuarioMapper.convertToDTO(novoUsuario);
            UsuarioResponse response = new UsuarioResponse("Usuário cadastrado com sucesso!", novoUsuarioDTO);
            session.setAttribute("user", usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (UsuarioDuplicadoException | UsuarioSenhaInvalidaException e) {

            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
