package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro;

import java.util.List;
import java.util.Optional;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioUsuarios;

@Service
public class CadastroUsuario {

    @Qualifier("interfaceRepositorioUsuarios")
    @Autowired
    private InterfaceRepositorioUsuarios repositorioUsuario;


    //salvar
    public Usuario save(Usuario usuario) throws UsuarioDuplicadoException {
        if (repositorioUsuario.existsByEmail(usuario.getEmail())) {
            throw new UsuarioDuplicadoException(usuario.getEmail());
        }

        //usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return repositorioUsuario.save(usuario);
    }


	//atualizar
    public Usuario update(Usuario novo) throws UsuarioInexistenteException {
        if (!repositorioUsuario.existsById(novo.getId())) {
            throw new UsuarioInexistenteException(novo.getId());
        }
        return repositorioUsuario.save(novo);
    }

    //apagar por id
	public void deleteById(Long id) throws UsuarioInexistenteException {
		if(!repositorioUsuario.existsById(id)) {
			throw new UsuarioInexistenteException(id);
        }
		repositorioUsuario.deleteById(id);
	}

    //apagar
    public void delete(Usuario usuario) throws UsuarioInexistenteException {
        if (!repositorioUsuario.existsById(usuario.getId())) {
            throw new UsuarioInexistenteException(usuario.getId());
        }
        repositorioUsuario.delete(usuario);
    }

    //listar todos
    public List<Usuario> findAll(){
		return repositorioUsuario.findAll();
	}
    
    //buscar por id(pode retornar null)
	public Optional<Usuario> findById(Long id) {
		return repositorioUsuario.findById(id);
	}
	
    //buscar por nome(pode retornar null)
	public List<Usuario> findByNome(String nome){
		return repositorioUsuario.findByNomeContainingIgnoreCase(nome);
	}

    //lista assistindo
    public List<Anime> getAssistindo(Long usuarioId) throws UsuarioInexistenteException {
        Usuario usuario = repositorioUsuario.findById(usuarioId)
            .orElseThrow(() -> new UsuarioInexistenteException(usuarioId));
        return usuario.getAssistindo();
    }

    //lista completos
    public List<Anime> getCompleto(Long usuarioId) throws UsuarioInexistenteException {
        Usuario usuario = repositorioUsuario.findById(usuarioId)
            .orElseThrow(() -> new UsuarioInexistenteException(usuarioId));
        return usuario.getCompleto();
    }
    
    //lista quero assistir
    public List<Anime> getQueroAssistir(Long usuarioId) throws UsuarioInexistenteException {
        Usuario usuario = repositorioUsuario.findById(usuarioId)
            .orElseThrow(() -> new UsuarioInexistenteException(usuarioId));
        return usuario.getQueroAssistir();
    }
    
}
