package br.edu.ufape.myufapeanime.myufapeanime.negocio.basica;

import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class Adm extends Usuario {
    public Adm(String nome, String email, List<Anime> assistindo, List<Anime> completo, List<Anime> queroAssistir) {
        super(nome, email, assistindo, completo, queroAssistir);
    }

    public Adm() {

    }

    public Adm(String nome, String email) {
        setNome(nome);
        setEmail(email);
    }
}
