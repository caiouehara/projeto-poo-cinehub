package br.com.cinehub.projetopoocinehub.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A classe {@code Filme} representa um filme com diversas propriedades como título, ano, sinopse, etc.
 * <p>
 * Esta classe utiliza as anotações do Jackson para permitir a serialização e desserialização de objetos
 * para JSON. O construtor é anotado com {@code @JsonCreator}, e cada parâmetro é anotado com
 * {@code @JsonProperty} para mapear as propriedades do JSON para os campos correspondentes da classe.
 * Isso facilita o processo de conversão entre JSON e objetos Java, garantindo que os dados sejam
 * corretamente interpretados e atribuídos.
 * </p>
 */
public class Filme {
    private int anoFilme;
    private String tituloFilme;
    private String sinopseFilme;
    private double avaliacaoFilme;
    private double duracaoFilme;
    private double precoFilmeCompra;
    private double precoFilmeAluguel;
    private int diasAluguel;
    private int usuariosAvaliaram;
    private String imagem;
    private ArrayList<Comentarios> comentarios;
    

    /**
     * Construtor da classe {@code Filme} utilizado pelo Jackson para desserialização do JSON.
     * <p>
     * A anotação {@code @JsonCreator} indica ao Jackson que este construtor deve ser usado para
     * criar instâncias da classe durante a desserialização. Cada parâmetro é anotado com
     * {@code @JsonProperty}, especificando o nome da propriedade no JSON que corresponde ao
     * parâmetro. Isso garante que cada campo seja corretamente mapeado a partir do JSON.
     * </p>
     *
     * @param anoFilme          o ano de lançamento do filme
     * @param tituloFilme       o título do filme
     * @param sinopseFilme      a sinopse do filme
     * @param avaliacaoFilme    a avaliação do filme
     * @param duracaoFilme      a duração do filme em minutos
     * @param precoFilmeCompra  o preço para compra do filme
     * @param precoFilmeAluguel o preço para aluguel do filme
     * @param diasAluguel       a quantidade de dias para aluguel
     * @param usuariosAvaliaram o número de usuários que avaliaram o filme
     * @param imagem            o caminho ou URL da imagem do filme
     */
    @JsonCreator
    public Filme(
        @JsonProperty("anoFilme") int anoFilme,
        @JsonProperty("tituloFilme") String tituloFilme,
        @JsonProperty("sinopseFilme") String sinopseFilme,
        @JsonProperty("avaliacaoFilme") double avaliacaoFilme,
        @JsonProperty("duracaoFilme") double duracaoFilme,
        @JsonProperty("precoFilmeCompra") double precoFilmeCompra,
        @JsonProperty("precoFilmeAluguel") double precoFilmeAluguel,
        @JsonProperty("diasAluguel") int diasAluguel,
        @JsonProperty("usuariosAvaliaram") int usuariosAvaliaram,
        @JsonProperty("imagem") String imagem
    ) {
        this.anoFilme = anoFilme;
        this.tituloFilme = tituloFilme;
        this.sinopseFilme = sinopseFilme;
        this.avaliacaoFilme = avaliacaoFilme;
        this.duracaoFilme = duracaoFilme;
        this.precoFilmeCompra = precoFilmeCompra;
        this.precoFilmeAluguel = precoFilmeAluguel;
        this.diasAluguel = diasAluguel;
        this.usuariosAvaliaram = usuariosAvaliaram;
        this.imagem = imagem;
        comentarios = new ArrayList<>();
    }

    // Getters e Setters

    /**
     * Obtém o ano de lançamento do filme.
     *
     * @return o ano do filme
     */
    public int getAnoFilme() {
        return anoFilme;
    }

    /**
     * Define o ano de lançamento do filme.
     *
     * @param anoFilme o ano do filme
     */
    public void setAnoFilme(int anoFilme) {
        this.anoFilme = anoFilme;
    }

    /**
     * Obtém o título do filme.
     *
     * @return o título do filme
     */
    public String getTituloFilme() {
        return tituloFilme;
    }

    /**
     * Define o título do filme.
     *
     * @param tituloFilme o título do filme
     */
    public void setTituloFilme(String tituloFilme) {
        this.tituloFilme = tituloFilme;
    }

    /**
     * Obtém a sinopse do filme.
     *
     * @return a sinopse do filme
     */
    public String getSinopseFilme() {
        return sinopseFilme;
    }

    /**
     * Define a sinopse do filme.
     *
     * @param sinopseFilme a sinopse do filme
     */
    public void setSinopseFilme(String sinopseFilme) {
        this.sinopseFilme = sinopseFilme;
    }

    /**
     * Obtém a avaliação do filme.
     *
     * @return a avaliação do filme
     */
    public double getAvaliacaoFilme() {
        return avaliacaoFilme;
    }

    /**
     * Define a avaliação do filme.
     *
     * @param avaliacaoFilme a avaliação do filme
     */
    public void setAvaliacaoFilme(double avaliacaoFilme) {
        this.avaliacaoFilme = avaliacaoFilme;
    }

    /**
     * Obtém a duração do filme em minutos.
     *
     * @return a duração do filme
     */
    public double getDuracaoFilme() {
        return duracaoFilme;
    }

    /**
     * Define a duração do filme em minutos.
     *
     * @param duracaoFilme a duração do filme
     */
    public void setDuracaoFilme(double duracaoFilme) {
        this.duracaoFilme = duracaoFilme;
    }

    /**
     * Obtém o preço para compra do filme.
     *
     * @return o preço de compra do filme
     */
    public double getPrecoFilmeCompra() {
        return precoFilmeCompra;
    }

    /**
     * Define o preço para compra do filme.
     *
     * @param precoFilmeCompra o preço de compra do filme
     */
    public void setPrecoFilmeCompra(double precoFilmeCompra) {
        this.precoFilmeCompra = precoFilmeCompra;
    }

    /**
     * Obtém o preço para aluguel do filme.
     *
     * @return o preço de aluguel do filme
     */
    public double getPrecoFilmeAluguel() {
        return precoFilmeAluguel;
    }

    /**
     * Define o preço para aluguel do filme.
     *
     * @param precoFilmeAluguel o preço de aluguel do filme
     */
    public void setPrecoFilmeAluguel(double precoFilmeAluguel) {
        this.precoFilmeAluguel = precoFilmeAluguel;
    }

    /**
     * Obtém a quantidade de dias para aluguel do filme.
     *
     * @return os dias de aluguel
     */
    public int getDiasAluguel() {
        return diasAluguel;
    }

    /**
     * Define a quantidade de dias para aluguel do filme.
     *
     * @param diasAluguel os dias de aluguel
     */
    public void setDiasAluguel(int diasAluguel) {
        this.diasAluguel = diasAluguel;
    }

    /**
     * Obtém o número de usuários que avaliaram o filme.
     *
     * @return o número de usuários que avaliaram
     */
    public int getUsuariosAvaliaram() {
        return usuariosAvaliaram;
    }

    /**
     * Define o número de usuários que avaliaram o filme.
     *
     * @param usuariosAvaliaram o número de usuários que avaliaram
     */
    public void setUsuariosAvaliaram(int usuariosAvaliaram) {
        this.usuariosAvaliaram = usuariosAvaliaram;
    }

    /**
     * Obtém o caminho ou URL da imagem do filme.
     *
     * @return a imagem do filme
     */
    public String getImagem() {
        return imagem;
    }

    /**
     * Define o caminho ou URL da imagem do filme.
     *
     * @param imagem a imagem do filme
     */
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    /**
     * Obtém o título do filme.
     *
     * @return o título do filme
     */
    public String getTitulo() {
        return tituloFilme;
    }

    //metodo para imprimir uma lista de filmes
    public static void imprimirFilmes(ArrayList<Filme> filmes) {
        for(int i = 0; i < filmes.size(); i++) {
            System.out.println("Título: "+filmes.get(i).getTituloFilme());
            System.out.println("Ano: " + filmes.get(i).getAnoFilme());
            System.out.println("Sinopse: "+filmes.get(i).getSinopseFilme());
            System.out.println("Preço aluguel: "+filmes.get(i).getPrecoFilmeAluguel());
            System.out.println("Preço compra: "+filmes.get(i).getPrecoFilmeCompra());
            System.out.println("Dias de aluguel: "+filmes.get(i).getDiasAluguel());
            System.out.println();
        }

    }

    //método imprime dados de um filme em específico
    public static void imprimirFilme(Filme filme) {

            System.out.println("Título: "+filme.getTituloFilme());
            System.out.println("Ano: " + filme.getAnoFilme());
            System.out.println("Sinopse: "+filme.getSinopseFilme());
            System.out.println("Preço aluguel: "+filme.getPrecoFilmeAluguel());
            System.out.println("Preço compra: "+filme.getPrecoFilmeCompra());
            System.out.println("Dias de aluguel: "+filme.getDiasAluguel());
    }


    //método para imprimir o catálogo de filmes disponíveis ao cliente
    public static void imprimirCatalogo(ArrayList<Filme> filmes) {
    	for (int i = 0; i<filmes.size(); i++) {
    		System.out.println("==================================================================================================================");
            System.out.println("                                             Opção " + (i+1) + "                                                        ");
            System.out.println("==================================================================================================================");
            Filme.imprimirFilme(filmes.get(i));
    	}
    }
    

    //método para adicionar comentário no filme
    public void adicionarComentarioNoFilme(Comentarios comentario) {
        comentarios.add(comentario);
    }


    //método para imprimir comentário no filme
    public void imprimirComentario() {
        for (int i = 0; i<comentarios.size();i++) {
            System.out.println("Comentário: " + comentarios.get(i).getComentario());
            System.out.println("Autor: " + comentarios.get(i).getAutor());
            System.out.println("Likes: " + comentarios.get(i).getQuantidadeLikes());
            System.out.println("Deslikes: " + comentarios.get(i).getQuantidadeDeslikes());
        }
    }
}
