package br.edu.ufape.myufapeanime.myufapeanime.repositorios;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;

import java.util.List;

@Repository
public interface InterfaceRepositorioAvaliacoes extends JpaRepository<Avaliacao, Long> {




    Long findByUsuarioAvaliador(Long id);
    Long findByAnimeId(Long id);

    //  List<Avaliacao> findByNomeUsuario(String nome);
    //  List<Avaliacao> findByNomeAnime(String nome);

}
