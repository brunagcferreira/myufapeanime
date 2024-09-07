package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions;

public class AnimeInexistenteException extends Exception {
    public AnimeInexistenteException() {
        super("Anime não encontrado");
    }
}
