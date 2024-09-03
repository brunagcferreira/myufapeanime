package br.edu.ufape.myufapeanime.myufapeanime.negocio.basica;


import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    @ManyToMany
    private List<Anime> assistindo;
    @ManyToMany
    private List<Anime> completo;
    @ManyToMany
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

    
}
