/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author leo
 */
import dao.ClassificacaoDAO;
import dao.PartidaDAO;
import model.Partida;

import java.sql.SQLException;

public class PartidaService {
    private final PartidaDAO partidaDAO = new PartidaDAO();
    private final ClassificacaoDAO classificacaoDAO = new ClassificacaoDAO();

    public void registrarPartida(Partida partida) throws SQLException {
        // Inserir partida
        partidaDAO.inserirPartida(partida);

        // Atualizar classificação caso a partida tenha um resultado
        if ("Encerrada".equals(partida.getStatus())) {
            int pontosMandante = partida.getGolsMandante() > partida.getGolsVisitante() ? 3 : (partida.getGolsMandante() == partida.getGolsVisitante() ? 1 : 0);
            int pontosVisitante = partida.getGolsVisitante() > partida.getGolsMandante() ? 3 : (partida.getGolsMandante() == partida.getGolsVisitante() ? 1 : 0);

            classificacaoDAO.atualizarClassificacao(partida.getIdTimeMandante(), partida.getGolsMandante(), partida.getGolsVisitante(), pontosMandante);
            classificacaoDAO.atualizarClassificacao(partida.getIdTimeVisitante(), partida.getGolsVisitante(), partida.getGolsMandante(), pontosVisitante);
        }
    }
}
