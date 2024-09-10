package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Anime;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAvaliacoes;

import java.util.List;
import java.util.Optional;


@Service
public class CadastroAvaliacao {

 //   @Qualifier("interfaceRepositorioAvaliacoes")
    @Autowired
    private InterfaceRepositorioAvaliacoes avaliacaoRepository;

    @Autowired
    private InterfaceRepositorioAnimes animeRepository;

    // Cadastrar
    public Avaliacao save(Avaliacao avaliacao) /* Criar um Exception*/{

        Avaliacao novaAvaliacao = avaliacaoRepository.save(avaliacao);
        atualizarPontuacaoESomarMedia(novaAvaliacao.getAnime(), novaAvaliacao.getNota());

        return novaAvaliacao;
    }

    // Atualizar
    public Avaliacao update(Avaliacao newAvaliacao, Optional<Avaliacao> antigaAvaliacao) /* Criar um Exception*/ {
        double notaAntiga = antigaAvaliacao.get().getNota();
        Avaliacao novaAvaliacao = avaliacaoRepository.save(newAvaliacao);
        ManterPontuacaoESomarMedia(notaAntiga, novaAvaliacao.getAnime(), novaAvaliacao.getNota());

        return novaAvaliacao;
    }

    // Deletar
    public void deleteAvaliacao(Long id)  /* Criar um Exception*/ {
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(""));

        atualizarPontuacaoESubtrairMedia(avaliacao.getAnime(), avaliacao.getNota());
        avaliacaoRepository.deleteById(id);
    }

    // Listar todas as Avaliações
    public List<Avaliacao> findAll(){
        return avaliacaoRepository.findAll();
    }

    // Procurar uma avaliação
    public Optional<Avaliacao> findByIdAvaliacao(Long id)  {
        return avaliacaoRepository.findById(id);
    }
    private void atualizarPontuacaoESomarMedia(Anime anime, Double novaNota) {
        anime.setPontuacao(anime.getPontuacao() + novaNota);
        anime.setAvaliacoesTotais(anime.getAvaliacoesTotais() + 1);
        Double novaMedia = anime.getPontuacao() / anime.getAvaliacoesTotais();
        anime.setNotaMedia(novaMedia);

        animeRepository.save(anime);
    }

    private void atualizarPontuacaoESubtrairMedia(Anime anime, Double avaliacaoNota) {

        anime.setPontuacao(anime.getPontuacao() - avaliacaoNota);
        anime.setAvaliacoesTotais(anime.getAvaliacoesTotais() - 1);
        if(anime.getPontuacao() == 0.0){
            anime.setNotaMedia(0.0);
            animeRepository.save(anime);
            return;
        }
        Double novaMedia = anime.getPontuacao() / anime.getAvaliacoesTotais();
        anime.setNotaMedia(novaMedia);

        animeRepository.save(anime);
    }

    private void ManterPontuacaoESomarMedia(Double antigaNota, Anime anime, Double novaNota) {
        anime.setPontuacao(anime.getPontuacao() + novaNota);
        anime.setPontuacao(anime.getPontuacao() - antigaNota);
        Double novaMedia = anime.getPontuacao() / anime.getAvaliacoesTotais();
        anime.setNotaMedia(novaMedia);

        animeRepository.save(anime);
    }

}
