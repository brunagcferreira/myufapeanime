package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions;

public class AvaliacaoInexistenteException extends Exception{
    private static final long serialVersionUID = 1L;
    private long id;
    public AvaliacaoInexistenteException(long id) {
        super("Avaliacao inexistente!");
        this.id = id;
    }
    public long getId() {return id;}

}
