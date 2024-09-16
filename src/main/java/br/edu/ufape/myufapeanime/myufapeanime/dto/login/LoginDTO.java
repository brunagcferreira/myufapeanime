package br.edu.ufape.myufapeanime.myufapeanime.dto.login;

public class LoginDTO {
    private String email;
    private String senha;

    // Construtor padrão
    public LoginDTO() {}

    // Construtor com parâmetros
    public LoginDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    // Getters e Setters
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
}