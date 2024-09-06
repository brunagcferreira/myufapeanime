package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions;

import java.io.Serial;

public class NomeDoAnimeVazioException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public NomeDoAnimeVazioException() {
        super("Nome do anime não pode ser vazio.");
    }
}
