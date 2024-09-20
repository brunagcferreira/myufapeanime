package br.edu.ufape.myufapeanime.myufapeanime.controllers;

import br.edu.ufape.myufapeanime.myufapeanime.dto.anime.AnimeDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.avaliacao.AvaliacaoDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.avaliacao.AvaliacaoPeloIdDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.avaliacao.AvaliacaoUpdateDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.mappers.AvaliacaoMapper;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoDuplicadaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoNotaInvalidaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada.GerenciadorAnimes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/avaliacao")
@Tag(name = "Comentários", description = "API de gerenciamento de avaliações")
public class AvaliacaoController {

    @Autowired
    private GerenciadorAnimes gerenciador;

    /*****  METODO POST Avaliacao  *****/
    @PostMapping("/cadastrar")
    @Operation(
            summary = "Cadastrar uma nova avaliação",
            description = "Cadastra uma nova avaliação no sistema",
            tags = {"Comentários"},
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto JSON da avaliação a ser cadastrada",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AvaliacaoPeloIdDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo de Avaliação",
                                            description = "Exemplo de um objeto de avaliação para cadastro",
                                            value = "{\n" +
                                                    "  \"nota\": 5,\n" +
                                                    "  \"comentario\": \"Sensacional!\",\n" +
                                                    "  \"usuarioAvaliador\": 2,\n" +
                                                    "  \"animeAvaliado\": 1" +
                                                    "}"
                                    )
                            }
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Avaliação cadastrada com sucesso"),
                    @ApiResponse(responseCode = "409", description = "Usuario já tem uma avaliação para esse Anime! ou Anime com id {id} não existe."),
                    @ApiResponse(responseCode = "400", description = "Nota de Cadastro/Update Invalida! ou Usuário com id {id} não existe."),

            }
    )
    public ResponseEntity<Object> cadastrarAvaliacao(@RequestBody AvaliacaoPeloIdDTO avaliacaoPeloIdDTO) {
        try {
            Avaliacao avaliacao = convertToEntity(avaliacaoPeloIdDTO);
            Avaliacao novaAvaliacao = gerenciador.saveAvaliacao(avaliacao);
            AvaliacaoPeloIdDTO novaAvaliacaoDTO = convertToComIdDTO(novaAvaliacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaAvaliacaoDTO);
        } catch (AvaliacaoNotaInvalidaException | UsuarioInexistenteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (AnimeInexistenteException | AvaliacaoDuplicadaException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    /*****  METODOS PUT *****/
    //update usuário existente
    @PutMapping("atualizar/{id}")
    @Operation(
            summary = "Atualizar uma avaliação",
            description = "Atualiza os detalhes de uma avaliação existente, Pondendo ser atualizado a nota e seu comentário" +
                    "aceitando contendo apenas um desses dados para ser atualizado (favor checar os exemplos)",
            tags = {"Comentários"},
            parameters = {
                    @Parameter(name = "id", description = "ID da avaliação a ser atualizada", required = true, example = "1")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto JSON do anime com os novos dados",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AnimeDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo de uma Avaliação completa",
                                            description = "Exemplo de um objeto de avaliação para atualização",
                                            value = "{\n" +
                                                    "  \"nota\": 3,\n" +
                                                    "  \"comentario\": \"Épico\"\n" +
                                                    "}"
                                    ),
                                    @ExampleObject(
                                            name = "Exemplo de nota de Avaliação",
                                            description = "Exemplo de um objeto de avaliação para atualização",
                                            value = "{\n" +
                                                    "  \"nota\": 3" +
                                                    "}"
                                    ),
                                    @ExampleObject(
                                            name = "Exemplo de comentário de Avaliação",
                                            description = "Exemplo de um objeto de avaliação para atualização",
                                            value = "{\n" +
                                                    "  \"comentario\": \"Épico\"" +
                                                    "}"
                                    )
                            }
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Avaliação atualizada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AvaliacaoUpdateDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "400", description = "Avaliacao com o id {id} inexistente! ou Nota de Cadastro/Update Invalida!",
                            content = @Content(
                                    mediaType = "text/plain",
                                    schema = @Schema(implementation = String.class)
                            )
                    )
            }
    )
    public ResponseEntity<Object> updateAvaliacao(@PathVariable Long id, @RequestBody AvaliacaoUpdateDTO avaliacaoDTO)
            throws AnimeInexistenteException {
        try {
            Avaliacao avaliacao = convertToEntityUpdate(avaliacaoDTO);
            avaliacao.setId(id);
            Avaliacao avaliacaoAtualizado = gerenciador.updateAvaliacao(avaliacao);
            AvaliacaoPeloIdDTO avaliacaoAtualizadoDTO = convertToComIdDTO(avaliacaoAtualizado);
            return ResponseEntity.ok(avaliacaoAtualizadoDTO);
        } catch (AvaliacaoNotaInvalidaException | AvaliacaoInexistenteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }


    }

    /*****  METODO DELETE Avaliacao *****/
    //apagar usuario por id
    @DeleteMapping("deletar/{id}")
    @Operation(
            summary = "Deletar uma avaliação",
            description = "Remove uma avaliação existente do sistema",
            tags = {"Comentários"},
            parameters = {
                    @Parameter(name = "id", description = "ID da avaliação a ser deletada", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "204", description = "avaliação deletada com sucesso"),
                    @ApiResponse(responseCode = "404", description = "Avaliacao com o id {id} inexistente!",
                            content = @Content(
                                    mediaType = "text/plain",
                                    schema = @Schema(implementation = String.class)
                            )
                    )
            }
    )
    public ResponseEntity<Object> deleteAvaliacao(@PathVariable Long id) {
        try {
            gerenciador.deleteAvaliacao(id);
            return ResponseEntity.noContent().build();
        } catch (AvaliacaoInexistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    /*****  METODO GET Avaliacao *****/

    //listar todas as Avaliações
    @GetMapping("/list")
    @Operation(
            summary = "Listar todas as avaliações",
            description = "Retorna uma lista com todas as avalialçoes cadastradas no sistema",
            tags = {"Comentários"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de avaliações retornada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AvaliacaoDTO.class))
                            )
                    )
            }
    )
    public ResponseEntity<List<AvaliacaoDTO>> findAll() {
        List<Avaliacao> avaliacao = gerenciador.findAllAvaliacao();
        List<AvaliacaoDTO> result = avaliacao.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    //lista uma Avaliação
    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar uma avaliação por ID",
            description = "Retorna os detalhes de uma avaliação específica pelo seu ID",
            tags = {"Comentários"},
            parameters = {
                    @Parameter(name = "id", description = "ID da avaliação a ser buscada", required = true, example = "1")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "avaliação encontrada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AvaliacaoDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Avaliacao com o id {id} inexistente!",
                            content = @Content(
                                    mediaType = "text/plain",
                                    schema = @Schema(implementation = String.class)
                            )
                    )
            }
    )
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        try {
            Avaliacao avaliacao = gerenciador.findByIdAvaliacao(id);
            AvaliacaoDTO avaliacaoDTO = convertToDTO(avaliacao);
            return ResponseEntity.ok(avaliacaoDTO);
        } catch (AvaliacaoInexistenteException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage()); // HTTP 404
        }
    }

    //listar todas as Avaliações de um anime específico
    @GetMapping("/list/anime/{id}")
    @Operation(
            summary = "Listar as avaliações de um anime",
            description = "Retorna uma lista de avaliações de um anime específico",
            tags = {"Comentários"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de avaliações retornada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AvaliacaoPeloIdDTO.class))
                            )
                    )
            }
    )
    public ResponseEntity<List<AvaliacaoPeloIdDTO>> findAll(@PathVariable Long id) {
        List<Avaliacao> avaliacao = gerenciador.findAllAvaliacao();
        List<AvaliacaoPeloIdDTO> result = avaliacao.stream()
                .map(this::convertToComIdDTO)
                .filter(AvaliacaoPeloIdDTO -> AvaliacaoPeloIdDTO.getAnimeAvaliado().equals(id))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    //listar todas as Avaliações de um usuário específico
    @GetMapping("/list/usuario/{id}")
    @Operation(
            summary = "Listar as avaliações de um usuário",
            description = "Retorna uma lista de avaliações de um usuário específico",
            tags = {"Comentários"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de avaliações retornada com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = AvaliacaoPeloIdDTO.class))
                            )
                    )
            }
    )
    public ResponseEntity<List<AvaliacaoPeloIdDTO>> findAllByUser(@PathVariable Long id) {
        List<Avaliacao> avaliacao = gerenciador.findAllAvaliacao();
        List<AvaliacaoPeloIdDTO> result = avaliacao.stream()
                .map(this::convertToComIdDTO)
                .filter(AvaliacaoPeloIdDTO -> AvaliacaoPeloIdDTO.getUsuarioAvaliador().equals(id))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


    private AvaliacaoPeloIdDTO convertToComIdDTO(Avaliacao avaliacao) {
        return AvaliacaoMapper.convertToComIdDTO(avaliacao);
    }

    private AvaliacaoDTO convertToDTO(Avaliacao avaliacao) {
        return AvaliacaoMapper.convertToDTO(avaliacao);
    }

    private Avaliacao convertToEntity(AvaliacaoPeloIdDTO avaliacaoDTO) throws AnimeInexistenteException, UsuarioInexistenteException {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setComentario(avaliacaoDTO.getComentario());
        avaliacao.setUsuarioAvaliador(gerenciador.findByIdUsuario(avaliacaoDTO.getUsuarioAvaliador()));
        avaliacao.setAnime(gerenciador.findByIdAnime(avaliacaoDTO.getAnimeAvaliado()));

        return avaliacao;
    }

    private Avaliacao convertToEntityUpdate(AvaliacaoUpdateDTO avaliacaoDTO) throws AnimeInexistenteException {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setComentario(avaliacaoDTO.getComentario());
        avaliacao.setId(avaliacaoDTO.getId());

        return avaliacao;
    }
}
