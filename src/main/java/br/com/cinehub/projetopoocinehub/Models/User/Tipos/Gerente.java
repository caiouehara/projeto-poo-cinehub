// Gerente.java
package br.com.cinehub.projetopoocinehub.Models.User.Tipos;

import br.com.cinehub.projetopoocinehub.Models.User.Usuario;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Gerente extends Usuario {

    public Gerente() {
        super();
        this.tipoDeUsuario = "Gerente";
    }

    @JsonCreator
    public Gerente(
            @JsonProperty("nome") String nome,
            @JsonProperty("email") String email,
            @JsonProperty("senha") String senha
    ) {
        super(nome, email, senha, "Gerente");
    }

    // Adicione métodos específicos do Gerente, se necessário
}
