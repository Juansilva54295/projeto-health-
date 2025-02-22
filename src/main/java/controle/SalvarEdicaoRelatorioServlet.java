package controle;

import java.io.IOException;
import controle.Relatorio;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SalvarEdicaoRelatorioServlet")
public class SalvarEdicaoRelatorioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
        String idade = request.getParameter("idade");
        String leito = request.getParameter("leito");
        String dataNascimento = request.getParameter("dataNascimento");
        String diag = request.getParameter("diagnostico");
        String cid = request.getParameter("cid");
        String medic = request.getParameter("medicamentos");
        String cuid = request.getParameter("cuidados");
        String histmed = request.getParameter("hist_medico");
        String prog = request.getParameter("prognostico");
        String codpc = request.getParameter("codpc");

        // Verifique se o ID do relatório foi fornecido
        if (codpc == null || codpc.isEmpty()) {
            throw new IllegalArgumentException("ID do relatório não fornecido.");
        }

        try {
            Relatorio relatorio = new Relatorio(codpc, idade, leito, dataNascimento, diag, cid, medic, cuid, histmed, prog);
            RelatorioDAO dao = new RelatorioDAO();
            boolean atualizado = dao.EditarRelatorio(relatorio);

            if (atualizado == true) {
                response.sendRedirect("RelatorioPacienteServlet?codpc=" + codpc);  // Redirecionar para o relatório atualizado
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar o relatório.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro no processo de edição.");
        }
    }
}
