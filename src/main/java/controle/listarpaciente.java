package controle;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controle.Paciente;

@WebServlet("/ListaPacientesServlet")
public class listarpaciente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ListarpacienteDAO listarpacienteDAO = new ListarpacienteDAO();

        try {
            // Obtendo todos os pacientes do banco de dados
            List<Paciente> pacientes = listarpacienteDAO.listarPacientes();

            // Verificando se a lista de pacientes está vazia
            if (pacientes.isEmpty()) {
                System.out.println("Nenhum paciente encontrado.");
            } else {
                System.out.println("Pacientes encontrados: ");
                for (Paciente paciente : pacientes) {
                    System.out.println(paciente.getNome());
                }
            }

            // Obtendo o parâmetro de pesquisa, se presente
            String searchQuery = request.getParameter("searchQuery");

            // Filtrando a lista de pacientes com base no parâmetro de pesquisa
            if (searchQuery != null && !searchQuery.isEmpty()) {
                pacientes = pacientes.stream()
                        .filter(paciente -> paciente.getNome().toLowerCase().contains(searchQuery.toLowerCase()) ||
                                String.valueOf(paciente.getId()).equals(searchQuery))
                        .collect(Collectors.toList());
                System.out.println("Pacientes após filtragem: " + pacientes.size());
            }

            // Atribuindo a lista filtrada (ou não filtrada) ao request
            request.setAttribute("pacientes", pacientes);

            // Encaminhando para a página JSP
            request.getRequestDispatcher("ListaPacientes.jsp").forward(request, response);

        } catch (Exception e) {
            // Tratando erros
            e.printStackTrace();
            System.out.println("Erro ao listar pacientes: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao listar pacientes.");
        }
    }
}
