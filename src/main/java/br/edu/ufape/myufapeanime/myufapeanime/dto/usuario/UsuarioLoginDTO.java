package br.edu.ufape.myufapeanime.myufapeanime.dto.usuario;

import br.edu.ufape.myufapeanime.myufapeanime.dto.anime.AnimeDTO;

import java.util.List;

public class UsuarioLoginDTO extends UsuarioDTO{
    private List<AnimeDTO> assistindo;
    private List<AnimeDTO> completo;
    private List<AnimeDTO> queroAssistir;

    public UsuarioLoginDTO() {
    }

    public List<AnimeDTO> getAssistindo() {
        return assistindo;
    }

    public void setAssistindo(List<AnimeDTO> assistindo) {
        this.assistindo = assistindo;
    }

    public List<AnimeDTO> getCompleto() {
        return completo;
    }

    public void setCompleto(List<AnimeDTO> completo) {
        this.completo = completo;
    }

    public List<AnimeDTO> getQueroAssistir() {
        return queroAssistir;
    }

    public void setQueroAssistir(List<AnimeDTO> queroAssistir) {
        this.queroAssistir = queroAssistir;
    }
}
