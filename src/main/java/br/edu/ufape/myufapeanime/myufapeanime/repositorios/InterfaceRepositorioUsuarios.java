package br.edu.ufape.myufapeanime.myufapeanime.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;

@Repository
public interface InterfaceRepositorioUsuarios extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNomeContainingIgnoreCase(String nome);

    boolean existsByEmail(String email);
    
    Optional<Usuario> findUsuarioByEmailIgnoreCase (String email);

}
