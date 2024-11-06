import java.util.ArrayList;
public class TesteFilme {
  
    public static void main(String args[]) {
        ArrayList<Filme> catalogoFilmes = new ArrayList<Filme>();
        Filme shrek = new Filme(2001, "Shrek", "saasa", 7.0, 120.0, 30.0, 7.0, 7,0,"assas");
        catalogoFilmes.add(shrek);
        Filme shrek2 = new Filme(2003, "Shrek 2", "saasa", 7.0, 120.0, 30.0, 7.0, 7,0,"assas");
        catalogoFilmes.add(shrek2);
        Filme shrek3 = new Filme(2006, "Shrek 3", "saasa", 7.0, 120.0, 30.0, 7.0, 7,0,"assas");
        catalogoFilmes.add(shrek3);
        //Menu.filmeMenu(catalogoFilmes);
        Cliente anna = new Cliente("anna", "anna", "anna");
        anna.realizarCompra(shrek);
        anna.realizarAluguel(shrek2);
        anna.realizarAluguel(shrek3);
        anna.getCompra().imprimirFilmesComprados();
        anna.getAluguel().imprimirFilmesAlugados();
        System.out.println("Gasto total aluguel: " + anna.getGastoAluguel());
        System.out.println("Gasto total compra: " + anna.getGastoCompra());

        
    }
}

