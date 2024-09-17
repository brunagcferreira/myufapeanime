package br.edu.ufape.myufapeanime.myufapeanime.TestesDeUnidade;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroAnime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.NumeroDeEpisodiosInvalidoException;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class CadastroAnimeTest {

    @Autowired
    private CadastroAnime cadastroAnime;

    @Autowired
    @Qualifier("interfaceRepositorioAnimes")
    private InterfaceRepositorioAnimes repositorioAnimes;

    @Test
    public void testSalvarAnimeComSucesso() throws NumeroDeEpisodiosInvalidoException, AnimeDuplicadoException {
        // Cria um anime
        Anime anime = new Anime();
        anime.setNome("Fullmetal Alchemist: Brotherhood");
        anime.setGenero("Fantasia");
        anime.setNumEpisodios(64);

        // Salva o anime
        cadastroAnime.create(anime);

        // Verifica se o anime foi salvo no repositório e se o que foi encontrado tem os mesmos dados do anime salvo
        Anime animeSalvo = repositorioAnimes.findByNome("Fullmetal Alchemist: Brotherhood");
        assertNotNull(animeSalvo);
        assertEquals("Fullmetal Alchemist: Brotherhood", animeSalvo.getNome());
        assertEquals("Fantasia", animeSalvo.getGenero());
        assertEquals(64, animeSalvo.getNumEpisodios());
    }


    @Test
    public void testSalvarAnimeComNumeroDeEpisodiosInvalido() {
        // Cria um anime com número de episódios inválido
        Anime anime = new Anime();
        anime.setNome("DragonBall");
        anime.setGenero("Luta");
        anime.setNumEpisodios(-100); // Número de episódios inválido

        // Verifica se a exceção é lançada
        assertThrows(NumeroDeEpisodiosInvalidoException.class, () -> {
            cadastroAnime.create(anime);
        });
    }

    @Test
    public void testSalvarAnimeDuplicado() throws NumeroDeEpisodiosInvalidoException, AnimeDuplicadoException {
        // Cria um anime
        Anime anime = new Anime();
        anime.setNome("Death Note");
        anime.setGenero("Mistério");
        anime.setNumEpisodios(37); // Número de episódios válido

        // Salva o anime
        cadastroAnime.create(anime);

        // Cria novamente o mesmo anime com dados um pouco diferentes, mas o identificador (nome) é o mesmo
        Anime animeDuplicado = new Anime();
        animeDuplicado.setNome("Death Note");
        animeDuplicado.setGenero("Investigação"); // genero diferente
        animeDuplicado.setNumEpisodios(1000); // numero de episodios também diferente
        assertThrows(AnimeDuplicadoException.class, () -> {
            cadastroAnime.create(animeDuplicado);
        });
    }

}
