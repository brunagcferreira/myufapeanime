package br.edu.ufape.myufapeanime.myufapeanime;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.*;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAdm;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAvaliacoes;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioUsuarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RepositorioTests {

    @Autowired
    private InterfaceRepositorioAdm repositorioAdm;

    @Autowired
    private InterfaceRepositorioAnimes repositorioAnimes;

    @Autowired
    private InterfaceRepositorioAvaliacoes repositorioAvaliacoes;

    @Autowired
    private InterfaceRepositorioUsuarios repositorioUsuarios;

    private Adm adm;
    private Anime anime;
    private Avaliacao avaliacao;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        //criação dos objetos vazios para teste de integração
        adm = new Adm();
        anime = new Anime();
        avaliacao = new Avaliacao();
        usuario = new Usuario();
    }

    @Test
    void testRepositorioAdm() {
        long qtdAdms = repositorioAdm.count();
        repositorioAdm.save(adm);
        long qtdAdms2 = repositorioAdm.count();
        assertEquals(qtdAdms + 1, qtdAdms2);
        assertThat(repositorioAdm.findById(adm.getId())).isPresent();
    }

    @Test
    void testRepositorioAnimes() {
        long qtdAnimes = repositorioAnimes.count();
        repositorioAnimes.save(anime);
        long qtdAnimes2 = repositorioAnimes.count();
        assertEquals(qtdAnimes + 1, qtdAnimes2);
        assertThat(repositorioAnimes.findById(anime.getId())).isPresent();
    }

    @Test
    void testRepositorioAvaliacoes() {
        long qtdAvaliacoes = repositorioAvaliacoes.count();
        repositorioAvaliacoes.save(avaliacao);
        long qtdAvaliacoes2 = repositorioAvaliacoes.count();
        assertEquals(qtdAvaliacoes + 1, qtdAvaliacoes2);
        assertThat(repositorioAvaliacoes.findById(avaliacao.getId())).isPresent();
    }

    @Test
    void testRepositorioUsuarios() {
        long qtdUsuarios = repositorioUsuarios.count();
        repositorioUsuarios.save(usuario);
        long qtdUsuarios2 = repositorioUsuarios.count();
        assertEquals(qtdUsuarios + 1, qtdUsuarios2);
        assertThat(repositorioUsuarios.findById(usuario.getId())).isPresent();
    }
}