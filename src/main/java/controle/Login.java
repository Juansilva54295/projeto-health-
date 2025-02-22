package controle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
        super();
      
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		  String usuario = request.getParameter("usuario");
		   String senha = request.getParameter("senha");
		
	//validando credenciais
		
		    if(usuario.equals("Juan15400") && senha.equals("15400")){
			
	//cria uma sessão para o usuario
		 
			HttpSession session = request.getSession();
			session.setAttribute("usuario", usuario);
			
	//redireciona para a pagina de boas vindas
			
		//	response.sendRedirect("");
			response.sendRedirect("Principal.html");

		}
			
		    else {
		        System.out.println("Login falhou para: " + usuario);
				response.sendRedirect("login.html");


	
		    	
		    }
	}

}
