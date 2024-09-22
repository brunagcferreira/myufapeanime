package br.edu.ufape.myufapeanime.myufapeanime.TestesDeUnidade;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroAvaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoDuplicadaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoNotaInvalidaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioUsuarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class CadastroAvaliacaoTest {

    @Autowired
    @Qualifier("interfaceRepositorioAnimes")
    private InterfaceRepositorioAnimes repositorioAnimes;

    @Autowired
    @Qualifier("interfaceRepositorioUsuarios")
    private InterfaceRepositorioUsuarios repositorioUsuarios;


    private Anime novoAnime;
    private Usuario NovoUsuario;

    @Autowired
    private CadastroAvaliacao cadastroAvaliacao;

    @BeforeEach
    public void setUp() {

        // Cria um novo anime e atribui à variável de instância
        novoAnime = new Anime();
        novoAnime.setNome("JoJo");
        novoAnime.setNumEpisodios(31);
        novoAnime.setGenero("Aventuras Bizarras");

        // Cria um novo usuário e atribui à variável de instância
        NovoUsuario = new Usuario();
        NovoUsuario.setNome("Joseph Joestar");
        NovoUsuario.setSenha("HolyJoestar");
        NovoUsuario.setEmail("JosephJoestar@gmail.com");

        // Salva o usuário e o anime no banco (se necessário)
        repositorioAnimes.save(novoAnime);
        repositorioUsuarios.save(NovoUsuario);
    }

    @Test
    public void testSalvarAvaliacaoComSucesso()
            throws AvaliacaoNotaInvalidaException, AvaliacaoDuplicadaException, AnimeInexistenteException, UsuarioInexistenteException {

        Avaliacao novaAvaliacao = new Avaliacao();
        novaAvaliacao.setNota(4.0);
        novaAvaliacao.setComentario("Anime Fantástico");
        novaAvaliacao.setAnime(novoAnime); // Usa a variável de instância inicializada no setUp()
        novaAvaliacao.setUsuarioAvaliador(NovoUsuario); // Usa a variável de instância inicializada no setUp()

        cadastroAvaliacao.create(novaAvaliacao);

        assertEquals(4.0, novaAvaliacao.getNota());
        assertEquals("Anime Fantástico", novaAvaliacao.getComentario());
        assertEquals(novoAnime, novaAvaliacao.getAnime());
        assertEquals(NovoUsuario,novaAvaliacao.getUsuarioAvaliador());

    }

    @Test
    public void testSalvarAvaliacaoDuplicada()
            throws AvaliacaoNotaInvalidaException, AvaliacaoDuplicadaException, AnimeInexistenteException, UsuarioInexistenteException {

        Avaliacao novaAvaliacao = new Avaliacao();
        novaAvaliacao.setNota(2.0);
        novaAvaliacao.setComentario("Anime not poggers");
        novaAvaliacao.setAnime(novoAnime);
        novaAvaliacao.setUsuarioAvaliador(NovoUsuario);

        cadastroAvaliacao.create(novaAvaliacao);

        Avaliacao newAvaliacao = new Avaliacao();
        newAvaliacao.setNota(4.5);
        newAvaliacao.setComentario("Melhor anime da temporada");
        newAvaliacao.setAnime(novoAnime);
        newAvaliacao.setUsuarioAvaliador(NovoUsuario);

        // Verifica se a exceção é lançada
        assertThrows(AvaliacaoDuplicadaException.class, () -> {
            cadastroAvaliacao.create(newAvaliacao);
        });
    }

    @Test
    public void testSalvarAvaliacaoComNotaInvalida() {

        Avaliacao novaAvaliacao = new Avaliacao();
        novaAvaliacao.setNota(-1.0);
        novaAvaliacao.setComentario("Anime bom de mais");
        novaAvaliacao.setAnime(novoAnime); // Usa a variável de instância inicializada no setUp()
        novaAvaliacao.setUsuarioAvaliador(NovoUsuario); // Usa a variável de instância inicializada no setUp()


        // Verifica se a exceção é lançada
        assertThrows(AvaliacaoNotaInvalidaException.class, () -> {
            cadastroAvaliacao.create(novaAvaliacao);
        });
    }

    @Test
    public void testSalvarAvaliacaoComAnimeInexistente() {
        Avaliacao novaAvaliacao = new Avaliacao();
        novaAvaliacao.setNota(3.0);
        novaAvaliacao.setComentario("Anime interessante");

        // Cria um anime que não foi salvo no banco (ou pode remover um já salvo)
        Anime animeInexistente = new Anime();
        animeInexistente.setNome("Anime Fantasma");
        animeInexistente.setId(9999L);
        novaAvaliacao.setAnime(animeInexistente); // Define o anime inexistente
        novaAvaliacao.setUsuarioAvaliador(NovoUsuario); // Usa o usuário salvo no setup

        // Verifica se a exceção AnimeInexistenteException é lançada
        assertThrows(AnimeInexistenteException.class, () -> {
            cadastroAvaliacao.create(novaAvaliacao);
        });
    }

    @Test
    public void testSalvarAvaliacaoComUsuarioInexistente() {
        Avaliacao novaAvaliacao = new Avaliacao();
        novaAvaliacao.setNota(4.0);
        novaAvaliacao.setComentario("Gostei bastante");

        // Cria um usuário com um ID inexistente
        Usuario usuarioInexistente = new Usuario();
        usuarioInexistente.setId(9999L); // Define um ID arbitrário que você sabe que não existe
        usuarioInexistente.setNome("Usuário Fantasma");
        usuarioInexistente.setEmail("fantasma@anime.com");

        novaAvaliacao.setUsuarioAvaliador(usuarioInexistente); // Define o usuário inexistente
        novaAvaliacao.setAnime(novoAnime); // Usa o anime salvo no setup

        // Verifica se a exceção UsuarioInexistenteException é lançada
        assertThrows(UsuarioInexistenteException.class, () -> {
            cadastroAvaliacao.create(novaAvaliacao);
        });
    }


}
