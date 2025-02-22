package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import controle.Paciente;

public class RelatorioDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/healthplus"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "12345"; 
    
    private String SQL = "INSERT INTO RELATORIO (ID_PACIENTE, LEITO, idade_PACI, datanasc, DIAGNOSTICO, cid, medicamentos, cuidados, hist_medico, prognostico) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String sqlupdate = "UPDATE RELATORIO SET LEITO = ?, idade_PACI = ?, datanasc = ?, DIAGNOSTICO = ?, cid = ?, medicamentos = ?, cuidados = ?, hist_medico = ?, prognostico = ? WHERE ID_PACIENTE = ?";
    private String sqlDelete = "DELETE FROM RELATORIO WHERE ID_PACIENTE = ?";
    
    protected Connection getConnection() {
        Connection connection = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);        
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar no banco");
        }
            
        return connection;
    }

    public void cadastrarRelatorio(Relatorio relatorio) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQL)) {

            stmt.setString(1, relatorio.getCodpc()); // ID_PACIENTE
            stmt.setString(2, relatorio.getLeito());
            stmt.setString(3, relatorio.getIdade());
            stmt.setString(4, relatorio.getDataNascimento());
            stmt.setString(5, relatorio.getDiag());
            stmt.setString(6, relatorio.getCid());
            stmt.setString(7, relatorio.getMedic());
            stmt.setString(8, relatorio.getCuid());
            stmt.setString(9, relatorio.getHistmed());
            stmt.setString(10, relatorio.getProg());

            stmt.executeUpdate();
            System.out.println("Relatório registrado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao registrar relatório: " + e.getMessage());
        }
    }

    public boolean EditarRelatorio(Relatorio relatorio) {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sqlupdate)) {

        	
            stmt.setString(1, relatorio.getIdade());
            stmt.setString(2, relatorio.getLeito());  
            stmt.setString(3, relatorio.getDataNascimento());
            stmt.setString(4, relatorio.getDiag());
            stmt.setString(5, relatorio.getCid());
            stmt.setString(6, relatorio.getMedic());
            stmt.setString(7, relatorio.getCuid());
            stmt.setString(8, relatorio.getHistmed());
            stmt.setString(9, relatorio.getProg());
            stmt.setString(10, relatorio.getCodpc()); // ID_PACIENTE

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; 
        } catch (SQLException e) {
            e.printStackTrace();  
            return false;
        }
    }

    public boolean deletarRelatorio(int id) {
        String sqlDelete = "DELETE FROM relatorio WHERE id_paciente = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(sqlDelete)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
