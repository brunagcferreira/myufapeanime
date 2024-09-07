package br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada;

import java.util.List;

import java.util.Optional;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroAnime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
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
    public Anime cadastrarAnime(Anime anime) throws AnimeDuplicadoException, NumeroDeEpisodiosInvalidoException {
        return cadastroAnime.cadastrarAnime(anime);
    }

    //listar todos
    public List<Anime> listarAnimes() {
        return cadastroAnime.listarAnimes();
    }

    //buscar por id
    public Anime findByIdAnime(Long id) throws AnimeInexistenteException {
        return cadastroAnime.findByIdAnime(id);
    }

    //atualizar
    public Anime atualizarAnime(Long id, Anime animeAtualizado) throws AnimeInexistenteException {
        return cadastroAnime.atualizarAnime(id, animeAtualizado);
    }

    //deletar
    public void deletarAnime(Long id) throws AnimeInexistenteException {
        cadastroAnime.deletarAnime(id);
    }

    public List<Anime> findByNomeAnime(String nome){
        return cadastroAnime.findByNomeAnime(nome);
    }
}
