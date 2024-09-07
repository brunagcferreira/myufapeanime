package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions;

import br.edu.ufape.myufapeanime.myufapeanime.negocio.basica.Usuario;

import java.io.Serial;

public class UsuarioInexistenteException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;
    private Long id;

    public UsuarioInexistenteException(Long id) {
        super("Usuário com id " + id + " não existe.");
        this.id = id;
    }

    public UsuarioInexistenteException() {
        super("Usuário inexistente.");
    }

    public Long getId() {
        return id;
    }
}
