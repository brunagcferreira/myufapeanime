package br.edu.ufape.myufapeanime.myufapeanime.Integracao;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Implementa teste de integração para o repositório de Adms
@SpringBootTest
public class InterfaceRepositorioUAnimesTest {
    @Autowired
    private InterfaceRepositorioAnimes colecaoAnimes;

    @Test
    void cadastrarTest(){
        //Inicialização
        long qtdAnimes = colecaoAnimes.count();
        Anime an = new Anime();

        //Interação
        colecaoAnimes.save(an);
        long qtdAnimes2 = colecaoAnimes.count();

        //Verificação
        assertEquals(qtdAnimes + 1, qtdAnimes2);
    }
}
