package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAnimeExceptions;

import java.io.Serial;

public class NumeroDeEpisodiosInvalidoException extends Throwable {
    @Serial
    private static final long serialVersionUID = 1L;

    public NumeroDeEpisodiosInvalidoException() {
        super("O número de episódios deve ser maior que zero");
    }
}
