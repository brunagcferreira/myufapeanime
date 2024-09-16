package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoDuplicadaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions.AvaliacaoNotaInvalidaException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroInterface.CadastroInterface;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions.UsuarioInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAvaliacoes;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CadastroAvaliacao implements CadastroInterface<Avaliacao> {

 //   @Qualifier("interfaceRepositorioAvaliacoes")
    @Autowired
    private InterfaceRepositorioAvaliacoes avaliacaoRepository;

    @Autowired
    private InterfaceRepositorioAnimes animeRepository;

    @Qualifier("interfaceRepositorioUsuarios")
    @Autowired
    private InterfaceRepositorioUsuarios repositorioUsuario;

    // Cadastrar
    @Override
    //TODO: falar com a professora sobre dependencias de outros repositorios no cadastro de avaliação
    public Avaliacao create(Avaliacao avaliacao)
            throws AvaliacaoNotaInvalidaException, UsuarioInexistenteException, AvaliacaoDuplicadaException, AnimeInexistenteException {
    if(avaliacao.getNota() > 5 || avaliacao.getNota() < 0){
        throw new AvaliacaoNotaInvalidaException(avaliacao.getNota());
    }

    if (!repositorioUsuario.existsById(avaliacao.getUsuarioAvaliador())){
        throw new UsuarioInexistenteException(avaliacao.getUsuarioAvaliador());
    }

    if(!animeRepository.existsById(avaliacao.getAnime().getId())){
        throw new AnimeInexistenteException(avaliacao.getAnime().getId());
    }

    if(avaliacaoRepository.existsAvaliacaoByAnimeAndUsuarioAvaliador(avaliacao.getAnime(),avaliacao.getUsuarioAvaliador())){
        throw new AvaliacaoDuplicadaException(avaliacao.getAnime().getId(),avaliacao.getUsuarioAvaliador());
    }
        Avaliacao novaAvaliacao = avaliacaoRepository.save(avaliacao);
        mudarPontuacaoAnime(novaAvaliacao.getAnime(), novaAvaliacao.getNota(), 1L);

        return novaAvaliacao;
    }

    // Atualizar
    @Override
    public Avaliacao update(Avaliacao newAvaliacao)
            throws AvaliacaoNotaInvalidaException, AvaliacaoInexistenteException{

        if(newAvaliacao.getNota() > 5 || newAvaliacao.getNota() < 0){
            throw new AvaliacaoNotaInvalidaException(newAvaliacao.getNota());
        }
        //aqui ele já pega a avaliação antiga e joga a exceção
        Avaliacao antigaAvaliacao = avaliacaoRepository.findById(newAvaliacao.getId())
                .orElseThrow(() -> new AvaliacaoInexistenteException(newAvaliacao.getId()));

        newAvaliacao.setAnime(antigaAvaliacao.getAnime());
        newAvaliacao.setUsuarioAvaliador(antigaAvaliacao.getUsuarioAvaliador());

        double notaAntiga = antigaAvaliacao.getNota();
        double notaAtual = newAvaliacao.getNota();
        Anime anime = newAvaliacao.getAnime();
        // eu tiro a pontuação antiga da nova para calcular a diferença
        mudarPontuacaoAnime(anime, notaAtual - notaAntiga, 0L);

        return avaliacaoRepository.save(newAvaliacao);
    }

    // Deletar


    @Override
    public void delete(Avaliacao avaliacao) throws AvaliacaoInexistenteException {
        deleteById(avaliacao.getId());
    }


    // Listar todas as Avaliações
    public List<Avaliacao> findAll(){
        return avaliacaoRepository.findAll();
    }

    //função comum nos deletes
    @Override
    public void deleteById(Long id) throws AvaliacaoInexistenteException {

        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new AvaliacaoInexistenteException(id));
        //ele tira a nota e tira um do total
        mudarPontuacaoAnime(avaliacao.getAnime(), - avaliacao.getNota(), - 1L);
        avaliacaoRepository.deleteById(id);
    }

    // Procurar uma avaliação
    @Override
    public Avaliacao findById(Long id) throws AvaliacaoInexistenteException {
        return avaliacaoRepository.findById(id).orElseThrow(() -> new AvaliacaoInexistenteException(id));
    }

    private void mudarPontuacaoAnime(Anime anime, Double ajusteNota, Long ajusteAvaliacoes) {
        anime.setPontuacao(anime.getPontuacao() + ajusteNota);
        anime.setAvaliacoesTotais(anime.getAvaliacoesTotais() + ajusteAvaliacoes);
        animeRepository.save(anime);
    }

}
