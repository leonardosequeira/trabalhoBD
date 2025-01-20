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
import model.Partida;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartidaDAO {

    public void inserirPartida(Partida partida) throws SQLException {
        String sql = "INSERT INTO Partida (data_hora, id_estadio, id_time_mandante, id_time_visitante, gols_mandante, gols_visitante, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setTimestamp(1, partida.getDataHora());
            stmt.setInt(2, partida.getIdEstadio());
            stmt.setInt(3, partida.getIdTimeMandante());
            stmt.setInt(4, partida.getIdTimeVisitante());
            stmt.setInt(5, partida.getGolsMandante());
            stmt.setInt(6, partida.getGolsVisitante());
            stmt.setString(7, partida.getStatus());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                partida.setId(rs.getInt(1));
            }
        }
    }

    public List<Partida> listarPartidas() throws SQLException {
        String sql = "SELECT * FROM BANCODEDADOSBR2.Partida";
        List<Partida> partidas = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Partida partida = new Partida();
                partida.setId(rs.getInt("id_partida"));
                partida.setDataHora(rs.getTimestamp("data_hora"));
                partida.setIdEstadio(rs.getInt("id_estadio"));
                partida.setIdTimeMandante(rs.getInt("id_time_mandante"));
                partida.setIdTimeVisitante(rs.getInt("id_time_visitante"));
                partida.setGolsMandante(rs.getInt("gols_mandante"));
                partida.setGolsVisitante(rs.getInt("gols_visitante"));
                partida.setStatus(rs.getString("status"));
                partidas.add(partida);
            }
        }

        return partidas;
}
}

