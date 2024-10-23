package br.com.cinehub.projetopoocinehub.Models;

public class Login {
    public static String usuarioLogado = null; //Controle se eh um cliente ou um gerente que está logado
    public static void login(String email, String senha, Cadastro cadastro) {
        if(validarLoginCliente(email, senha, cadastro)) {
            System.out.println("Logado como cliente!");
            usuarioLogado = "Cliente";
        }
        else if(validarLoginGerente(email, senha, cadastro)) {
            System.out.println("Logado como gerente!");
            usuarioLogado = "Gerente";
        }
        else{
            System.out.println("Dados inválidos!");
        }
    }
    public static boolean validarLoginCliente(String email, String senha, Cadastro cadastro) {
        for (int i = 0; i<cadastro.listaClientes.size(); i++) {
            if (cadastro.listaClientes.get(i).getEmail().equals(email) && cadastro.listaClientes.get(i).getSenha().equals(senha)) {
                return true;
            }

        }
        return false;
    }
    public static boolean validarLoginGerente(String email, String senha, Cadastro cadastro) {
        for (int i =0; i<cadastro.listaGerentes.size(); i++) {
            if (cadastro.listaGerentes.get(i).getEmail().equals(email) && cadastro.listaGerentes.get(i).getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }
}
