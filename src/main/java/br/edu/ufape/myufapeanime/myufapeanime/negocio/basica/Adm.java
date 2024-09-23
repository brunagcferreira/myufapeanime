package br.edu.ufape.myufapeanime.myufapeanime.negocio.basica;

import jakarta.persistence.Entity;

/**
 * Classe que representa um adm no sistema
 * o adm é responsável por cadastrar e editar os animes
 *
 * Relacionamento:
 *  como ele tem uma herança de usuário
 *  então ele tem todos os relacionamentos como lista de animes e avaliações
 *
 * @author RaylandsonCesário
 */


@Entity
public class Adm extends Usuario {
    public Adm(Usuario usuario) {
    }

    public Adm() {
    }
}
