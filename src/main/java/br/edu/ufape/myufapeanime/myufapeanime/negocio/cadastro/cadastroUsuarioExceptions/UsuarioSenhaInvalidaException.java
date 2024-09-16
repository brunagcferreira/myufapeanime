package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroUsuarioExceptions;

public class UsuarioSenhaInvalidaException extends Exception {
    public UsuarioSenhaInvalidaException() {
        super("Senha inválida.");
    }
}
