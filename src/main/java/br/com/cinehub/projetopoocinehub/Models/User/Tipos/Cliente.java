// Cliente.java
/**
 * Classe que representa o tipo de usuário Cliente no sistema.
 * Um Cliente é responsável por realizar ações como aluguel e compra
 * de filmes, e seus gastos são registrados.
 * 
 * <p>Esta classe herda da classe {@link Usuario} e define o tipo de usuário
 * como "Cliente".</p>
 * 
 * @see Usuario
 */
package br.com.cinehub.projetopoocinehub.Models.User.Tipos;

import br.com.cinehub.projetopoocinehub.Models.User.Usuario;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cliente extends Usuario {
    /** Total gasto em aluguéis pelo cliente. */
    private double gastoAluguel = 0;
    /** Total gasto em compras pelo cliente. */
    private double gastoCompra = 0;
    /** Total geral de gastos do cliente (soma de aluguel e compra). */
    private double gastoTotal = 0;

    /**
     * Construtor padrão que inicializa o tipo de usuário como "Cliente".
     */
    public Cliente() {
        super();
        this.tipoDeUsuario = "Cliente";
    }

    /**
     * Construtor que inicializa os atributos do Cliente.
     * 
     * @param nome         Nome do cliente.
     * @param email        Email do cliente.
     * @param senha        Senha do cliente.
     * @param gastoAluguel Total gasto em aluguéis.
     * @param gastoCompra  Total gasto em compras.
     * @param gastoTotal   Total geral de gastos.
     */
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
