package br.edu.ufape.myufapeanime.myufapeanime.TestesDeIntegracao;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.NumeroDeEpisodiosInvalidoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAutenticacaoExceptions.AutorizacaoNegadaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada.GerenciadorAnimes;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class IntegracaoAnimeTest {
    @Autowired
    private GerenciadorAnimes gerenciadorAnimes;

    @Autowired
    @Qualifier("interfaceRepositorioAnimes")
    private InterfaceRepositorioAnimes repositorioAnimes;

    @Test
    public void testCriarEBuscarAnimePorId() throws NumeroDeEpisodiosInvalidoException, AnimeDuplicadoException, AnimeInexistenteException, AutorizacaoNegadaException {
        // Cria um novo anime
        Anime novoAnime = new Anime();
        novoAnime.setNome("Pokemon");
        novoAnime.setNumEpisodios(23);
        novoAnime.setGenero("Violencia");

        // Salva o anime pela fachada
        Anime animeSalvo = gerenciadorAnimes.createAnime(novoAnime, null);

        // Busca o anime salvo pelo ID e verifica os dados
        Anime animeEncontrado = gerenciadorAnimes.findAnimeById(animeSalvo.getId());

        assertEquals(animeSalvo.getNome(), animeEncontrado.getNome());
        assertEquals(animeSalvo.getNumEpisodios(), animeEncontrado.getNumEpisodios());
        assertEquals(animeSalvo.getGenero(), animeEncontrado.getGenero());
    }
}
