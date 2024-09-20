package br.edu.ufape.myufapeanime.myufapeanime.dto.usuario;

public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private boolean isAdm;

    public UsuarioDTO() {}

    public UsuarioDTO(Long id, String nome, String email, String senha, boolean isAdm) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.isAdm = isAdm;
    }

    public boolean isAdm(){
        return isAdm;
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

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setAdm(boolean adm) {
        isAdm = adm;
    }
}
