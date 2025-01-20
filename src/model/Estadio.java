/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author leo
 */
public class Estadio {
    private int idEstadio;
    private String nome;
    private String cidade;
    private int capacidade;

    // Construtores
    public Estadio() {
    }

    public Estadio(int id_Estadio, String nome, String cidade, int capacidade) {
        this.idEstadio = idEstadio;
        this.nome = nome;
        this.cidade = cidade;
        this.capacidade = capacidade;
    }

    // Getters e Setters
    public int getIdEstadio() {
        return idEstadio;
    }

    public void setIdEstadio(int idEstadio) {
        this.idEstadio = idEstadio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}
