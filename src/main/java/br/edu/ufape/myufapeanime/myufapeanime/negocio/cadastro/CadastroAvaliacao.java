package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAvaliacoes;
import jakarta.transaction.Transactional;


@Service
public class CadastroAvaliacao {

    @Autowired
    private InterfaceRepositorioAvaliacoes avaliacaoRepository;

    @Autowired
    private InterfaceRepositorioAnimes animeRepository;
    
    @Transactional
    public Avaliacao salvarAvaliacao(Avaliacao avaliacao) {
        Avaliacao novaAvaliacao = avaliacaoRepository.save(avaliacao);

        Anime anime = novaAvaliacao.getAnime();//byId
        //anime = cadatsroanime.findById(anime.getId());
        atualizarPontuacaoECalcularMedia(anime, novaAvaliacao.getNota());

        return novaAvaliacao;
    }

    private void atualizarPontuacaoECalcularMedia(Anime anime, Double novaNota) {
        anime.setPontuacao(anime.getPontuacao() + novaNota);
        anime.setAvaliacoesTotais(anime.getAvaliacoesTotais() + 1);

        Double novaMedia = anime.getPontuacao() / anime.getAvaliacoesTotais();
        anime.setNotaMedia(novaMedia);

        animeRepository.save(anime);
    }
}
