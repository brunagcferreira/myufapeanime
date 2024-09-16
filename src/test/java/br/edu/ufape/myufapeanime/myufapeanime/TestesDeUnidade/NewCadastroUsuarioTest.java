package br.edu.ufape.myufapeanime.myufapeanime.TestesDeUnidade;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.*;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroUsuario;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioUsuarios;

@SpringBootTest
public class NewCadastroUsuarioTest {

    @Autowired
    private CadastroUsuario cadastroUsuario;

    @Autowired
    @Qualifier("interfaceRepositorioUsuarios")
    private InterfaceRepositorioUsuarios repositorioUsuarios;

    @Test
    public void testSalvarUsuarioDuplicado() {
        // Cria um usuário com e-mail duplicado
        Usuario usuario1 = new Usuario();
        usuario1.setNome("Carlos Souza");
        usuario1.setEmail("carlos.souza@example.com");
        repositorioUsuarios.save(usuario1);

        // Cria outro usuário com o mesmo e-mail
        Usuario usuario2 = new Usuario();
        usuario2.setNome("Maria Oliveira");
        usuario2.setSenha("123321");
        usuario2.setEmail("carlos.souza@example.com");

        // Verifica se a exceção de duplicidade é lançada
        try {
            cadastroUsuario.create(usuario2);
            fail("Deveria ter lançado UsuarioDuplicadoException");
        } catch (UsuarioDuplicadoException | UsuarioSenhaInvalidaException e) {
            // Verifica a mensagem da exceção
            assertThrows(UsuarioDuplicadoException.class, () -> {
                throw e;
            });
        }
    }
}
