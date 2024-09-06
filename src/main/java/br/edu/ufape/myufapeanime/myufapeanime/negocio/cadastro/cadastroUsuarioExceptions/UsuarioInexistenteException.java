package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions;

public class UsuarioInexistenteException extends Exception {
    private static final long serialVersionUID = 1L;
    private Long id;

    public UsuarioInexistenteException(Long id) {
        super("Usuário com id " + id + " não existe.");
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
