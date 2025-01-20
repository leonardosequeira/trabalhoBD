/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author leo
 */
public enum CampeonatoDAO {
    SERIE_A, 
    SERIE_B, 
    SERIE_C;
    private static String sql;

    // Método para converter a string para o enum
    public static CampeonatoDAO fromString(String nome) {
        switch (nome) {
            case "SERIE_A":
                return SERIE_A;
            case "SERIE_B":
                return SERIE_B;
            case "SERIE_C":
                return SERIE_C;
            default:
                throw new IllegalArgumentException("Nome do campeonato inválido: " + nome);
        }
    }
        public static int pesquisarCampeonato(String nomeCampeonato) {

        if (nomeCampeonato.equals("SERIE_A")) {
            return 1;  
        } else if (nomeCampeonato.equals("SERIE_B")) {
            return 2;  
        } else if (nomeCampeonato.equals("SERIE_C")) {
            return 3; 
        } else {
            throw new IllegalArgumentException("Campeonato não encontrado");
        }
    }
}
    



