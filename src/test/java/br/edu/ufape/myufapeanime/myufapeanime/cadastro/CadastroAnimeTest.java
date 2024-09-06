package br.edu.ufape.myufapeanime.myufapeanime.cadastro;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroAnime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.NomeDoAnimeVazioException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.NumeroDeEpisodiosInvalidoException;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CadastroAnimeTest {
    @Mock
    private InterfaceRepositorioAnimes repositorioAnime;

    @InjectMocks
    private CadastroAnime cadastroAnime;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAnimeComSucesso() throws NumeroDeEpisodiosInvalidoException, AnimeDuplicadoException, NomeDoAnimeVazioException {
        Anime anime = new Anime();
        anime.setNome("Naruto");
        anime.setNumEpisodios(220);
        anime.setId(1L);

        when(repositorioAnime.existsById(1L)).thenReturn(false);
        when(repositorioAnime.save(anime)).thenReturn(anime);

        Anime savedAnime = cadastroAnime.create(anime);
        assertNotNull(savedAnime);
        verify(repositorioAnime, times(1)).save(anime);
    }

    // Testa se o nome é alterado e salvo no formato padrão
    @Test
    void testAsseguraFormatoNomePadrao() throws NumeroDeEpisodiosInvalidoException, AnimeDuplicadoException, NomeDoAnimeVazioException {
        Anime anime = new Anime();
        anime.setNome("nArUtO");
        anime.setNumEpisodios(220);
        anime.setId(1L);

        when(repositorioAnime.existsById(1L)).thenReturn(false);
        when(repositorioAnime.save(anime)).thenReturn(anime);

        Anime savedAnime = cadastroAnime.create(anime);
        assertEquals("Naruto", savedAnime.getNome());
    }

    @Test
    void testCreateAnimeDuplicado() {
        Anime anime = new Anime();
        anime.setNome("Naruto");
        anime.setNumEpisodios(220);
        anime.setId(1L);

        when(repositorioAnime.existsById(1L)).thenReturn(true);

        assertThrows(AnimeDuplicadoException.class, () -> cadastroAnime.create(anime));
        verify(repositorioAnime, times(0)).save(anime);
    }

    @Test
    void testCreateAnimeNomeVazio() {
        Anime anime = new Anime();
        anime.setNome("");
        anime.setId(1L);

        assertThrows(NomeDoAnimeVazioException.class, () -> cadastroAnime.create(anime));
        verify(repositorioAnime, times(0)).save(anime);
    }

    @Test
    void testCreateAnimeNumeroDeEpisodiosInvalido() {
        Anime anime = new Anime();
        anime.setNome("Naruto");
        anime.setNumEpisodios(-100);
        anime.setId(1L);

        assertThrows(NumeroDeEpisodiosInvalidoException.class, () -> cadastroAnime.create(anime));
        verify(repositorioAnime, times(0)).save(anime);
    }

    @Test
    void testUpdateAnimeComSucesso() throws AnimeInexistenteException {
        Anime anime = new Anime();
        anime.setNome("Naruto");
        anime.setNumEpisodios(220);
        anime.setId(1L);

        when(repositorioAnime.existsById(1L)).thenReturn(true);
        when(repositorioAnime.save(anime)).thenReturn(anime);

        Anime animeUpdate = new Anime();
        animeUpdate.setNome("Naruto Shippuden");
        animeUpdate.setNumEpisodios(500);
        animeUpdate.setId(1L);

        when(repositorioAnime.existsById(1L)).thenReturn(true);
        when(repositorioAnime.save(animeUpdate)).thenReturn(animeUpdate);

        Anime updatedAnime = cadastroAnime.update(animeUpdate);
        assertNotNull(updatedAnime);
        assertEquals("Naruto Shippuden", updatedAnime.getNome());
        assertEquals(500, updatedAnime.getNumEpisodios());
        verify(repositorioAnime, times(2)).save(animeUpdate);
    }

    @Test
    void testUpdateAnimeInexistente() {
        Anime anime = new Anime();
        anime.setNome("Naruto");
        anime.setNumEpisodios(220);
        anime.setId(1L);

        when(repositorioAnime.existsById(1L)).thenReturn(false);

        assertThrows(AnimeInexistenteException.class, () -> cadastroAnime.update(anime));
        verify(repositorioAnime, times(0)).save(anime);
    }

    @Test
    void testDeleteAnimeComSucesso() throws AnimeInexistenteException {
        Anime anime = new Anime();
        anime.setNome("Naruto");
        anime.setNumEpisodios(220);
        anime.setId(1L);

        when(repositorioAnime.existsById(1L)).thenReturn(true);

        cadastroAnime.delete(anime);
        verify(repositorioAnime, times(1)).delete(anime);
    }

    @Test
    void testDeleteAnimeInexistente() {
        Anime anime = new Anime();
        anime.setNome("Naruto");
        anime.setNumEpisodios(220);
        anime.setId(1L);

        when(repositorioAnime.existsById(1L)).thenReturn(false);

        assertThrows(AnimeInexistenteException.class, () -> cadastroAnime.delete(anime));
        verify(repositorioAnime, times(0)).delete(anime);
    }

    @Test
    void testDeleteByIdAnimeComSucesso() throws AnimeInexistenteException {
        when(repositorioAnime.existsById(1L)).thenReturn(true);

        cadastroAnime.deleteById(1L);
        verify(repositorioAnime, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteByIdAnimeInexistente() {
        when(repositorioAnime.existsById(1L)).thenReturn(false);

        assertThrows(AnimeInexistenteException.class, () -> cadastroAnime.deleteById(1L));
        verify(repositorioAnime, times(0)).deleteById(1L);
    }

    @Test
    void testFindAllAnimeComSucesso() {
        cadastroAnime.findAll();
        verify(repositorioAnime, times(1)).findAll();
    }

    @Test
    void testFindByNomeAnimeComSucesso() throws NomeDoAnimeVazioException, AnimeInexistenteException {
        when(repositorioAnime.findByNomeContainingIgnoreCase("Naruto")).thenReturn(Collections.singletonList(new Anime()));

        cadastroAnime.findByNome("Naruto");
        verify(repositorioAnime, times(1)).findByNomeContainingIgnoreCase("Naruto");
    }

    @Test
    void testFindByNomeAnimeNomeVazio() {
        assertThrows(NomeDoAnimeVazioException.class, () -> cadastroAnime.findByNome(""));
        verify(repositorioAnime, times(0)).findByNomeContainingIgnoreCase("");
    }

    @Test
    void testFindByNomeAnimeInexistente() {
        when(repositorioAnime.findByNomeContainingIgnoreCase("Naruto")).thenReturn(Collections.emptyList());

        assertThrows(AnimeInexistenteException.class, () -> cadastroAnime.findByNome("Naruto"));
        verify(repositorioAnime, times(1)).findByNomeContainingIgnoreCase("Naruto");
    }
}