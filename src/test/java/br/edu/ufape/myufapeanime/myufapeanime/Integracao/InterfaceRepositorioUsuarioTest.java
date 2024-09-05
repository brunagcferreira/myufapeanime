package br.edu.ufape.myufapeanime.myufapeanime.Integracao;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioUsuarios;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Implementa teste de integração para o repositório de Adms
@SpringBootTest
public class InterfaceRepositorioUsuarioTest {
    @Autowired
    private InterfaceRepositorioUsuarios colecaoUsuario;

    @Test
    void cadastrarTest(){
        //Inicialização
        long qtdUsuarios = colecaoUsuario.count();
        Usuario user = new Usuario();

        //Interação
        colecaoUsuario.save(user);
        long qtdUsuarios2 = colecaoUsuario.count();

        //Verificação
        assertEquals(qtdUsuarios + 1, qtdUsuarios2);
    }
}
