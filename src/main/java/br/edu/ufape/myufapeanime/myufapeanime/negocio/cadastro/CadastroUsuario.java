package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro;

import java.util.List;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroInterface.CadastroInterface;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioSenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioUsuarios;

/**
 * Classe responsável por gerenciar as operações relacionadas ao cadastro de usuários.
 * Realiza operações de criação, atualização, deleção e busca de usuários no sistema.
 * 
 * @author Bruna Ferreira
 */
@Service
public class CadastroUsuario implements CadastroInterface<Usuario> {

    @Qualifier("interfaceRepositorioUsuarios")
    @Autowired
    private InterfaceRepositorioUsuarios repositorioUsuario;

    /**
     * Cria um novo usuário no sistema. Verifica se o e-mail do usuário já está
     * cadastrado antes de salvar.
     * 
     * @param usuario O objeto do tipo Usuario a ser criado.
     * @return O objeto Usuario salvo no banco de dados.
     * @throws UsuarioDuplicadoException Lançada quando o e-mail do usuário já está cadastrado.
     */
    @Override
    public Usuario create(Usuario usuario) throws UsuarioDuplicadoException, UsuarioSenhaInvalidaException {
        if (repositorioUsuario.existsByEmail(usuario.getEmail())) {
            throw new UsuarioDuplicadoException(usuario.getEmail());
        }

        System.out.println(usuario.toString());
        if(usuario.getSenha() == null || usuario.getSenha().length() < 6){
            throw new UsuarioSenhaInvalidaException();
        }

        //usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return repositorioUsuario.save(usuario);
    }


    /**
     * Atualiza um usuário existente no banco de dados. Verifica se o usuário existe
     * antes de realizar a atualização.
     * 
     * @param novo O objeto do tipo Usuario com as informações atualizadas.
     * @return O objeto Usuario atualizado.
     * @throws UsuarioInexistenteException Lançada quando o usuário não é encontrado no banco de dados.
     */
    @Override
    public Usuario update(Usuario novo) throws UsuarioInexistenteException {
        if (!repositorioUsuario.existsById(novo.getId())) {
            throw new UsuarioInexistenteException(novo.getId());
        }
        return repositorioUsuario.save(novo);
    }


    //apagar
    @Override
    public void delete(Usuario usuario) throws UsuarioInexistenteException {
        if (!repositorioUsuario.existsById(usuario.getId())) {
            throw new UsuarioInexistenteException(usuario.getId());
        }
        repositorioUsuario.delete(usuario);
    }
    
    //apagar por id
    @Override
	public void deleteById(Long id) throws UsuarioInexistenteException {
		if(!repositorioUsuario.existsById(id)) {
			throw new UsuarioInexistenteException(id);
        }
		repositorioUsuario.deleteById(id);
	}
    
    //listar todos
    @Override
    public List<Usuario> findAll(){
		return repositorioUsuario.findAll();
	}

    //buscar por id(pode retornar null)
    @Override
    public Usuario findById(Long id) throws UsuarioInexistenteException {
        return repositorioUsuario.findById(id)
                .orElseThrow(() -> new UsuarioInexistenteException(id));
    }

    //buscar por nome(pode retornar null)
	public List<Usuario> findByNome(String nome){
		return repositorioUsuario.findByNomeContainingIgnoreCase(nome);
	}

    public Usuario findByEmail(String email) throws UsuarioInexistenteException{
        return repositorioUsuario.findUsuarioByEmailIgnoreCase(email).orElseThrow(UsuarioInexistenteException::new);
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
