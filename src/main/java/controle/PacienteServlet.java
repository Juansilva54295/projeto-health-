package controle;
import java.io.IOException;

import java.sql.Date;
import java.sql.SQLException;
import controle.Paciente;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PacienteServlet")
public class PacienteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

               request.setCharacterEncoding("UTF-8"); 
        // Recebe dados do formulário
        String nome = request.getParameter("nome");
        String idade = request.getParameter("idade");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
        String rg = request.getParameter("rg");
        String dataNascimento = request.getParameter("dataNascimento");
        String senha = request.getParameter("senha");
  
        
        Paciente paciente = new Paciente();
        paciente.setNome(nome);
        paciente.setIdade(idade);
        paciente.setEmail(email);
        paciente.setCpf(cpf);
        paciente.setRg(rg);
        System.out.println("Data de Nascimento Recebida: " + dataNascimento);
        paciente.setDataNascimento(Date.valueOf(dataNascimento));
        paciente.setSenha(senha);

   
        PacienteDAO pacienteDAO = new PacienteDAO();
        try {
            pacienteDAO.cadastrarPaciente(paciente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        response.setContentType("text/html;charset=UTF-8");
        
     
        request.getRequestDispatcher("Principal.html").forward(request, response);
    }
}
