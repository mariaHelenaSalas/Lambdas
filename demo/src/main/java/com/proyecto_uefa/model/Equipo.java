package com.proyecto_uefa.model;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private int id;
    private String name;
    private int yearfoundation;
    private String coach;
    private List<Jugadores> Jugadores; 

    public Equipo(int id, String name, int yearfoundation, String coach) {
        this.id = id;
        this.name = name;
        this.yearfoundation = yearfoundation;
        this.coach = coach;
        this.Jugadores = new ArrayList<>(); 
    }

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYearfoundation() {
        return yearfoundation;
    }

    public void setYearfoundation(int yearfoundation) {
        this.yearfoundation = yearfoundation;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public List<Jugadores> getJugadores() {
        return Jugadores;
    }

    public void setJugadores(List<Jugadores> jugadores) {
        this.Jugadores = jugadores;
    }

    
    @Override
    public String toString() {
        return String.format("ID: %d | Equipo: %s | Fundación: %d | Entrenador: %s", id, name, yearfoundation, coach);
    }

}
