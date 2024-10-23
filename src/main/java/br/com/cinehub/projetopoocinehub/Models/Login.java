package br.com.cinehub.projetopoocinehub.Models;

public class Login {
    public static void login(String email, String senha, Cadastro cadastro) {
        if(validarLogin(email, senha, cadastro)) {
            System.out.println("Login efetuado com sucesso!");
        }
        else{
            System.out.println("Dados inválidos!");
        }
    }
    public static boolean validarLogin(String email, String senha, Cadastro cadastro) {
        for (int i = 0; i<cadastro.listaClientes.size(); i++) {
            if (cadastro.listaClientes.get(i).getEmail().equals(email) && cadastro.listaClientes.get(i).getSenha().equals(senha)) {
                return true;
            }

        }
        for (int i =0; i<cadastro.listaGerentes.size(); i++) {
            if (cadastro.listaGerentes.get(i).getEmail().equals(email) && cadastro.listaGerentes.get(i).getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }
}
