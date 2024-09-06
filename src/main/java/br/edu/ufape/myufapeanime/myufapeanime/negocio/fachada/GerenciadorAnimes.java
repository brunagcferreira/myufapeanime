package br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada;

import java.util.List;

import java.util.Optional;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroAnime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.NomeDoAnimeVazioException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.NumeroDeEpisodiosInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroUsuario;

@Service
public class GerenciadorAnimes {

    @Autowired
    private CadastroUsuario cadastroUsuario;

    @Autowired
    private CadastroAnime cadastroAnime;

    /**********IMPLEMENTAÇÃO DE CADASTRO USUARIO ********/
    //salvar
    public Usuario saveUsuario(Usuario usuario) throws UsuarioDuplicadoException {
        return cadastroUsuario.save(usuario);
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
    public Optional<Usuario> findByIdUsuario(Long id) {
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
    public Anime saveAnime(Anime anime) throws NumeroDeEpisodiosInvalidoException, AnimeDuplicadoException, NomeDoAnimeVazioException {
        return cadastroAnime.create(anime);
    }
    //atualizar
    public Anime updateAnime(Anime anime) throws AnimeInexistenteException {
        return cadastroAnime.update(anime);
    }
    //apagar por id
    public void deleteAnimeById(Long id) throws AnimeInexistenteException {
        cadastroAnime.deleteById(id);
    }
    //apagar
    public void deleteAnime(Anime anime) throws AnimeInexistenteException {
        cadastroAnime.delete(anime);
    }
    //listar todos
    public List<Anime> findAllAnimes(){
        return cadastroAnime.findAll();
    }
    //buscar por nome(pode retornar null)
    public List<Anime> findByNomeAnime(String nome) throws AnimeInexistenteException, NomeDoAnimeVazioException {
        return cadastroAnime.findByNome(nome);
    }

}
