/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

/**
 *
 * @author leo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Configurações de conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/BANCODEDADOSBR2"; // Substitua pelo nome do seu banco
    private static final String USER = "root"; // Substitua pelo usuário do banco
    private static final String PASSWORD = "leo98672810"; // Substitua pela senha do banco

    // Método para retornar a conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        try {
            // Retorna uma conexão com o banco de dados
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw e; // Relança a exceção para que o erro seja tratado adequadamente
        }
    }
}

