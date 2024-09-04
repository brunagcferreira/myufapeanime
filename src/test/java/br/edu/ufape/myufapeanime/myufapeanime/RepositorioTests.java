package br.edu.ufape.myufapeanime.myufapeanime;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Adm;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAdm;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAvaliacoes;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioUsuarios;
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

    @Test
    void testRepositorioAdm() {
        //Inicialização
        long qtdAdms = repositorioAdm.count();
        Adm adm = new Adm("AdmistradorDeAnimes", "AdmistradorDeAnimes@email.com");

        //Interação
        repositorioAdm.save(adm);
        long qtdAdms2 = repositorioAdm.count();

        //Verificação
        assertEquals(qtdAdms + 1, qtdAdms2);

        assertThat(repositorioAdm).isNotNull();
    }

    @Test
    void testRepositorioAnimes() {
        // Adicione aqui os testes para o repositório de Animes
        //Inicialização
        long qtdAnimes = repositorioAnimes.count();
        Anime an = new Anime("Naruto", "Aventura", 320, 1000.0, 2002L, 220.0);

        //Interação
        repositorioAnimes.save(an);
        long qtdAnimes2 = repositorioAnimes.count();

        //Verificação
        assertEquals(qtdAnimes + 1, qtdAnimes2);

        assertThat(repositorioAnimes).isNotNull();
    }

    @Test
    void testRepositorioAvaliacoes() {
        // Adicione aqui os testes para o repositório de Avaliacoes
        //Inicialização
        long qtdAvaliacoes = repositorioAvaliacoes.count();
        Avaliacao av = new Avaliacao(10.0, "Incrivel");

        //Interação
        repositorioAvaliacoes.save(av);
        long qtdAvaliacoes2 = repositorioAvaliacoes.count();

        //Verificação
        assertEquals(qtdAvaliacoes + 1, qtdAvaliacoes2);

        assertThat(repositorioAvaliacoes).isNotNull();
    }

    @Test
    void testRepositorioUsuarios() {
        // Adicione aqui os testes para o repositório de Usuarios
        //Inicialização
        long qtdUsuarios = repositorioUsuarios.count();
        Usuario user = new Usuario("UsuarioDeAnimes", "UsuarioDeAnimes@email.com");

        //Interação
        repositorioUsuarios.save(user);
        long qtdUsuarios2 = repositorioUsuarios.count();

        //Verificação
        assertEquals(qtdUsuarios + 1, qtdUsuarios2);

        assertThat(repositorioUsuarios).isNotNull();
    }
}