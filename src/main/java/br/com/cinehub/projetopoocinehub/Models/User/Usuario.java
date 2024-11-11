// Usuario.java
package br.com.cinehub.projetopoocinehub.Models.User;

import br.com.cinehub.projetopoocinehub.Models.User.Tipos.Cliente;
import br.com.cinehub.projetopoocinehub.Models.User.Tipos.Gerente;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "tipoDeUsuario"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Cliente.class, name = "Cliente"),
    @JsonSubTypes.Type(value = Gerente.class, name = "Gerente")
})
public abstract class Usuario {
    protected String nome;
    protected String email;
    protected String senha;
    protected String tipoDeUsuario;

    // Construtores
    public Usuario() {
    }

    public Usuario(String nome, String email, String senha, String tipoDeUsuario) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoDeUsuario = tipoDeUsuario;
    }

    // Getters e Setters
    @JsonProperty("nome")
    public String getNome() {
        return nome;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("senha")
    public String getSenha() {
        return senha;
    }

    @JsonProperty("tipoDeUsuario")
    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }
}
