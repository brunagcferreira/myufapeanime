package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeDuplicadoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.NumeroDeEpisodiosInvalidoException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroInterface.CadastroInterface;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe responsável por gerenciar as operações relacionadas ao cadastro de animes.
 * Realiza operações de criação, atualização, deleção e busca de animes no sistema.
 *
 * @author VictorAlexandre
 */

@Service
public class CadastroAnime implements CadastroInterface<Anime> {

    @Qualifier("interfaceRepositorioAnimes")
    @Autowired
    private InterfaceRepositorioAnimes animeRepository;

    /**
     * Cria um novo anime no sistema. Verifica se o nome do anime já está cadastrado
     * antes de salvar e se o número de episódios é válido.
     *
     * @param anime O objeto do tipo Anime a ser criado.
     * @return O objeto Anime salvo no banco de dados.
     * @throws AnimeDuplicadoException Lançada quando o nome do anime já está cadastrado.
     * @throws NumeroDeEpisodiosInvalidoException Lançada quando o número de episódios é inválido (<= 0).
     */
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

    /**
     * Lista todos os animes cadastrados no sistema.
     *
     * @return Lista de objetos Anime.
     */
    @Override
    public List<Anime> findAll() {
        return animeRepository.findAll();
    }

    /**
     * Busca um anime pelo seu ID.
     *
     * @param id O ID do anime a ser buscado.
     * @return O objeto Anime encontrado.
     * @throws AnimeInexistenteException Lançada quando o anime não é encontrado no banco de dados.
     */
    @Override
    public Anime findById(Long id) throws AnimeInexistenteException {
        return animeRepository.findById(id).orElseThrow(() -> new AnimeInexistenteException(id));
    }

    /**
     * Busca animes pelo nome.
     *
     * @param nome O nome ou parte do nome do anime a ser buscado.
     * @return Lista de objetos Anime que contenham o nome especificado.
     */
    public List<Anime> findByNome(String nome){
        return animeRepository.findByNomeContainingIgnoreCase(nome);
    }

    /**
     * Atualiza as informações de um anime existente.
     *
     * @param animeAtualizado O objeto Anime com as informações atualizadas.
     * @return O objeto Anime atualizado.
     * @throws AnimeInexistenteException Lançada quando o anime não é encontrado no banco de dados.
     * @throws AnimeDuplicadoException Lançada quando o nome atualizado do anime já está cadastrado.
     */
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

    /**
     * Exclui um anime pelo seu ID.
     *
     * @param id O ID do anime a ser deletado.
     * @throws AnimeInexistenteException Lançada quando o anime não é encontrado no banco de dados.
     */
    @Override
    public void deleteById(Long id) throws AnimeInexistenteException {
        Anime anime = animeRepository.findById(id).orElseThrow(() -> new AnimeInexistenteException(id)); // Verifica se o anime existe
        System.out.println(anime);
        animeRepository.delete(anime);
    }

    /**
     * Exclui um objeto do tipo Anime do banco de dados.
     *
     * @param anime O objeto Anime a ser deletado.
     * @throws Exception Lançada em caso de erro durante a exclusão.
     */
    @Override
    public void delete(Anime anime) {
        animeRepository.delete(anime);
    }

}
