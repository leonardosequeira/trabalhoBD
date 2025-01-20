/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author leo
 */
public class Time {
    private int id;
    private String nome;
    private int idEstadio;
    private int idCampeonato;

    // Construtor padrão
    public Time() {}

    // Construtor com parâmetros
    public Time(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Sobrescrevendo o método toString para exibir informações do time
    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    public void setIdEstadio(int idEstadio) {
        this.idEstadio = idEstadio;  
    }

    public void setIdCampeonato(int idCampeonato) {
        this.idCampeonato = idCampeonato;
    }
    
    public int getIdEstadio() {
        return idEstadio;
    }
    
    public int getIdCampeonato() {
        return idCampeonato;
    }
}
