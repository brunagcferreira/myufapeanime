package br.edu.ufape.myufapeanime.myufapeanime.TestesDeIntegracao;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAutenticacaoExceptions.AutorizacaoNegadaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoDuplicadaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoNotaInvalidaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada.GerenciadorAnimes;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAvaliacoes;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioUsuarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class IntegracaoAvaliacaoTest {

    @Autowired
    private GerenciadorAnimes gerenciadorAnimes;

    @Autowired
    @Qualifier("interfaceRepositorioUsuarios")
    private InterfaceRepositorioUsuarios repositorioUsuarios;

    @Autowired
    @Qualifier("interfaceRepositorioAnimes")
    private InterfaceRepositorioAnimes repositorioAnimes;

    @Autowired
    @Qualifier("interfaceRepositorioAvaliacoes")
    private InterfaceRepositorioAvaliacoes repositorioAvaliacoes;

    private Anime novoAnime;
    private Usuario NovoUsuario;

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
        repositorioUsuarios.save(NovoUsuario);
        repositorioAnimes.save(novoAnime);
    }

    @Test
    public void testCriarEBuscarAvaliacaoPorId()
            throws AvaliacaoNotaInvalidaException, AnimeInexistenteException, AvaliacaoDuplicadaException, UsuarioInexistenteException, AvaliacaoInexistenteException, AutorizacaoNegadaException {

        // Cria uma nova avaliação e associa ao anime e usuário
        Avaliacao novaAvaliacao = new Avaliacao();
        novaAvaliacao.setNota(4.0);
        novaAvaliacao.setComentario("Anime Fantástico");
        novaAvaliacao.setAnime(novoAnime); // Usa a variável de instância inicializada no setUp()
        novaAvaliacao.setUsuarioAvaliador(NovoUsuario); // Usa a variável de instância inicializada no setUp()

        // Salva a avaliação pela fachada
        Avaliacao avaliacaoSalva = gerenciadorAnimes.createAvaliacao(novaAvaliacao, NovoUsuario);

        // Busca a avaliação salva pelo ID e verifica os dados
        Avaliacao avaliacaoEncontrada = gerenciadorAnimes.findAvaliacaoById(avaliacaoSalva.getId());

        assertEquals(avaliacaoSalva.getNota(), avaliacaoEncontrada.getNota());
        assertEquals(avaliacaoSalva.getComentario(), avaliacaoEncontrada.getComentario());
        assertEquals(avaliacaoSalva.getAnime().getId(), avaliacaoEncontrada.getAnime().getId());
        assertEquals(avaliacaoSalva.getUsuarioAvaliador().getId(), avaliacaoEncontrada.getUsuarioAvaliador().getId());
    }

}
