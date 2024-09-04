package br.edu.ufape.myufapeanime.myufapeanime.Integracao;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Adm;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAdm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
// Implementa teste de integração para o repositório de Adms
@SpringBootTest
public class InterfaceRepositorioAdmTest {
    @Autowired
    private InterfaceRepositorioAdm colecaoAdm;

    @Test
    void cadastrarTest(){
        //Inicialização
        long qtdAdms = colecaoAdm.count();
        Adm adm = new Adm();

        //Interação
        colecaoAdm.save(adm);
        long qtdAdms2 = colecaoAdm.count();

        //Verificação
        assertEquals(qtdAdms + 1, qtdAdms2);
    }
}
