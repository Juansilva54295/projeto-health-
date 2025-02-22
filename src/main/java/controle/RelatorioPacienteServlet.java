package controle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/relatorioPaciente")
public class RelatorioPacienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String codpcParam = request.getParameter("codpc");
        String action = request.getParameter("action"); // Novo parâmetro para determinar o destino

        if (codpcParam == null || codpcParam.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID do paciente ausente.");
            return;
        }

        int codpc;
        try {
            codpc = Integer.parseInt(codpcParam);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID do paciente inválido.");
            return;
        }

        // Continue com a lógica de obtenção dos dados do paciente...
        String nomePaciente = "";
        String cpf = "";
        String leito = "";
        String idade = "";
        String dataNascimento = "";
        String diag = "";
        String cid = "";
        String medic = "";
        String cuid = "";
        String histmed = "";
        String prog = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthplus", "root", "12345");

            String sql = "SELECT p.nome, p.cpf, r.leito, r.idade_paci, r.datanasc, r.diagnostico, r.cid, r.medicamentos, r.cuidados, r.hist_medico, r.prognostico " +
                    "FROM paciente p " +
                    "JOIN relatorio r ON p.id = r.id_paciente " +
                    "WHERE p.id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codpc);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                nomePaciente = rs.getString("nome");
                cpf = rs.getString("cpf");
                leito = rs.getString("leito");
                idade = rs.getString("idade_paci");
                dataNascimento = rs.getString("datanasc");
                diag = rs.getString("diagnostico");
                cid = rs.getString("cid");
                medic = rs.getString("medicamentos");
                cuid = rs.getString("cuidados");
                histmed = rs.getString("hist_medico");
                prog = rs.getString("prognostico");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("nomePaciente", nomePaciente);
        request.setAttribute("cpf", cpf);
        request.setAttribute("idade", idade);
        request.setAttribute("leito", leito);
        request.setAttribute("dataNascimento", dataNascimento);
        request.setAttribute("diagnostico", diag);
        request.setAttribute("cid", cid);
        request.setAttribute("medicamentos", medic);
        request.setAttribute("cuidados", cuid);
        request.setAttribute("historicoMedico", histmed);
        request.setAttribute("prognostico", prog);

        // Encaminha para a página apropriada com base no parâmetro 'action'
        if ("editar".equals(action)) {
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("relatorio.jsp").forward(request, response);
        }
    }
}
