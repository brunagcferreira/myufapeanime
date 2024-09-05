package br.edu.ufape.myufapeanime.myufapeanime.exceptions.cadastroAnimeExceptions;

import java.io.Serial;

public class AnimeDuplicadoException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;
    public AnimeDuplicadoException(String nome) {
        super("Já existe um anime cadastrado com o nome " + nome);
    }
}
