package br.edu.ufape.myufapeanime.myufapeanime.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Adm;

@Repository
public interface InterfaceRepositorioAdm extends JpaRepository<Adm, Long> {

}
