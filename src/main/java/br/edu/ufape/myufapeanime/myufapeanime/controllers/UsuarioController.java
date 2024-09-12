package br.edu.ufape.myufapeanime.myufapeanime.controllers;

import br.edu.ufape.myufapeanime.myufapeanime.dto.anime.AnimeDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.usuario.UsuarioDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.usuario.UsuarioResponse;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada.GerenciadorAnimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private GerenciadorAnimes gerenciador;

    /*****  METODOS GET *****/

    //listar todos
    @GetMapping("/list")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<Usuario> usuarios = gerenciador.findAllUsuarios();
        List<UsuarioDTO> result = usuarios.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    //procurar por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        try {
            Usuario usuario = gerenciador.findByIdUsuario(id);
            UsuarioDTO usuarioDTO = convertToDTO(usuario);
            return ResponseEntity.ok(usuarioDTO);
            /*
            return gerenciador.findByIdUsuario(id)
                    .map(this::convertToDTO)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());

             */
        } catch (UsuarioInexistenteException e) {
            // colocar a mensagem específica
            return ResponseEntity.notFound().build();
        }
    }

    //procurar por nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<UsuarioDTO>> findByNome(@PathVariable String nome) {
        List<Usuario> usuarios = gerenciador.findByNomeUsuario(nome);
        
        if (usuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<UsuarioDTO> dtos = usuarios.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
    
    //exibir lista_assistindo
    @GetMapping("/{id}/assistindo")
    public ResponseEntity<List<AnimeDTO>> getAnimesAssistidos(@PathVariable Long id) {
        try {
            //busca a lista de animes assistidos
            List<Anime> animesAssistidos = gerenciador.getAssistindoUsuario(id);

            //converte os Animes para AnimeDTOs
            List<AnimeDTO> dtos = animesAssistidos.stream()
                .map(this::convertToAnimeDTO)
                .collect(Collectors.toList());

            return ResponseEntity.ok(dtos); 
        } catch (UsuarioInexistenteException e) {
            return ResponseEntity.notFound().build(); 
        }
    }

    //exibir lista_completo
    @GetMapping("/{id}/completos")
    public ResponseEntity<List<AnimeDTO>> getAnimesCompletos(@PathVariable Long id) {
        try {
            List<Anime> animesAssistidos = gerenciador.getCompletosUsuario(id);

            List<AnimeDTO> dtos = animesAssistidos.stream()
                .map(this::convertToAnimeDTO)
                .collect(Collectors.toList());

            return ResponseEntity.ok(dtos); 
        } catch (UsuarioInexistenteException e) {
            return ResponseEntity.notFound().build(); 
        }
    }

    //exibir lista_quero_assistir
    @GetMapping("/{id}/quero-assistir")
    public ResponseEntity<List<AnimeDTO>> getAnimesQueroAssistir(@PathVariable Long id) {
        try {
            List<Anime> animesAssistidos = gerenciador.getQueroAssistirUsuario(id);

            List<AnimeDTO> dtos = animesAssistidos.stream()
                .map(this::convertToAnimeDTO)
                .collect(Collectors.toList());

            return ResponseEntity.ok(dtos); 
        } catch (UsuarioInexistenteException e) {
            return ResponseEntity.notFound().build(); 
        }
    }


    /*****  METODOS POST *****/

    //Cadastrar novo usuario
    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = convertToEntity(usuarioDTO);
            Usuario novoUsuario = gerenciador.saveUsuario(usuario);
            UsuarioDTO novoUsuarioDTO = convertToDTO(novoUsuario);
            UsuarioResponse response = new UsuarioResponse("Usuário cadastrado com sucesso!", novoUsuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (UsuarioDuplicadoException e) {
            
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /*****  METODOS PUT *****/
    //update usuário existente
    @PutMapping("atualizar/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = convertToEntity(usuarioDTO);
            usuario.setId(id); 
            
            Usuario usuarioAtualizado = gerenciador.updateUsuario(usuario);
            
            UsuarioDTO usuarioAtualizadoDTO = convertToDTO(usuarioAtualizado);

            UsuarioResponse response = new UsuarioResponse("Usuário atualizado com sucesso!", usuarioAtualizadoDTO);
            return ResponseEntity.ok(response);

        } catch (UsuarioInexistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /*****  METODOS DELETE *****/
    //apagar usuario por id
    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable Long id) {
        try {
            gerenciador.deleteUsuarioById(id);
            return ResponseEntity.noContent().build(); 
        } catch (UsuarioInexistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    /*****  METODOS PRIVADOS *****/
    //converte Anime em AnimeDTO
    private AnimeDTO convertToAnimeDTO(Anime anime) {
        AnimeDTO dto = new AnimeDTO();
        dto.setId(anime.getId());
        dto.setNome(anime.getNome());
        dto.setGenero(anime.getGenero());
        dto.setNumeroEpisodios(anime.getNumeroEpisodios());
        return dto;
    }

    //converte Usuario em UsuarioDTO
    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        return dto;
    }

    //converte UsuarioDTO em Usuario
    private Usuario convertToEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        return usuario;
    }
    

}
