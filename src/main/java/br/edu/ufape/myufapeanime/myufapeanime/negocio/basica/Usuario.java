package br.edu.ufape.myufapeanime.myufapeanime.negocio.basica;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Classe que representa um usuário do sistema. Um usuário pode ter listas de animes
 * que está assistindo, já completou ou deseja assistir. Além disso, a classe contém
 * informações básicas do usuário, como nome, e-mail e senha.
 * 
 * Esta classe é utilizada para gerenciar o perfil de usuário, associando-o às suas
 * interações com o sistema, como avaliações e listas de animes.
 * 
 * Relacionamentos:
 * - Um usuário pode ter vários animes na lista "assistindo".
 * - Um usuário pode ter vários animes na lista "completo".
 * - Um usuário pode ter vários animes na lista "quero assistir".
 * 
 * @author BrunaFerreira
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "tb_usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String password;
    @ManyToMany
    @JoinTable(name = "tb_assistindo",
               joinColumns = @JoinColumn(name = "usuario_id"),
               inverseJoinColumns = @JoinColumn(name = "anime_id"))
    private List<Anime> assistindo;

    @ManyToMany
    @JoinTable(name = "tb_completo",
               joinColumns = @JoinColumn(name = "usuario_id"),
               inverseJoinColumns = @JoinColumn(name = "anime_id"))
    private List<Anime> completo;

    @ManyToMany
    @JoinTable(name = "tb_quero_assistir",
               joinColumns = @JoinColumn(name = "usuario_id"),
               inverseJoinColumns = @JoinColumn(name = "anime_id"))
    private List<Anime> queroAssistir;

    public Usuario() {}

    public Usuario(String nome, String email, List<Anime> assistindo, List<Anime> completo, List<Anime> queroAssistir) {
        this.nome = nome;
        this.email = email;
        this.assistindo = assistindo;
        this.completo = completo;
        this.queroAssistir = queroAssistir;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }
    public List<Anime> getAssistindo() {
        return assistindo;
    }

    public void setAssistindo(List<Anime> assistindo) {
        this.assistindo = assistindo;
    }

    public List<Anime> getCompleto() {
        return completo;
    }

    public void setCompleto(List<Anime> completo) {
        this.completo = completo;
    }

    public List<Anime> getQueroAssistir() {
        return queroAssistir;
    }

    public void setQueroAssistir(List<Anime> queroAssistir) {
        this.queroAssistir = queroAssistir;
    }

    public Usuario map(Object convertToDTO) {
        return null;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", assistindo=" + assistindo +
                ", completo=" + completo +
                ", queroAssistir=" + queroAssistir +
                '}';
    }
}
