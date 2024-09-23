package br.edu.ufape.myufapeanime.myufapeanime.TestesDeIntegracao;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Adm;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.NumeroDeEpisodiosInvalidoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAutenticacaoExceptions.AutorizacaoNegadaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioSenhaInvalidaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada.GerenciadorAnimes;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioUsuarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IntegracaoAuthTest {
    @Autowired
    private GerenciadorAnimes gerenciadorAnimes;

    @Autowired
    @Qualifier("interfaceRepositorioUsuarios")
    private InterfaceRepositorioUsuarios repositorioUsuarios;
    Adm adm = null;
    Anime anime = null;
    Usuario usuario = null;

    @BeforeEach
    public void setUp() {
        adm = new Adm();
        adm.setNome("Ademiro");
        adm.setEmail("ademiro@gmail.com");
        adm.setSenha("123456");
        anime = new Anime();
        anime.setNome("Anime");
        anime.setGenero("Neutre");
        anime.setNumEpisodios(12);
        usuario = new Usuario();
        usuario.setNome("Usuario");
        usuario.setEmail("usuario@gmail.com");
        usuario.setSenha("122333");
    }

    @Test
    public void cadastrarAdmSenhaInvalida() {
        adm.setSenha("12345");
        //assertThrows(UsuarioSenhaInvalidaException.class, () -> gerenciadorAnimes.createUsuario(adm));
    }

    @Test
    public void cadastrarAnimeLogado() throws NumeroDeEpisodiosInvalidoException, AutorizacaoNegadaException, AnimeDuplicadoException {

        Anime novoAnime = gerenciadorAnimes.createAnime(anime, adm);
        assertEquals(anime , novoAnime);

    }

    @Test
    public void cadastrarAnimeDeslogado() {
        assertThrows(AutorizacaoNegadaException.class, () -> gerenciadorAnimes.createAnime(anime, null));

    }

    @Test
    public void cadastrarAnimeLogadoComUsuario() {
        assertThrows(AutorizacaoNegadaException.class, () -> gerenciadorAnimes.createAnime(anime, usuario));
    }

}
