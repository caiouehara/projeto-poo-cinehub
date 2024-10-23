package br.com.cinehub.projetopoocinehub.Models;

public class Login {
    public static void login(String email, String senha) {
        if(validarLogin(email, senha)) {
            System.out.println("Login efetuado com sucesso!");
        }
    }
    public static boolean validarLogin(String email, String senha) {
        Cadastro cadastrado = new Cadastro();
        for (int i = 0; i<cadastrado.listaClientes.size(); i++) {
            if (cadastrado.listaClientes.get(i).getEmail().equals(email) && cadastrado.listaClientes.get(i).getSenha().equals(senha)) {
                return true;
            }

        }
        for (int i =0; i<cadastrado.listaGerentes.size(); i++) {
            if (cadastrado.listaGerentes.get(i).getEmail().equals(email) && cadastrado.listaGerentes.get(i).getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }
}
