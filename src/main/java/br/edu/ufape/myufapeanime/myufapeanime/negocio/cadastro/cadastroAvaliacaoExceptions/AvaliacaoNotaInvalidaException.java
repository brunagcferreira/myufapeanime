package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions;

public class AvaliacaoNotaInvalidaException extends Exception{
    private static final long serialVersionUID = 1L;
    private double nota;

    public AvaliacaoNotaInvalidaException(double nota) {
        super("Nota de Cadastro/Update Invalida!");
        this.nota = nota;
    }

    public double getNota() {
        return nota;
    }
}
