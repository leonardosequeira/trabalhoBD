/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author leo
 */

import java.sql.Timestamp;

public class Partida {
    private int id;
    private Timestamp dataHora;
    private int idEstadio;
    private int idTimeMandante;
    private int idTimeVisitante;
    private int golsMandante;
    private int golsVisitante;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public int getIdEstadio() {
        return idEstadio;
    }

    public void setIdEstadio(int idEstadio) {
        this.idEstadio = idEstadio;
    }

    public int getIdTimeMandante() {
        return idTimeMandante;
    }

    public void setIdTimeMandante(int idTimeMandante) {
        this.idTimeMandante = idTimeMandante;
    }

    public int getIdTimeVisitante() {
        return idTimeVisitante;
    }

    public void setIdTimeVisitante(int idTimeVisitante) {
        this.idTimeVisitante = idTimeVisitante;
    }

    public int getGolsMandante() {
        return golsMandante;
    }

    public void setGolsMandante(int golsMandante) {
        this.golsMandante = golsMandante;
    }

    public int getGolsVisitante() {
        return golsVisitante;
    }

    public void setGolsVisitante(int golsVisitante) {
        this.golsVisitante = golsVisitante;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

