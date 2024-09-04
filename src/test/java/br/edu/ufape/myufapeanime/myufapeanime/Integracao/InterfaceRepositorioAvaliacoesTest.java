package br.edu.ufape.myufapeanime.myufapeanime.Integracao;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Adm;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAvaliacoes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Implementa teste de integração para o repositório de Adms
@SpringBootTest
public class InterfaceRepositorioAvaliacoesTest {
    @Autowired
    private InterfaceRepositorioAvaliacoes colecaoAvaliacao;

    @Test
    void cadastrarTest(){
        //Inicialização
        long qtdAvaliacoes = colecaoAvaliacao.count();
        Avaliacao av = new Avaliacao();

        //Interação
        colecaoAvaliacao.save(av);
        long qtdAvaliacoes2 = colecaoAvaliacao.count();

        //Verificação
        assertEquals(qtdAvaliacoes + 1, qtdAvaliacoes2);
    }
}
