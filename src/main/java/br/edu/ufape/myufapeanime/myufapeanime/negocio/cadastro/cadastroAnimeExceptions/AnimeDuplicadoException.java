package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions;

import java.io.Serial;

public class AnimeDuplicadoException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    private String nome;
    public AnimeDuplicadoException(String nome) {
        super("Já existe um anime cadastrado com o nome " + nome);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
