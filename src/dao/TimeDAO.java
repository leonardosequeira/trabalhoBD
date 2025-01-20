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
import model.Time;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimeDAO {

    static void deleteEstadio(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static void deleteTime(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Método para inserir um novo time na tabela Time
    public void inserirTime(Time time) throws SQLException {
    String sql = "INSERT INTO Time (nome, id_estadio, id_campeonato) VALUES (?, ?, ?)";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, time.getNome());
        stmt.setInt(2, time.getIdEstadio()); 
        stmt.setInt(3, time.getIdCampeonato()); 
        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            time.setId(rs.getInt(1));
        }
    }
}

    // Método para listar todos os times da tabela Time
    public List<Time> listarTimes() throws SQLException {
        String sql = "SELECT * FROM Time";
        List<Time> times = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Time time = new Time();
                time.setId(rs.getInt("id_time"));
                time.setNome(rs.getString("nome"));
                times.add(time);
            }
        }

        return times;
    }

    // Método para buscar um time por ID
    public Time buscarTimePorId(int id) throws SQLException {
        String sql = "SELECT * FROM Time WHERE id_time = ?";
        Time time = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                time = new Time();
                time.setId(rs.getInt("id_time"));
                time.setNome(rs.getString("nome"));
            }
        }

        return time;
    }

    // Método para atualizar um time
    public void atualizarTime(Time time) throws SQLException {
        String sql = "UPDATE Time SET nome = ? WHERE id_time = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, time.getNome());
            stmt.setInt(2, time.getId());
            stmt.executeUpdate();
        }
    }

    // Método para deletar um time
    public void deletarTime(int id) throws SQLException {
        String sql = "DELETE FROM Time WHERE id_time = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    // Deletar o time pelo nome
    
        public static boolean removerTime(String nome) {
        String query = "SELECT id_time FROM Time WHERE nome = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id_time");
                    deleteTime(id); 
                    return true;
                } else {
                    System.out.println("Time não encontrado.");
                    return false;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao pesquisar e deletar time: " + e.getMessage());
            return false;
        }
    }
    // Pesquisar Time por Nome
    public static int pesquisarTime(String nome) {
    String query = "SELECT id_time FROM Time WHERE nome = ?";
    try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setString(1, nome);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                // Retorna o id_time do time encontrado
                return rs.getInt("id_time");
            } else {
                System.out.println("Time não encontrado.");
                return -1; // Retorna -1 se o time não for encontrado
            }
        }
    } catch (SQLException e) {
        System.err.println("Erro ao pesquisar time: " + e.getMessage());
        return -1;
    }
}
    
    public static void createTime(String nomeTime) {
    String sql = "INSERT INTO Time (nome) VALUES (?)";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, nomeTime);
        stmt.executeUpdate();

        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                int generatedId = rs.getInt(1);
                System.out.println("Time criado com sucesso! ID: " + generatedId);
            }
        }

    } catch (SQLException e) {
        System.err.println("Erro ao criar o time: " + e.getMessage());
    }
}
}
