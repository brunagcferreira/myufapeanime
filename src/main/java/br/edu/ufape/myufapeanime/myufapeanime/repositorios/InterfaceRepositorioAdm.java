package br.edu.ufape.myufapeanime.myufapeanime.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Adm;

import java.util.Optional;

@Repository
public interface InterfaceRepositorioAdm extends InterfaceRepositorioUsuarios {
    //Optional<Adm> findAdmByEmail(String email);

    Optional<Object> findByEmail(String name);
}
