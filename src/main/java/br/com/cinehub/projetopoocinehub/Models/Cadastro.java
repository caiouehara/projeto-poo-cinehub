package br.com.cinehub.projetopoocinehub.Models;
import java.util.ArrayList;

public class Cadastro {
    ArrayList<Gerente> listaGerentes;
    ArrayList<Cliente> listaClientes;

    public Cadastro()
    {
        listaGerentes = new ArrayList<>();
        listaClientes = new ArrayList<>();
    }
    public void adicionarNaListaGerente(Gerente gerente)
    {
        listaGerentes.add(gerente);
    }
    public void cadastroGerente(String nome, String email, String senha)
    {
        //Precisa verificar se é um gerente que está adicionando
        if(Login.usuarioLogado.equals("Gerente")) {
            Gerente novoGerente = new Gerente(nome, email, senha); //Instancia um novo gerente
            if (verificarCadastroGerente(email, novoGerente)) {
                adicionarNaListaGerente(novoGerente); //Adiciona o novo gerente na lista de gerentes
                System.out.println("Novo gerente cadastrado com sucesso!");
            }
        }
        else{
            System.out.println("Você precisa ser um gerente para cadastrar outro!");
        }
    }
    public void cadastroCliente(String nome, String email, String senha)
    {
        Cliente novoCliente = new Cliente(nome,email,senha);
        if(verificarCadastroCliente(email,novoCliente)) {
            listaClientes.add(novoCliente); //Adiciona o novo cliente na lista de clientes
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
    public ArrayList<Cliente> getListaClientes() { //Metodo de retorno da lista completa de clientes cadastrados
        return listaClientes;
    }
    public ArrayList<Gerente> getListaGerentes() { //Metodo de retorno da lista completa de gerentes cadastrados
        return listaGerentes;
    }

}
