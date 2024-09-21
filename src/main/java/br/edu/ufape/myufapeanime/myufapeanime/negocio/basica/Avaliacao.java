package br.edu.ufape.myufapeanime.myufapeanime.negocio.basica;

import jakarta.persistence.*;

/**
 * Classe que representa uma avaliação de um usuário no sistema, associada a um anime específico.
 * Cada avaliação contém uma nota e um comentário, e é vinculada a um usuário avaliador e a um anime.
 *
 * A avaliação reflete a opinião de um usuário sobre um determinado anime, permitindo que diferentes
 * usuários registrem suas próprias avaliações.
 *
 * Relacionamentos:
 * - Cada avaliação está associada a um único anime.
 * - Cada avaliação é realizada por um único usuário.
 *
 * Esta classe é utilizada para armazenar as informações de cada avaliação e
 * facilitar o registro de feedbacks individuais dos usuários.
 *
 * @author JorgeRibeiro
 */

@Entity
@Table(name = "tb_avaliacoes")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double nota;
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "anime_id")  // Define a chave estrangeira no banco
    private Anime anime;  // Relacionamento correto com Anime

    @ManyToOne
    @JoinColumn(name = "usuario_id") // ajuste o nome da coluna conforme necessário
    private Usuario usuarioAvaliador;


    public Avaliacao() {}

    public Avaliacao(Double nota, String comentario, Usuario usuarioAvaliador, Anime anime) {
        this.nota = nota;
        this.comentario = comentario;
        this.usuarioAvaliador = usuarioAvaliador;
        this.anime = anime;  // Passa a entidade Anime diretamente
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuarioAvaliador() {
        return usuarioAvaliador;
    }

    public void setUsuarioAvaliador(Usuario usuarioAvaliador) {
        this.usuarioAvaliador = usuarioAvaliador;
    }

    public Anime getAnime() {
        return anime;  // Retorna o objeto Anime
    }

    public void setAnime(Anime anime) {
        this.anime = anime;  // Define o objeto Anime
    }

}