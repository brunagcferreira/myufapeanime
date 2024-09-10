package br.edu.ufape.myufapeanime.myufapeanime.dto.mappers;

import br.edu.ufape.myufapeanime.myufapeanime.dto.AvaliacaoComIdDTO;
import br.edu.ufape.myufapeanime.myufapeanime.dto.AvaliacaoDTO;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Avaliacao;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions.AnimeInexistenteException;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada.GerenciadorAnimes;
import org.springframework.beans.factory.annotation.Autowired;
import br.edu.ufape.myufapeanime.myufapeanime.negocio.fachada.GerenciadorAnimes;
import br.edu.ufape.myufapeanime.myufapeanime.repositorios.InterfaceRepositorioAnimes;

public class AvaliacaoMapper {

    @Autowired
    private GerenciadorAnimes gerenciador;


    //converte Avaliacao para AvaliacaoComIdDTO
    public static AvaliacaoComIdDTO convertToComIdDTO(Avaliacao avaliacao) {
        AvaliacaoComIdDTO dto = new AvaliacaoComIdDTO();
        dto.setId(avaliacao.getId());
        dto.setNota(avaliacao.getNota());
        dto.setComentario(avaliacao.getComentario());
        dto.setUsuarioAvaliador(avaliacao.getUsuarioAvaliador());
        dto.setAnimeAvaliado(avaliacao.getAnime().getId());

        return dto;
    }

    //converte avaliacao em avaliacaoDTO
    public static AvaliacaoDTO convertToDTO(Avaliacao avaliacao) {
        AvaliacaoDTO dto = new AvaliacaoDTO();
        dto.setId(avaliacao.getId());
        dto.setNota(avaliacao.getNota());
        dto.setComentario(avaliacao.getComentario());
        dto.setUsuarioAvaliador(avaliacao.getUsuarioAvaliador());
        dto.setAnimeAvaliado(avaliacao.getAnime());                             // Objeto Anime

        return dto;
    }
    
}
