package br.edu.ufape.myufapeanime.myufapeanime.exceptions;

public class UsuarioDuplicadoException extends Exception {
    private static final long serialVersionUID = 1L;
    private String email;

    public UsuarioDuplicadoException(String email) {
        super(email + " já cadastrado.");
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    
    
}
