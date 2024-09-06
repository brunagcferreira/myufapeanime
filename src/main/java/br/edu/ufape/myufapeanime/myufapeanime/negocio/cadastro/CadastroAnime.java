package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro;

import br.edu.ufape.myufapeanime.myufapeanime.exceptions.cadastroAnimeExceptions.AnimeDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.exceptions.cadastroAnimeExceptions.NomeDoAnimeVazioException;
import br.edu.ufape.myufapeanime.myufapeanime.exceptions.cadastroAnimeExceptions.NumeroDeEpisodiosInvalidoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroAnime {
    @Autowired
    private InterfaceRepositorioAnimes animeRepository;

    @Transactional
    public void salvarAnime(Anime anime) throws AnimeDuplicadoException, NomeDoAnimeVazioException, NumeroDeEpisodiosInvalidoException {
        // Verifica se o anime é nulo
        if(anime == null) {
            throw new NomeDoAnimeVazioException();
        }

        //verifica se o numero de episodios é zero ou negativo
        if(anime.getNumEpisodios() <= 0) {
            throw new NumeroDeEpisodiosInvalidoException();
        }

        // Converte o nome do anime para o formato padrão, com a primeira letra maiúscula e o restante minúscula
        String nome = anime.getNome();
        nome = nome.trim();  // remove espaços em branco no início e no final
        nome = nome.replaceAll("\\s+", " ");  // remove espaços em branco duplicados

        nome = nome.toLowerCase();  // transforma tudo para minúsculas
        nome = nome.substring(0, 1).toUpperCase() + nome.substring(1);  // primeira letra maiúscula
        anime.setNome(nome);

        //Verifica se um anime com o mesmo nome já está cadastrado
        if(animeRepository.findByNome(anime.getNome()) == null) {
            animeRepository.save(anime);
        }
        else {
            throw new AnimeDuplicadoException(anime.getNome());
        }
    }
}