package br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.dto.AnimeDTO;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroAdm;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroAnime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroAvaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroUsuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;

@Service
public class GerenciadorAnimes {
    
    @Autowired
    private CadastroUsuario cadastroUsuario;
    @Autowired
    private CadastroAnime cadastroAnime;
    @Autowired
    private CadastroAvaliacao cadastroAvaliacao;
    @Autowired
    private CadastroAdm cadastroAdm;

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
    //buscar anime com avaliações
    public AnimeDTO buscarAnimeComAvaliacoes(Long id) throws AnimeInexistenteException {
        return cadastroAnime.buscarAnimeComAvaliacoes(id);
    }

    /**********IMPLEMENTAÇÃO DE CADASTRO AVALIAÇÃO ********/

    /**********IMPLEMENTAÇÃO DE CADASTRO ADM ********/

}
