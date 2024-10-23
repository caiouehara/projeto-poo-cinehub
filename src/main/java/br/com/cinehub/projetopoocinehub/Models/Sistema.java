package br.com.cinehub.projetopoocinehub.Models;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Sistema {
    ArrayList<Gerente> listaGerentes;
    ArrayList<Cliente> listaClientes;

    public Sistema()
    {
        listaGerentes = new ArrayList<>();
        listaClientes = new ArrayList<>();
    }
    public void cadastroGerente(String nome, String email, String senha)
    {
        //Precisa verificar se é um gerente que está adicionando
        Gerente novoGerente = new Gerente(nome,email,senha); //Instancia um novo gerente
        if(verificarCadastroGerente(email,novoGerente)) {
            listaGerentes.add(novoGerente);
            System.out.println("Novo gerente cadastrado com sucesso!");
        }
    }
    public void cadastroCliente(String nome, String email, String senha)
    {
        Cliente novoCliente = new Cliente(nome,email,senha);
        if(verificarCadastroCliente(email,novoCliente)) {
            listaClientes.add(novoCliente);
            System.out.println("Novo Cliente cadastrado com sucesso!");
        }
    }
    public boolean verificarCadastroGerente(String email, Gerente novoGerente){
        for(int i = 0; i<listaGerentes.size(); i++)
        {
            if(novoGerente.getEmail().equals(listaGerentes.get(i).getEmail()))
                return false;
        }
        return true;
    }
    public boolean verificarCadastroCliente(String email, Cliente novoCliente){
        for(int i = 0; i<listaClientes.size(); i++)
        {
            if(novoCliente.getEmail().equals(listaClientes.get(i).getEmail()))
                return false;
        }
        return true;
    }

}