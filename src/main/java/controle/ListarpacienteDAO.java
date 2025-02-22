package controle;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import controle.Paciente;

public class ListarpacienteDAO {
	 private static final String URL = "jdbc:mysql://localhost:3306/healthplus";
	  private static final String USER = "root"; 
	   private static final String PASSWORD = "12345"; 
	    

    // Método para criar e retornar a conexão com o banco de dados
    private Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carregar o driver
            conn = DriverManager.getConnection(URL, USER, PASSWORD); // Estabelecer a conexão
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar: ");
        }
        return conn;
    }

    // Método para listar pacientes
    public List<Paciente> listarPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        
        // Obter a conexão
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT ID, NOME FROM PACIENTE");
             ResultSet rs = stmt.executeQuery()) {

            // Iterar os resultados da consulta 
            while (rs.next()) {
            	   int id = rs.getInt("ID");   // Obtém o ID
                   String nome = rs.getString("NOME"); // Obtém o Nome
                   Paciente paciente = new Paciente(id, nome); // Cria o objeto Paciente
                   pacientes.add(paciente); // Adiciona à lista
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return pacientes;
    }
}
