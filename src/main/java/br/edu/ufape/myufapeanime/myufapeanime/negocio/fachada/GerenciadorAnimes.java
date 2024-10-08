package br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada;

import br.edu.ufape.myufapeanime.myufapeanime.dto.avaliacao.AvaliacaoPeloIdDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.mappers.AvaliacaoMapper;
import br.edu.ufape.myufapeanime.myufapeanime.dto.mappers.UsuarioMapper;
import br.edu.ufape.myufapeanime.myufapeanime.dto.model.TipoLista;
import br.edu.ufape.myufapeanime.myufapeanime.dto.usuario.UsuarioComSenhaDTO;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Adm;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroAnime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroAvaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroUsuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.NumeroDeEpisodiosInvalidoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAutenticacaoExceptions.AutorizacaoNegadaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoDuplicadaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoNotaInvalidaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioSenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerenciadorAnimes {

    @Autowired
    private CadastroUsuario cadastroUsuario;

    @Autowired
    private CadastroAnime cadastroAnime;

    @Autowired
    private CadastroAvaliacao cadastroAvaliacao;


    /**********IMPLEMENTAÇÃO DE CADASTRO USUARIO ********/
    //salvar
    public Usuario createUsuario(UsuarioComSenhaDTO usuarioComSenhaDTO) throws UsuarioDuplicadoException, UsuarioSenhaInvalidaException {

        if (usuarioComSenhaDTO.getIsAdm()) {
            Adm usuario = UsuarioMapper.convertToAdm(usuarioComSenhaDTO);
            return cadastroUsuario.create(usuario);
        }
        Usuario usuario = UsuarioMapper.convertToEntityPassword(usuarioComSenhaDTO);

        return cadastroUsuario.create(usuario);
    }

    //atualizar
    public void updateUsuario(Usuario usuario) throws UsuarioInexistenteException,
            UsuarioDuplicadoException {
        cadastroUsuario.update(usuario);
    }

    public Usuario updateUsuarioLogado(UsuarioComSenhaDTO usuarioComSenhaDTO, Usuario usuarioLogado) throws AutorizacaoNegadaException,
            UsuarioDuplicadoException, UsuarioInexistenteException {
        Usuario usuario;

        if (usuarioLogado instanceof Adm) {
            usuario = UsuarioMapper.convertToAdm(usuarioComSenhaDTO);
        } else {
            usuario = UsuarioMapper.convertToEntityPassword(usuarioComSenhaDTO);
        }
        checarUsuarioLogado(usuarioLogado);
        usuario.setId(usuarioLogado.getId());

        return cadastroUsuario.update(usuario);
    }

    //apagar usuário logado
    public void deletarUsuarioLogado(Usuario usuario) throws UsuarioInexistenteException, AutorizacaoNegadaException {
        checarUsuarioLogado(usuario);
        // Faz uma lista filtrando apenas as avaliações desse user e dps apaga elas


        List<Avaliacao> avaliacao = findAllAvaliacao();
        List<AvaliacaoPeloIdDTO> result = avaliacao.stream()
                .map(this::convertToComIdDTO)
                .filter(AvaliacaoComIdDTO -> AvaliacaoComIdDTO.getUsuarioAvaliador().equals(usuario.getId()))
                .toList();
        result.forEach(avaliacaoDTO -> {
            try {
                deleteAvaliacaoById(avaliacaoDTO.getId());
            } catch (AvaliacaoInexistenteException e) {
                System.err.println("Avaliacao com ID " + avaliacaoDTO.getId() + " não existe. Ignorando...");
            }

        });

        cadastroUsuario.delete(usuario);
    }

    private AvaliacaoPeloIdDTO convertToComIdDTO(Avaliacao avaliacao) {
        return AvaliacaoMapper.convertToComIdDTO(avaliacao);
    }
    //listar todos
    public List<Usuario> findAllUsuarios() {
        return cadastroUsuario.findAll();
    }

    //buscar por id
    public Usuario findUsuarioById(Long id) throws UsuarioInexistenteException {
        return cadastroUsuario.findById(id);
    }

    //buscar por nome
    public List<Usuario> findByNomeUsuario(String nome) {
        return cadastroUsuario.findByNome(nome);
    }

    //lista assistindo
    public List<Anime> getAssistindoUsuario(Usuario usuario) throws UsuarioInexistenteException, AutorizacaoNegadaException {
        checarUsuarioLogado(usuario);
        return cadastroUsuario.getAssistindo(usuario.getId());
    }

    //lista completos
    public List<Anime> getCompletosUsuario(Usuario usuario) throws UsuarioInexistenteException, AutorizacaoNegadaException {
        checarUsuarioLogado(usuario);
        return cadastroUsuario.getCompleto(usuario.getId());
    }

    //lista quero assistir
    public List<Anime> getQueroAssistirUsuario(Usuario usuario) throws UsuarioInexistenteException, AutorizacaoNegadaException {
        checarUsuarioLogado(usuario);
        return cadastroUsuario.getQueroAssistir(usuario.getId());
    }

    //add anime da lista do usuario
    public void adicionarAnimeLista(Usuario usuario, Long animeId, TipoLista tipoLista) 
    throws AnimeInexistenteException, AutorizacaoNegadaException {
        checarUsuarioLogado(usuario);
        cadastroUsuario.adicionarAnimeLista(usuario, animeId, tipoLista);
    }
    
    //remover anime da lista do usuario
    public void removerAnimeLista(Usuario usuario, Long animeId, TipoLista tipoLista) 
    throws AnimeInexistenteException, AutorizacaoNegadaException {
        checarUsuarioLogado(usuario);
        cadastroUsuario.removerAnimeLista(usuario, animeId, tipoLista);
    }

    /**********IMPLEMENTAÇÃO DE CADASTRO ANIME ********/
    //salvar
    public Anime createAnime(Anime anime, Usuario usuario) throws AnimeDuplicadoException, NumeroDeEpisodiosInvalidoException, AutorizacaoNegadaException {
        checarAdm(usuario);
        return cadastroAnime.create(anime);
    }

    //listar todos
    public List<Anime> findAllAnime() {
        return cadastroAnime.findAll();
    }

    //buscar por id
    public Anime findAnimeById(Long id) throws AnimeInexistenteException {
        return cadastroAnime.findById(id);
    }

    //atualizar
    //tirar essa id depois
    public Anime atualizarAnime(Long id, Anime animeAtualizado, Usuario usuario) throws AnimeInexistenteException, AnimeDuplicadoException, AutorizacaoNegadaException, NumeroDeEpisodiosInvalidoException {
        checarAdm(usuario);
        return cadastroAnime.update(animeAtualizado);
    }

    //deletar
    public void deletarAnime(Long id, Usuario usuario) throws AnimeInexistenteException, AutorizacaoNegadaException {
        checarAdm(usuario);
        cadastroAnime.deleteById(id);
    }

    public List<Anime> findAnimeByNome(String nome) {
        return cadastroAnime.findByNome(nome);
    }

    /**********IMPLEMENTAÇÃO DE CADASTRO Avalicao ********/

    // Salvar Avaliacao
    public Avaliacao createAvaliacao(Avaliacao avaliacao, Usuario usuario)
            throws AvaliacaoNotaInvalidaException, UsuarioInexistenteException, AnimeInexistenteException, AvaliacaoDuplicadaException, AutorizacaoNegadaException {
        // colocar DTO
        checarUsuarioLogado(usuario);
        avaliacao.setUsuarioAvaliador(usuario);
        return cadastroAvaliacao.create(avaliacao);
    }

    // Atualizar Avaliacao
    //ve se assim funciona
    public Avaliacao updateAvaliacao(Avaliacao novaAvaliacao)
            throws AvaliacaoNotaInvalidaException, AvaliacaoInexistenteException {
        return cadastroAvaliacao.update(novaAvaliacao);
    }

    // Apagar avaliacao por Id
    public void deleteAvaliacaoById(Long id) throws AvaliacaoInexistenteException {
        cadastroAvaliacao.deleteById(id);
    }

    //buscar por id
    public Avaliacao findAvaliacaoById(Long id) throws AvaliacaoInexistenteException {
        return cadastroAvaliacao.findById(id);
    }

    // listar todas as Avaliações
    public List<Avaliacao> findAllAvaliacao() {
        return cadastroAvaliacao.findAll();
    }


    public Usuario login(String email, String senha) throws UsuarioInexistenteException, UsuarioSenhaInvalidaException {
        Usuario usuario = cadastroUsuario.findByEmail(email);

        if (!usuario.getSenha().equals(senha)) {
            throw new UsuarioSenhaInvalidaException();
        }
        return usuario;
    }


    //checar se é adm
    private void checarAdm(Usuario usuario) throws AutorizacaoNegadaException {
        if(!(usuario instanceof Adm)){
            throw new AutorizacaoNegadaException("Somente administradores podem atualizar animes");
        }
    }
    //vefifica usuario logado no sistema
    private void checarUsuarioLogado(Usuario usuario) throws AutorizacaoNegadaException {
        if(usuario == null){
            throw new AutorizacaoNegadaException("Faça login para executar essa ação");
        }
    }
}
