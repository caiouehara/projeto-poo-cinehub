package br.com.cinehub.projetopoocinehub.Models.User.Tipos;

import br.com.cinehub.projetopoocinehub.Models.User.Usuario;
import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Gerente extends Usuario {

    // Construtor com parâmetros e anotações para o Jackson
    @JsonCreator
    public Gerente(
            @JsonProperty("nome") String nome,
            @JsonProperty("email") String email,
            @JsonProperty("senha") String senha
    ) {
        super(nome, email, senha);
    }
}
