/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author leo
 */
import connection.DBConnection;
import model.Estadio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadioDAO {

    // Método para inserir um novo estádio na tabela
    public void inserirEstadio(Estadio estadio) throws SQLException {
        String sql = "INSERT INTO Estadio (nome, cidade, capacidade) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, estadio.getNome());
            stmt.setString(2, estadio.getCidade());
            stmt.setInt(3, estadio.getCapacidade());

            stmt.executeUpdate();

            // Recuperar o id gerado
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                estadio.setIdEstadio(rs.getInt(1));
            }
        }
    }

    // Método para listar todos os estádios
    public List<Estadio> listarEstadios() throws SQLException {
        String sql = "SELECT * FROM Estadio";
        List<Estadio> estadios = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Estadio estadio = new Estadio();
                estadio.setIdEstadio(rs.getInt("id_estadio"));
                estadio.setNome(rs.getString("nome"));
                estadio.setCidade(rs.getString("cidade"));
                estadio.setCapacidade(rs.getInt("capacidade"));
                estadios.add(estadio);
            }
        }

        return estadios;
    }
    
    // Deletar um Estádio
    public static void deleteEstadio(int id) {
        String query = "DELETE FROM Estadio WHERE id_estadio = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Estádio deletado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar estádio: " + e.getMessage());
        }
    }
    
    // Deletar Estádio pelo nome
    public static boolean deletarEstadio(String nome) {
        String query = "SELECT id_estadio FROM Estadio WHERE nome = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id_estadio");
                    EstadioDAO.deleteEstadio(id);
                    return true;
                } else {
                    System.out.println("Estádio não encontrado.");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar e deletar estádio: " + e.getMessage());
            return false;
        }
    }
    
    // Pesquisar
    public static int pesquisarEstadio(String nome) {
    String query = "SELECT id_estadio FROM Estadio WHERE nome = ?";
    try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, nome);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                // Se o estádio for encontrado, retorna o id
                return rs.getInt("id_estadio");
            } else {
                System.out.println("Estádio não encontrado.");
                return -1; // Retorna -1 se o estádio não for encontrado
            }
        }
    } catch (SQLException e) {
        System.err.println("Erro ao pesquisar estádio: " + e.getMessage());
        return -1; // Retorna -1 em caso de erro
    }
}
}
    
    
