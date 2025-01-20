/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author leo
 */
import java.sql.SQLException;
import model.Partida;
import service.PartidaService;

import java.sql.Timestamp;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PartidaService partidaService = new PartidaService();

        try {
            System.out.println("Data e hora da partida (yyyy-MM-dd HH:mm:ss): ");
            String dataHoraStr = scanner.nextLine();
            Timestamp dataHora = Timestamp.valueOf(dataHoraStr);

            System.out.println("ID do estádio: ");
            int idEstadio = scanner.nextInt();

            System.out.println("ID do time mandante: ");
            int idTimeMandante = scanner.nextInt();

            System.out.println("ID do time visitante: ");
            int idTimeVisitante = scanner.nextInt();

            System.out.println("Gols do mandante: ");
            int golsMandante = scanner.nextInt();

            System.out.println("Gols do visitante: ");
            int golsVisitante = scanner.nextInt();

            Partida partida = new Partida();
            partida.setDataHora(dataHora);
            partida.setIdEstadio(idEstadio);
            partida.setIdTimeMandante(idTimeMandante);
            partida.setIdTimeVisitante(idTimeVisitante);
            partida.setGolsMandante(golsMandante);
            partida.setGolsVisitante(golsVisitante);
            partida.setStatus("Encerrada");

            partidaService.registrarPartida(partida);
            System.out.println("Partida registrada com sucesso!");
        } catch (SQLException e) {
        } finally {
            scanner.close();
        }
    }
}

