package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions;

import java.io.Serial;

public class AnimeInexistenteException extends Throwable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;

    public AnimeInexistenteException(Long id) {
        super("Anime com id " + id + " não existe.");
        this.id = id;
    }

    public AnimeInexistenteException(String nome) {
        super("Anime com nome " + nome + " não existe.");
    }

    public Long getId() {
        return id;
    }
}
