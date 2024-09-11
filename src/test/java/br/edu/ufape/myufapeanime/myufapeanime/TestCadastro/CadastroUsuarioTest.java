package br.edu.ufape.myufapeanime.myufapeanime.TestCadastro;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.CadastroUsuario;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioUsuarios;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

class CadastroUsuarioTest {

    @Mock
    private InterfaceRepositorioUsuarios repositorioUsuario;

    @InjectMocks
    private CadastroUsuario cadastroUsuario;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUsuarioComSucesso() throws UsuarioDuplicadoException {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(repositorioUsuario.existsById(1L)).thenReturn(false);
        when(repositorioUsuario.save(usuario)).thenReturn(usuario);

        Usuario savedUsuario = cadastroUsuario.create(usuario);
        assertNotNull(savedUsuario);
        verify(repositorioUsuario, times(1)).save(usuario);
    }

    @Test
    void testSaveUsuarioDuplicado() {
        Usuario usuario = new Usuario();
        usuario.setEmail("email_duplicado@gmail.com");
    
        when(repositorioUsuario.existsByEmail(usuario.getEmail())).thenReturn(true);
    
        assertThrows(UsuarioDuplicadoException.class, () -> cadastroUsuario.create(usuario));
    
        verify(repositorioUsuario, times(0)).save(usuario);
    }
    

    @Test
    void testUpdateUsuarioInexistente() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(repositorioUsuario.existsById(1L)).thenReturn(false);

        assertThrows(UsuarioInexistenteException.class, () -> cadastroUsuario.update(usuario));
        verify(repositorioUsuario, times(0)).save(usuario); // save não é chamado
    }

    @Test
    void testUpdateUsuarioComSucesso() throws UsuarioInexistenteException {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        when(repositorioUsuario.existsById(1L)).thenReturn(true);
        when(repositorioUsuario.save(usuario)).thenReturn(usuario);

        Usuario updatedUsuario = cadastroUsuario.update(usuario);
        assertNotNull(updatedUsuario);
        verify(repositorioUsuario, times(1)).save(usuario);
    }

    @Test
    void testDeleteUsuarioPorIdInexistente() {
        when(repositorioUsuario.existsById(1L)).thenReturn(false);

        assertThrows(UsuarioInexistenteException.class, () -> cadastroUsuario.deleteById(1L));
        verify(repositorioUsuario, times(0)).deleteById(1L);
    }

    @Test
    void testDeleteUsuarioPorIdComSucesso() throws UsuarioInexistenteException {
        when(repositorioUsuario.existsById(1L)).thenReturn(true);

        cadastroUsuario.deleteById(1L);
        verify(repositorioUsuario, times(1)).deleteById(1L);
    }

    @Test
    void testGetAssistindoUsuarioComSucesso() throws UsuarioInexistenteException {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        Anime anime1 = new Anime();
        Anime anime2 = new Anime();
        usuario.setAssistindo(Arrays.asList(anime1, anime2));

        when(repositorioUsuario.findById(1L)).thenReturn(Optional.of(usuario));

        List<Anime> assistindo = cadastroUsuario.getAssistindo(1L);
        assertEquals(2, assistindo.size());
        verify(repositorioUsuario, times(1)).findById(1L);
    }

    @Test
    void testGetAssistindoUsuarioInexistente() {
        when(repositorioUsuario.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UsuarioInexistenteException.class, () -> cadastroUsuario.getAssistindo(1L));
        verify(repositorioUsuario, times(1)).findById(1L);
    }
}
