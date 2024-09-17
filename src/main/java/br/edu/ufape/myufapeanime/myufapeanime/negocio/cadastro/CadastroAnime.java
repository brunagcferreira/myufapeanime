package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.NumeroDeEpisodiosInvalidoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroInterface.CadastroInterface;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroAnime implements CadastroInterface<Anime> {

    @Autowired
    private InterfaceRepositorioAnimes animeRepository;

    // Create

    @Override
    public Anime create(Anime anime) throws AnimeDuplicadoException, NumeroDeEpisodiosInvalidoException {
        if (anime.getNumEpisodios() <= 0) {
            throw new NumeroDeEpisodiosInvalidoException();
        }

        if (animeRepository.existsByNomeContainingIgnoreCase(anime.getNome())) {
            throw new AnimeDuplicadoException(anime.getNome());
        }

 //     anime.setAvaliacoes(null);
        anime.setAvaliacoesTotais(0L);
        anime.setNotaMedia(0.0);
        anime.setPontuacao(0.0);
        return animeRepository.save(anime);
    }


    // Read (listar todos)
    @Override
    public List<Anime> findAll() {
        return animeRepository.findAll();
    }

    // Read (listar por ID)
    @Override
    public Anime findById(Long id) throws AnimeInexistenteException {
        return animeRepository.findById(id).orElseThrow(() -> new AnimeInexistenteException(id));
    }

    // Read (listar por nome)
    public List<Anime> findByNomeAnime(String nome){
        return animeRepository.findByNomeContainingIgnoreCase(nome);
    }

    // Update
    @Override
    public Anime update(Anime animeAtualizado) throws AnimeInexistenteException, AnimeDuplicadoException {
        Anime animeExistente = findById(animeAtualizado.getId()); // Verifica se o anime existe

        // Atualizar apenas os campos que não estão nulos ou têm um valor significativo
        if (animeAtualizado.getNome() != null && !animeAtualizado.getNome().isEmpty()) {
            if(animeRepository.existsByNomeContainingIgnoreCase(animeAtualizado.getNome())){
                throw new AnimeDuplicadoException(animeAtualizado.getNome());
            }
            animeExistente.setNome(animeAtualizado.getNome());
        }

        if (animeAtualizado.getGenero() != null && !animeAtualizado.getGenero().isEmpty()) {
            animeExistente.setGenero(animeAtualizado.getGenero());
        }

        if (animeAtualizado.getNumEpisodios() > 0) { // Verifica se o número de episódios é maior que zero
            animeExistente.setNumEpisodios(animeAtualizado.getNumEpisodios());
        }

        // Salva o objeto atualizado no banco
        return animeRepository.save(animeExistente);
    }

    // Delete
    @Override
    public void deleteById(Long id) throws AnimeInexistenteException {
        Anime anime = findById(id); // Verifica se o anime existe
        animeRepository.delete(anime);
    }

    @Override
    public void delete(Anime object) throws Exception {
        animeRepository.delete(object);

    }

}
