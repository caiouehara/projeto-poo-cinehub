package br.com.cinehub.projetopoocinehub.Controllers;

import br.com.cinehub.projetopoocinehub.Models.Aluguel.Aluguel;
import br.com.cinehub.projetopoocinehub.Models.Aluguel.AluguelModel;
import br.com.cinehub.projetopoocinehub.Models.User.CadastroModel;
import br.com.cinehub.projetopoocinehub.Models.Filmes.Filme;
import br.com.cinehub.projetopoocinehub.Models.Filmes.FilmesModel;
import br.com.cinehub.projetopoocinehub.Models.User.Tipos.Cliente;
import br.com.cinehub.projetopoocinehub.Models.User.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "cliente", value = "/cliente")
public class ClienteController extends HttpServlet {

    @Override
    public void init() {
        // Initialization logic if needed
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the session and check if the user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            // Redirect to the login page if the user is not logged in
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // Get the logged-in user from the session
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // Fetch the client details using the user's email
        Cliente cliente = CadastroModel.buscarClienteEmail(usuario.getEmail());
        if (cliente == null) {
            // If the client is not found, redirect to the error page
            response.sendRedirect(request.getContextPath() + "/error");
            return;
        }

        // Fetch the client's rentals
        List<Aluguel> alugueisDoCliente = AluguelModel.buscarAlugueisPorCliente(cliente.getEmail());

        // Prepare the list of rented movies
        List<Filme> filmesAlugados = new ArrayList<>();
        for (Aluguel aluguel : alugueisDoCliente) {
            Filme filme = FilmesModel.buscarFilmePorId(aluguel.getFilmeId());
            if (filme != null) {
                filmesAlugados.add(filme);
            }
        }

        // Set attributes for the JSP page
        request.setAttribute("cliente", cliente);
        request.setAttribute("filmesAlugados", filmesAlugados);
        request.setAttribute("alugueisDoCliente", alugueisDoCliente); // If you need rental dates

        // Forward to the profile JSP page
        request.getRequestDispatcher("/jsp/perfil.jsp").forward(request, response);
    }
}
