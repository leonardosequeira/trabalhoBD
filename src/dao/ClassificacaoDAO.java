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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClassificacaoDAO {

    public void atualizarClassificacao(int idTime, int golsMarcados, int golsSofridos, int pontos) throws SQLException {
    String sql = "INSERT INTO Classificacao (id_time, pontos, vitorias, empates, derrotas, gols_marcados, gols_sofridos) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                 "ON DUPLICATE KEY UPDATE " +
                 "pontos = pontos + VALUES(pontos), " +
                 "vitorias = vitorias + VALUES(vitorias), " +
                 "empates = empates + VALUES(empates), " +
                 "derrotas = derrotas + VALUES(derrotas), " +
                 "gols_marcados = gols_marcados + VALUES(gols_marcados), " +
                 "gols_sofridos = gols_sofridos + VALUES(gols_sofridos)";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        int vitorias = 0;
        int empates = 0;
        int derrotas = 0;

        // Determina o resultado (vitória, empate, derrota)
        if (golsMarcados > golsSofridos) {
            vitorias = 1;
        } else if (golsMarcados == golsSofridos) {
            empates = 1;
        } else {
            derrotas = 1;
        }

        // Configura os valores
        stmt.setInt(1, idTime);
        stmt.setInt(2, pontos);
        stmt.setInt(3, vitorias);
        stmt.setInt(4, empates);
        stmt.setInt(5, derrotas);
        stmt.setInt(6, golsMarcados);
        stmt.setInt(7, golsSofridos);

        stmt.executeUpdate();
    }
}

}