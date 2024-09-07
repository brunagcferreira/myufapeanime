package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.NumeroDeEpisodiosInvalidoException;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

import java.util.List;

@Service
public class CadastroAnime {

    @Autowired
    private InterfaceRepositorioAnimes animeRepository;

    // Create
    public Anime cadastrarAnime(Anime anime) throws AnimeDuplicadoException, NumeroDeEpisodiosInvalidoException {
        if (anime.getNumeroEpisodios() <= 0) {
            throw new NumeroDeEpisodiosInvalidoException();
        }

        if (animeRepository.existsByNomeContainingIgnoreCase(anime.getNome())) {
            throw new AnimeDuplicadoException(anime.getNome());
        }
        return animeRepository.save(anime);
    }

    // Read (listar todos)
    public List<Anime> listarAnimes() {
        return animeRepository.findAll();
    }

    // Read (listar por ID)
    public Anime findByIdAnime(Long id) throws AnimeInexistenteException {
        return animeRepository.findById(id).orElseThrow(() -> new AnimeInexistenteException(id));
    }

    // Read (listar por nome)
    public List<Anime> findByNomeAnime(String nome){
        return animeRepository.findByNomeContainingIgnoreCase(nome);
    }

    // Update
    public Anime atualizarAnime(Long id, Anime animeAtualizado) throws AnimeInexistenteException {
        Anime animeExistente = findByIdAnime(id); // Verifica se o anime existe

        // Atualizar apenas os campos que não estão nulos ou têm um valor significativo
        if (animeAtualizado.getNome() != null && !animeAtualizado.getNome().isEmpty()) {
            animeExistente.setNome(animeAtualizado.getNome());
        }

        if (animeAtualizado.getGenero() != null && !animeAtualizado.getGenero().isEmpty()) {
            animeExistente.setGenero(animeAtualizado.getGenero());
        }

        if (animeAtualizado.getNumeroEpisodios() > 0) { // Verifica se o número de episódios é maior que zero
            animeExistente.setNumEpisodios(animeAtualizado.getNumeroEpisodios());
        }

        // Salva o objeto atualizado no banco
        return animeRepository.save(animeExistente);
    }

    // Delete
    public void deletarAnime(Long id) throws AnimeInexistenteException {
        Anime anime = findByIdAnime(id); // Verifica se o anime existe
        animeRepository.delete(anime);
    }

    private boolean DataLancamentoValidaAnime(Anime anime) {
        LocalDate hoje = LocalDate.now();
        LocalDate animeDataLancamento = anime.getDataLancamento();

        // rezam lendas que um dos primeiros animes foi feito por volta dessa data
        LocalDate primeiroAnimeLancado = LocalDate.of(1907, 1, 1);

        return animeDataLancamento != null && !animeDataLancamento.isAfter(hoje)
                && !animeDataLancamento.isBefore(primeiroAnimeLancado);
    }
}
