package br.com.cinehub.projetopoocinehub.Models;

public class Login {
    public static void login(String email, String senha) {
        if(validarLogin(email, senha)) {
            System.out.println("Login efetuado com sucesso!");
        }
    }
    public static boolean validarLogin(String email, String senha) {
        Sistema cadastrado = new Sistema();
        for (int i = 0; i<cadastrado.listaClientes.size(); i++) {
            if (cadastrado.listaClientes.get(i).getEmail().equals(email)) {
                return true;
            }

        }
        for (int i =0; i<cadastrado.listaGerentes.size(); i++) {
            if (cadastrado.listaGerentes.get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
