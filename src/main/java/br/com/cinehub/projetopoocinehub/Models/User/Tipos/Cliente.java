// Cliente.java
package br.com.cinehub.projetopoocinehub.Models.User.Tipos;

import br.com.cinehub.projetopoocinehub.Models.User.Usuario;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cliente extends Usuario {
    private double gastoAluguel = 0;
    private double gastoCompra = 0;
    private double gastoTotal = 0;

    public Cliente() {
        super();
        this.tipoDeUsuario = "Cliente";
    }

    @JsonCreator
    public Cliente(
            @JsonProperty("nome") String nome,
            @JsonProperty("email") String email,
            @JsonProperty("senha") String senha,
            @JsonProperty("gastoAluguel") double gastoAluguel,
            @JsonProperty("gastoCompra") double gastoCompra,
            @JsonProperty("gastoTotal") double gastoTotal
    ) {
        super(nome, email, senha, "Cliente");
        this.gastoAluguel = gastoAluguel;
        this.gastoCompra = gastoCompra;
        this.gastoTotal = gastoTotal;
    }
}
