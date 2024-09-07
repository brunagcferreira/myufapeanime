package br.edu.ufape.myufapeanime.myufapeanime.cadastro;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.DataInvalidaAnimeException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.NomeDoAnimeVazioException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.NumeroDeEpisodiosInvalidoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroAnime;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CadastroAnimeTest {

    @Mock
    private InterfaceRepositorioAnimes animeRepository;

    @InjectMocks
    private CadastroAnime cadastroAnime;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void salvarAnimeNormalmenteTest() throws AnimeDuplicadoException, NomeDoAnimeVazioException, NumeroDeEpisodiosInvalidoException, DataInvalidaAnimeException {
        Anime anime = new Anime();
        anime.setNome("Naruto");
        anime.setNumEpisodios(220);

        when(animeRepository.findByNome(any(String.class))).thenReturn(null);

        cadastroAnime.salvarAnime(anime);

        verify(animeRepository, times(1)).save(anime);
    }

    @Test
    void nomeVazioDadoInvalidoTest() {
        assertThrows(NomeDoAnimeVazioException.class, () -> {
            cadastroAnime.salvarAnime(null);
        });
    }

    @Test
    void numeroEpisodiosInvalidoTest() {
        Anime anime = new Anime();
        anime.setNome("Naruto");
        anime.setNumEpisodios(0);

        assertThrows(NumeroDeEpisodiosInvalidoException.class, () -> {
            cadastroAnime.salvarAnime(anime);
        });
    }

    @Test
    void animeDuplicadoTest() throws NumeroDeEpisodiosInvalidoException, AnimeDuplicadoException, NomeDoAnimeVazioException, DataInvalidaAnimeException {
        Anime anime = new Anime();
        anime.setNome("Naruto");
        anime.setNumEpisodios(220);

        when(animeRepository.findByNome(any(String.class))).thenReturn(anime);

        assertThrows(AnimeDuplicadoException.class, () -> {
            cadastroAnime.salvarAnime(anime);
        });
    }

    @Test
    void dataInvalidaAnimeTest() throws NumeroDeEpisodiosInvalidoException, AnimeDuplicadoException, NomeDoAnimeVazioException, DataInvalidaAnimeException {

        Anime anime = new Anime();
        anime.setNome("Hajime no ippo");
        anime.setNumEpisodios(300);
        LocalDate dataInvalida = LocalDate.of(1500, 1, 1);
        anime.setDataLancamento(dataInvalida);

        assertThrows(DataInvalidaAnimeException.class, () -> {
            cadastroAnime.salvarAnime(anime);
        });
    }
}