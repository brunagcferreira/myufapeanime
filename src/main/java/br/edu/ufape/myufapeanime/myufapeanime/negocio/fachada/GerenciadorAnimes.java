package br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroAnime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroAvaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroUsuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.NumeroDeEpisodiosInvalidoException;
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
    public Usuario saveUsuario(Usuario usuario) throws UsuarioDuplicadoException, UsuarioSenhaInvalidaException {
        return cadastroUsuario.create(usuario);
    }
    //atualizar
    public Usuario updateUsuario(Usuario usuario) throws UsuarioInexistenteException {
        return cadastroUsuario.update(usuario);
    }
    //apagar por id
    public void deleteUsuarioById(Long id) throws UsuarioInexistenteException {
        cadastroUsuario.deleteById(id);
    }

    //apagar
    public void deleteUsuario(Usuario usuario) throws UsuarioInexistenteException {
        cadastroUsuario.delete(usuario);
    }
    //listar todos
    public List<Usuario> findAllUsuarios(){
        return cadastroUsuario.findAll();
    }

    //buscar por id(pode retornar null)
    //nao retorna mais null, retorna exception
    public Usuario findByIdUsuario(Long id) throws UsuarioInexistenteException {
        return cadastroUsuario.findById(id);
    }

    //buscar por nome(pode retornar null)
    public List<Usuario> findByNomeUsuario(String nome) {
        return cadastroUsuario.findByNome(nome);
    }

    //lista assistindo
    public List<Anime> getAssistindoUsuario(Long usuarioId) throws UsuarioInexistenteException {
        return cadastroUsuario.getAssistindo(usuarioId);
    }
    //lista completos
    public List<Anime> getCompletosUsuario(Long usuarioId) throws UsuarioInexistenteException {
        return cadastroUsuario.getCompleto(usuarioId);
    }
    //lista quero assistir
    public List<Anime> getQueroAssistirUsuario(Long usuarioId) throws UsuarioInexistenteException {
        return cadastroUsuario.getQueroAssistir(usuarioId);
    }

    /**********IMPLEMENTAÇÃO DE CADASTRO ANIME ********/
    //salvar
    public Anime cadastrarAnime(Anime anime) throws AnimeDuplicadoException, NumeroDeEpisodiosInvalidoException {
        return cadastroAnime.create(anime);
    }

    //listar todos
    public List<Anime> listarAnimes() {
        return cadastroAnime.findAll();
    }

    //buscar por id
    public Anime findByIdAnime(Long id) throws AnimeInexistenteException {
        return cadastroAnime.findById(id);
    }

    //atualizar
    //tirar essa id depois
    public Anime atualizarAnime(Long id, Anime animeAtualizado) throws AnimeInexistenteException, AnimeDuplicadoException {
        return cadastroAnime.update(animeAtualizado);
    }

    //deletar
    public void deletarAnime(Long id) throws AnimeInexistenteException {
        cadastroAnime.deleteById(id);
    }

    public List<Anime> findByNomeAnime(String nome){
        return cadastroAnime.findByNomeAnime(nome);
    }

    /**********IMPLEMENTAÇÃO DE CADASTRO Avalicao ********/

    // Salvar Avaliacao
    public Avaliacao saveAvaliacao(Avaliacao avaliacao)
            throws AvaliacaoNotaInvalidaException, UsuarioInexistenteException, AnimeInexistenteException, AvaliacaoDuplicadaException {
        // colocar DTO
        return cadastroAvaliacao.create(avaliacao);
    }
    // Atualizar Avaliacao
    //ve se assim funciona
    public Avaliacao updateAvaliacao(Avaliacao novaAvaliacao)
            throws AvaliacaoNotaInvalidaException, AvaliacaoInexistenteException {
        return cadastroAvaliacao.update(novaAvaliacao);
    }
    // Apagar avaliacao por Id
    public void deleteAvaliacao(Long id) throws AvaliacaoInexistenteException {
        cadastroAvaliacao.deleteById(id);
    }

    //buscar por id
    public Avaliacao findByIdAvaliacao(Long id) throws AvaliacaoInexistenteException {
        return cadastroAvaliacao.findById(id);
    }

    // listar todas as Avaliações
    public List<Avaliacao> findAllAvaliacao(){
        return cadastroAvaliacao.findAll();
    }


    public Usuario login(String email, String senha) throws UsuarioInexistenteException, UsuarioSenhaInvalidaException {
        Usuario usuario = cadastroUsuario.findByEmail(email);

        if(!usuario.getSenha().equals(senha)){
            throw new UsuarioSenhaInvalidaException();
        }
        return usuario;
    }
}
