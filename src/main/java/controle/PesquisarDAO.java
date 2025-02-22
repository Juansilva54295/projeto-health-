package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PesquisarDAO {
    
    private static final String URL = "jdbc:mysql://localhost:3306/healthplus"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "12345"; 
    
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);        
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // Método para pesquisar pacientes por ID ou Nome
    public List<Paciente> searchPacientes(String searchQuery) {
        List<Paciente> pacientes = new ArrayList<>();
        
        // Verificando se a busca é por ID ou nome
        String sql = "SELECT * FROM paciente WHERE nome LIKE ? OR id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Definindo parâmetros para a busca (nome ou ID)
            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                if (searchQuery.matches("\\d+")) { // Se o searchQuery for numérico (ID)
                    stmt.setInt(2, Integer.parseInt(searchQuery));  // Busca por ID
                    stmt.setString(1, "%"); // Não filtra o nome
                } else {
                    stmt.setString(1, "%" + searchQuery + "%");  // Busca por Nome (parcial)
                    stmt.setInt(2, -1); // Ignora o filtro de ID
                }
                
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Paciente paciente = new Paciente();
                        paciente.setId(rs.getInt("id"));
                        paciente.setNome(rs.getString("nome"));
                        pacientes.add(paciente);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return pacientes;
    }
}
