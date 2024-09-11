package br.edu.ufape.myufapeanime.myufapeanime.negocio.cadastro.cadastroAvaliacaoExceptions;

public class AvaliacaoDuplicadaException extends Exception{
    private static final long serialVersionUID = 1L;
    private long animeId;
    private long usuarioId;

    public AvaliacaoDuplicadaException (long animeId, long usuarioId) {
        super("Usuario já tem uma avaliação para esse Anime!");
        this.animeId = animeId;
        this.usuarioId = usuarioId;
    }

    public long getAnimeId() {
        return animeId;
    }
    public long getUsuarioId() {
        return usuarioId;
    }

}
