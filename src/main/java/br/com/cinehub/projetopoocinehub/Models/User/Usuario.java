/**
 * Classe abstrata que representa um usuário do sistema.
 * Essa classe utiliza anotações do Jackson para definir a serialização e
 * desserialização baseadas no tipo de usuário.
 *
 * <p>Os tipos de usuários podem ser:
 * <ul>
 *   <li>{@link Cliente}</li>
 *   <li>{@link Gerente}</li>
 * </ul>
 *
 * @see Cliente
 * @see Gerente
 */

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
      /** Nome do usuário. */
    protected String nome;
    /** E-mail do usuário. */
    protected String email;
    /** Senha do usuário. */
    protected String senha;
    /** Tipo de usuário (ex.: "Cliente", "Gerente"). */
    protected String tipoDeUsuario;

    /**
     * Construtor padrão.
     */
    // Construtores
    public Usuario() {
    }
    
    /**
     * Construtor que inicializa os atributos do usuário.
     *
     * @param nome          o nome do usuário.
     * @param email         o e-mail do usuário.
     * @param senha         a senha do usuário.
     * @param tipoDeUsuario o tipo de usuário.
     */
    public Usuario(String nome, String email, String senha, String tipoDeUsuario) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoDeUsuario = tipoDeUsuario;
    }

    // Getters e Setters
    /**
     * Obtém o nome do usuário.
     *
     * @return o nome do usuário.
     */
    @JsonProperty("nome")
    public String getNome() {
        return nome;
    }
    /**
     * Obtém o e-mail do usuário.
     *
     * @return o e-mail do usuário.
     */
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }
    /**
     * Obtém a senha do usuário.
     *
     * @return a senha do usuário.
     */
    @JsonProperty("senha")
    public String getSenha() {
        return senha;
    }
     /**
     * Obtém o tipo de usuário.
     *
     * @return o tipo de usuário.
     */
    @JsonProperty("tipoDeUsuario")
    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }
}
