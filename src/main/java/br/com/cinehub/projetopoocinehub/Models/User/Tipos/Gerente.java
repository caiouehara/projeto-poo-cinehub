// Gerente.java
/**
 * Classe que representa o tipo de usuário Gerente no sistema.
 * Um Gerente é responsável por funções administrativas e possui permissões
 * diferenciadas em comparação aos outros usuários.
 *
 * <p>Esta classe herda da classe {@link Usuario} e define o tipo de usuário
 * como "Gerente".</p>
 *
 * @see Usuario
 */
package br.com.cinehub.projetopoocinehub.Models.User.Tipos;

import br.com.cinehub.projetopoocinehub.Models.User.Usuario;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Gerente extends Usuario {

    /**
     * Construtor padrão que inicializa o tipo de usuário como "Gerente".
     */
    public Gerente() {
        super();
        this.tipoDeUsuario = "Gerente";
    }

    /**
     * Construtor que inicializa os atributos do Gerente.
     *
     * @param nome  Nome do gerente.
     * @param email Email do gerente.
     * @param senha Senha do gerente.
     */
    @JsonCreator
    public Gerente(
            @JsonProperty("nome") String nome,
            @JsonProperty("email") String email,
            @JsonProperty("senha") String senha
    ) {
        super(nome, email, senha, "Gerente");
    }

}
