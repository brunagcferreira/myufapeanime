package br.edu.ufape.myufapeanime.myufapeanime.repositorios;

import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface InterfaceRepositorioAdm extends InterfaceRepositorioUsuarios {
    

    Optional<Object> findByEmail(String name);
}
