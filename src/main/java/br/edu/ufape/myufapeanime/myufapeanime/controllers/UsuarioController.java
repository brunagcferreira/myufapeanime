package br.edu.ufape.myufapeanime.myufapeanime.controllers;

import br.edu.ufape.myufapeanime.myufapeanime.dto.anime.AnimeDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.avaliacao.AvaliacaoPeloIdDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.mappers.AvaliacaoMapper;
import br.edu.ufape.myufapeanime.myufapeanime.dto.mappers.UsuarioMapper;
import br.edu.ufape.myufapeanime.myufapeanime.dto.usuario.UsuarioDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.usuario.UsuarioResponse;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAutenticacaoExceptions.AutorizacaoNegadaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada.GerenciadorAnimes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario", description = "API para operações relacionadas a usuários")
public class UsuarioController {

    @Autowired
    private GerenciadorAnimes gerenciador;

    /*****  METODOS GET *****/

    //listar todos os usuarios
    @GetMapping("/list")
    @Operation(
        summary = "Listar todos os usuários",
        description = "Retorna uma lista com todos os usuários cadastrados no sistema.",
        tags = {"Usuario"},
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado")
        })
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<Usuario> usuarios = gerenciador.findAllUsuarios();
        List<UsuarioDTO> result = usuarios.stream()
            .map(UsuarioMapper::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    //procurar por ID
    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar usuário por ID",
        description = "Retorna os detalhes de um usuário específico com base no ID fornecido.",
        tags = {"Usuario"},
        parameters = {
            @Parameter(name = "id", description = "ID do usuário a ser buscado", required = true, example = "1")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
        })
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        try {
            Usuario usuario = gerenciador.findUsuarioById(id);
            UsuarioDTO usuarioDTO = UsuarioMapper.convertToDTO(usuario);
            return ResponseEntity.ok(usuarioDTO);

        } catch (UsuarioInexistenteException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //procurar por nome
    @GetMapping("/nome/{nome}")
    @Operation(
        summary = "Buscar usuários por nome",
        description = "Retorna uma lista de usuários cujo nome corresponde ao fornecido.",
        tags = {"Usuario"},
        parameters = {
            @Parameter(name = "nome", description = "Nome do usuário a ser buscado", required = true, example = "João")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado com o nome fornecido")
        })
    public ResponseEntity<List<UsuarioDTO>> findByNome(@PathVariable String nome) {
        List<Usuario> usuarios = gerenciador.findByNomeUsuario(nome);
        
        if (usuarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<UsuarioDTO> dtos = usuarios.stream()
                .map(UsuarioMapper::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
    
    //exibir lista_assistindo
    @GetMapping("/assistindo")
    @Operation(
        summary = "Listar animes na lista 'assistindo' do usuário",
        description = "Retorna a lista de animes que o usuário está atualmente assistindo.",
        tags = {"Usuario", "Animes"},
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de animes retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário ou lista de animes não encontrada")
        })
    public ResponseEntity<List<AnimeDTO>> getAnimesAssistidosUsuario(HttpSession session) {
        try {
            //busca a lista de animes assistidos
            Usuario usuario = (Usuario) session.getAttribute("user");

            List<Anime> animesAssistidos = gerenciador.getAssistindoUsuario(usuario);

            //converte os Animes para AnimeDTOs
            List<AnimeDTO> dtos = animesAssistidos.stream()
                .map(UsuarioMapper::convertToAnimeDTO)
                .collect(Collectors.toList());

            return ResponseEntity.ok(dtos); 
        } catch (UsuarioInexistenteException | AutorizacaoNegadaException e) {
            return ResponseEntity.notFound().build(); 
        }
    }

    //exibir lista_completo
    @GetMapping("/completos")
    @Operation(
        summary = "Listar animes completos do usuário",
        description = "Retorna a lista de animes que o usuário completou.",
        tags = {"Usuario", "Animes"},
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de animes retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário ou lista de animes não encontrada")
        })
    public ResponseEntity<List<AnimeDTO>> getAnimesCompletos(HttpSession session) {
        try {
            Usuario usuario = (Usuario) session.getAttribute("user");
            List<Anime> animesAssistidos = gerenciador.getCompletosUsuario(usuario);

            List<AnimeDTO> dtos = animesAssistidos.stream()
                .map(UsuarioMapper::convertToAnimeDTO)
                .collect(Collectors.toList());

            return ResponseEntity.ok(dtos);
        } catch (UsuarioInexistenteException | AutorizacaoNegadaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    //exibir lista_quero_assistir
    @GetMapping("/quero-assistir")
    @Operation(
        summary = "Listar animes na lista 'quero assistir' do usuário",
        description = "Retorna a lista de animes que o usuário marcou como 'quero assistir'.",
        tags = {"Usuario", "Animes"},
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de animes retornada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário ou lista de animes não encontrada")
        })
    public ResponseEntity<List<AnimeDTO>> getAnimesQueroAssistir(HttpSession session) {
        try {
            Usuario usuario = (Usuario) session.getAttribute("user");

            List<Anime> animesAssistidos = gerenciador.getQueroAssistirUsuario(usuario);

            List<AnimeDTO> dtos = animesAssistidos.stream()
                .map(UsuarioMapper::convertToAnimeDTO)
                .collect(Collectors.toList());

            return ResponseEntity.ok(dtos);
        } catch (UsuarioInexistenteException e) {
            return ResponseEntity.notFound().build();
        } catch (AutorizacaoNegadaException e) {
            throw new RuntimeException(e);
        }
    }


    /*****  METODOS POST *****/

    // Adicionar anime à lista "assistindo"
    @PostMapping("/assistindo/{animeId}")
        @Operation(
        summary = "Adicionar anime à lista 'assistindo'",
        description = "Adiciona um anime à lista de animes que o usuário está assistindo.",
        tags = {"Usuario", "Animes"},
        parameters = {
            @Parameter(name = "animeId", description = "ID do anime a ser adicionado", required = true, example = "1")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Anime adicionado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário ou anime não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
        })
    public ResponseEntity<Object> adicionarAnimeAssistindo(@PathVariable Long animeId, HttpSession session) {
        try {
            Usuario usuario = (Usuario) session.getAttribute("user");

            gerenciador.adicionarAnimeAssistindo(usuario, animeId);
            return ResponseEntity.ok("Anime adicionado à lista 'assistindo' com sucesso!");
        } catch (UsuarioInexistenteException | AnimeInexistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (AutorizacaoNegadaException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    // Adicionar anime à lista "completo"
    @PostMapping("/completo/{animeId}")
    @Operation(
        summary = "Adicionar anime à lista 'completo'",
        description = "Adiciona um anime à lista de animes que o usuário completou.",
        tags = {"Usuario", "Animes"},
        parameters = {
            @Parameter(name = "animeId", description = "ID do anime a ser adicionado", required = true, example = "1")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Anime adicionado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário ou anime não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
        })
    public ResponseEntity<Object> adicionarAnimeCompleto(@PathVariable Long animeId, HttpSession session) {
        try {
            Usuario usuario = (Usuario) session.getAttribute("user");

            gerenciador.adicionarAnimeCompleto(usuario, animeId);

            return ResponseEntity.ok("Anime adicionado à lista 'completo' com sucesso!");
        } catch (UsuarioInexistenteException | AnimeInexistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (AutorizacaoNegadaException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    // Adicionar anime à lista "quero assistir"
    @PostMapping("/quero-assistir/{animeId}")
    @Operation(
        summary = "Adicionar anime à lista 'quero assistir'",
        description = "Adiciona um anime à lista de animes que o usuário deseja assistir.",
        tags = {"Usuario", "Animes"},
        parameters = {
            @Parameter(name = "animeId", description = "ID do anime a ser adicionado", required = true, example = "1")
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Anime adicionado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário ou anime não encontrado"),
            @ApiResponse(responseCode = "403", description = "Acesso negado")
        })
    public ResponseEntity<Object> adicionarAnimeQueroAssistir(@PathVariable Long animeId, HttpSession session) {
        try {
            Usuario usuario = (Usuario) session.getAttribute("user");

            gerenciador.adicionarAnimeQueroAssistir(usuario, animeId);

            return ResponseEntity.ok("Anime adicionado à lista 'quero assistir' com sucesso!");
        } catch (UsuarioInexistenteException | AnimeInexistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (AutorizacaoNegadaException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    /*****  METODOS PUT *****/

    //update usuário existente
    @PutMapping("atualizar/{id}")
    @Operation(
        summary = "Atualizar usuário",
        description = "Atualiza as informações de um usuário existente com base no ID fornecido.",
        tags = {"Usuario"},
        parameters = {
            @Parameter(name = "id", description = "ID do usuário a ser atualizado", required = true, example = "1")
        },
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Objeto JSON contendo os novos dados do usuário",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = UsuarioDTO.class)
            )
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "409", description = "Conflito de dados, e-mail já cadastrado")
        }
    )
    public ResponseEntity<Object> updateUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = UsuarioMapper.convertToEntity(usuarioDTO);
            usuario.setId(id); 
            
            Usuario usuarioAtualizado = gerenciador.updateUsuario(usuario);
            
            UsuarioDTO usuarioAtualizadoDTO = UsuarioMapper.convertToDTO(usuarioAtualizado);

            UsuarioResponse response = new UsuarioResponse("Usuário atualizado com sucesso!", usuarioAtualizadoDTO);
            return ResponseEntity.ok(response);

        } catch (UsuarioInexistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (UsuarioDuplicadoException e) {
            // Se o e-mail já estiver em uso, retorna 409 (Conflito)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /*****  METODOS DELETE *****/


    //apagar usuario por id
    @DeleteMapping("deletar/{id}")
    @Operation(
        summary = "Deletar usuário",
        description = "Remove um usuário do sistema com base no ID fornecido.",
        tags = {"Usuario"},
        parameters = {
            @Parameter(name = "id", description = "ID do usuário a ser deletado", required = true, example = "1")
        },
        responses = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
        }
    )
    public ResponseEntity<Object> deleteUsuario(@PathVariable Long id) {
        try {
            gerenciador.deleteUsuarioById(id);
            // Faz uma lista filtrando apenas as avaliações desse user e dps apaga elas
            List<Avaliacao> avaliacao = gerenciador.findAllAvaliacao();
            List<AvaliacaoPeloIdDTO> result = avaliacao.stream()
                    .map(this::convertToComIdDTO)
                    .filter(AvaliacaoComIdDTO -> AvaliacaoComIdDTO.getUsuarioAvaliador().equals(id))
                    .toList();
            result.forEach(avaliacaoDTO -> {
                try {
                    gerenciador.deleteAvaliacaoById(avaliacaoDTO.getId());
                } catch (AvaliacaoInexistenteException e) {
                    throw new RuntimeException(e);
                }
            });

            return ResponseEntity.noContent().build(); 
        } catch (UsuarioInexistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    /*****  METODOS PRIVADOS *****/
    private AvaliacaoPeloIdDTO convertToComIdDTO(Avaliacao avaliacao) {
        return AvaliacaoMapper.convertToComIdDTO(avaliacao);
    }

}
