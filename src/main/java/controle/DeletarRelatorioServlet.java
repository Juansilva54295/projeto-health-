package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeletarRelatorioServlet")
public class DeletarRelatorioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codpc = request.getParameter("codpc"); // Recebe o codpc do formulário

        if (codpc != null && !codpc.isEmpty()) {
            try {
                int id = Integer.parseInt(codpc); // Converte para int
                RelatorioDAO relatorioDAO = new RelatorioDAO();
                boolean isDeleted = relatorioDAO.deletarRelatorio(id);

                if (isDeleted) {
                    response.sendRedirect("Principal.html?msg=Relatório excluído com sucesso!");
                } else {
                    response.sendRedirect("erro.jsp?msg=Erro ao excluir relatório.");
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("erro.jsp?msg=ID inválido fornecido.");
            }
        } else {
            response.sendRedirect("erro.jsp?msg=ID do paciente inválido.");
        }
    }
}



