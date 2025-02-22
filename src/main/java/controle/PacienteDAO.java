package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;



public class PacienteDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/healthplus"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "12345"; 
        
    private String SQL = "INSERT INTO Paciente (nome, idade, email, cpf, rg, datanascimento, senha) VALUES (?, ?, ?, ?, ?, ?, ?)";

	protected Connection getConnection(){
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL,USER,PASSWORD);		
		}catch (SQLException | ClassNotFoundException e ) {
			e.printStackTrace();
		}
			
		return connection;

	}

    public void cadastrarPaciente(Paciente paciente) throws SQLException {
    	try (Connection connection = getConnection(); //metodo de conexão getConnection
    PreparedStatement stmt = connection.prepareStatement(SQL)){
        

            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getIdade()); 
            stmt.setString(3, paciente.getEmail());
            stmt.setString(4, paciente.getCpf());
            stmt.setString(5, paciente.getRg());
            stmt.setDate(6, paciente.getDataNascimento());
            stmt.setString(7, paciente.getSenha());

            stmt.executeUpdate();
            System.out.println("Paciente cadastrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar paciente: " + e.getMessage());
        }
    }
    }
