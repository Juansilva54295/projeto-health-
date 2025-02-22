package controle;


import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/listarelatorioServlet")
public class RelatorioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	   request.setCharacterEncoding("UTF-8");

        // Recebe dados do formulário
        String codpc = request.getParameter("codpc");
        String leito = request.getParameter("leito");
        String idade = request.getParameter ("idade");
        String dataNascimento = request.getParameter("dataNascimento");
        String diag = request.getParameter("diag");
        String cid = request.getParameter("cid");
        String medic = request.getParameter("medic");
        String cuid = request.getParameter("cuid");
        String histmed = request.getParameter("histmed");
        String prog = request.getParameter("prog");
  
        // Cria o objeto Paciente
        Relatorio relatorio = new Relatorio();
        relatorio.setCodpc(codpc);
        relatorio.setLeito(leito);
        relatorio.setIdade(idade);
        relatorio.setDataNascimento(dataNascimento);
        relatorio.setDiag(diag);
        relatorio.setCid(cid);
        relatorio.setMedic(medic);
        relatorio.setCuid(cuid);
        relatorio.setHistmed(histmed);
        relatorio.setProg(prog);

        // Salva no banco de dados
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        try {
			relatorioDAO.cadastrarRelatorio(relatorio);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        response.setContentType("text/html;charset=UTF-8");
        
        
        request.getRequestDispatcher("Principal.html").forward(request, response);
        

    }
    
}

