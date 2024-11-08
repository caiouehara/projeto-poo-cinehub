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
    public void cadastroGerente(String email, String senha, String nome)
    {
        //Precisa verificar se é um gerente que está adicionando
        if(Login.usuarioLogado.equals("Gerente")) {
            Gerente novoGerente = new Gerente(nome, email, senha); //Instancia um novo gerente
            if (verificaCadastroGerente(novoGerente)) {
                listaGerentes.add(novoGerente); //Adiciona o novo gerente na lista de gerentes, se ele já não estiver na lista
                System.out.println("Novo gerente cadastrado com sucesso!");
            }
            else{
                System.out.println("Esse gerente já tem cadastro!");
            }
        }
        else{
            System.out.println("Você precisa ser um gerente para cadastrar outro!");
        }
    }
    public void cadastroCliente(String nome, String email, String senha)
    {
        Cliente novoCliente = new Cliente(nome,email,senha);
        if(verificaCadastroCliente(novoCliente)) {
            listaClientes.add(novoCliente); //Adiciona o novo cliente na lista de clientes
            System.out.println("Novo Cliente cadastrado com sucesso!");
        }
        else{
            System.out.println("Esse Cliente já tem cadastro!");
        }
    }
    public void removeCliente(Cliente cliente){
        //Verifica se o cliente a ser removido existe e indentifica o index dele na lista
        if(!verificaCadastroCliente(cliente)) {
            listaClientes.remove(cliente); //Remove o cliente da lista de acordo com o index dele na lista
        }
    }
    public void removeGerente(Gerente gerente){
        //Verifica se o gerente a ser removido existe
        if(!verificaCadastroGerente(gerente)) {
            listaGerentes.remove(gerente); //Remove o gerente de acordo com o index dele na lista
        }
    }
    public boolean verificaCadastroGerente(Gerente gerente){
        for(int i = 0; i<listaGerentes.size(); i++)
        {
            if(listaGerentes.get(i) == gerente) {
                return false;
            }
        }
        return true;
    }
    //Percorre a lista para verificar se o cliente está cadastrado de acordo com o email
    public boolean verificaCadastroCliente(Cliente cliente){
        for(int i = 0; i<listaClientes.size(); i++)
        {
            if(listaClientes.get(i) == cliente) {
                return false; //Cliente tá na lista de cadastro
            }
        }
        return true; //Cliente não tem cadastro
    }
    //Vai ser usado na parte de arquivos
    public ArrayList<Cliente> getListaClientes() { //Metodo de retorno da lista completa de clientes cadastrados
        return listaClientes;
    }
    public ArrayList<Gerente> getListaGerentes() { //Metodo de retorno da lista completa de gerentes cadastrados
        return listaGerentes;
    }
    //Metodos que serão usados junto com a parte de arquivos para preencher a lista de usuarios
    public void setListaClientes(ArrayList<Cliente> listaClientes) {
         this.listaClientes = listaClientes;
    }
    public void setListaGerentes(ArrayList<Gerente> listaGerentes) {
        this.listaGerentes = listaGerentes;
    }

}
