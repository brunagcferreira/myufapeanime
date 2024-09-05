package br.edu.ufape.myufapeanime.myufapeanime.exceptions;

public class UsuarioDuplicadoException extends Exception {
    private static final long serialVersionUID = 1L;
    private Long id;

    public UsuarioDuplicadoException(Long id) {
        super("Usuário com id " + id + " já existe.");
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    
}
