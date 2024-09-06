package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.NomeDoAnimeVazioException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.NumeroDeEpisodiosInvalidoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroAnime {
    @Autowired
    private InterfaceRepositorioAnimes animeRepository;

    //CRUD

    // Create
    public Anime create(Anime novoAnime) throws AnimeDuplicadoException, NomeDoAnimeVazioException, NumeroDeEpisodiosInvalidoException {
        // Verifica se o novoAnime é nulo
        if(novoAnime == null || novoAnime.getNome() == null || novoAnime.getNome().isEmpty()) {
            throw new NomeDoAnimeVazioException();
        }

        //verifica se o numero de episodios é zero ou negativo
        if(novoAnime.getNumEpisodios() <= 0) {
            throw new NumeroDeEpisodiosInvalidoException();
        }

        // Converte o nome do novoAnime para o formato padrão, com a primeira letra maiúscula e o restante minúscula
        String nome = novoAnime.getNome();
        nome = nome.trim();  // remove espaços em branco no início e no final
        nome = nome.replaceAll("\\s+", " ");  // remove espaços em branco duplicados

        nome = nome.toLowerCase();  // transforma tudo para minúsculas
        nome = nome.substring(0, 1).toUpperCase() + nome.substring(1);  // primeira letra maiúscula
        novoAnime.setNome(nome);

        //Verifica se um novoAnime com o mesmo nome já está cadastrado
        if(!animeRepository.existsById(novoAnime.getId())){
            animeRepository.save(novoAnime);
        }
        else {
            throw new AnimeDuplicadoException(novoAnime.getNome());
        }
        return novoAnime;
    }

    // Read
    public List<Anime> findAll() {
        return animeRepository.findAll();
    }

    public List<Anime> findByNome(String nome) throws NomeDoAnimeVazioException, AnimeInexistenteException {
        if(nome == null || nome.isEmpty()) {
            throw new NomeDoAnimeVazioException();
        }

        List<Anime> animes = animeRepository.findByNomeContainingIgnoreCase(nome);

        if(animes.isEmpty()) {
            throw new AnimeInexistenteException(nome);
        }

        return animes;
    }

    // Upadate
    public Anime update(Anime anime) throws AnimeInexistenteException {
        if(animeRepository.existsById(anime.getId())){
            animeRepository.save(anime);
        }
        else
        {
            throw new AnimeInexistenteException(anime.getId());
        }
        animeRepository.save(anime);
        return anime;
    }

   // Delete
public void delete(Anime anime) throws AnimeInexistenteException {
    if(animeRepository.existsById(anime.getId())) {
        animeRepository.delete(anime);
    } else {
        throw new AnimeInexistenteException(anime.getId());
    }
}

    public void deleteById(Long id) throws AnimeInexistenteException {
        if(!animeRepository.existsById(id)) {
            throw new AnimeInexistenteException(id);
        }
        animeRepository.deleteById(id);
    }
}
