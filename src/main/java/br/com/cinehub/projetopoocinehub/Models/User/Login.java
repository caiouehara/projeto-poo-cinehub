package br.com.cinehub.projetopoocinehub.Models.User;

public class Login {
    public static boolean validarLoginCliente(String email, String senha, CadastroModel cadastro) {
        return cadastro.getListaClientes().stream()
            .anyMatch(cliente -> cliente.getEmail().equals(email) && cliente.getSenha().equals(senha));
    }

    public static boolean validarLoginGerente(String email, String senha, CadastroModel cadastro) {
        return cadastro.getListaGerentes().stream()
            .anyMatch(gerente -> gerente.getEmail().equals(email) && gerente.getSenha().equals(senha));
    }
}
