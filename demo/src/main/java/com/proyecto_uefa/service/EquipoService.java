package com.proyecto_uefa.service;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.io.FileReader;
import java.util.ArrayList;

import com.proyecto_uefa.model.Equipo;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class EquipoService {
     private static final String JSON_PATH = "src/main/java/JSON/UefaTeam.json";
    public static List<Equipo> listarEquiposFundadosDespuesDe2000() {
        List<Equipo> listaEquipos = new ArrayList<>();

        try {


           
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(JSON_PATH));
            JSONObject jsonObject = (JSONObject) obj;

     
            JSONArray equipos = (JSONArray) jsonObject.get("equipos");

         
            for (Object equipoObj : equipos) {
                JSONObject equipoJSON = (JSONObject) equipoObj;
                int yearFoundation = Integer.parseInt(equipoJSON.get("yearfoundation").toString());

                Equipo equipo = new Equipo(
                    Integer.parseInt(equipoJSON.get("id").toString()),
                    (String) equipoJSON.get("name"),
                    yearFoundation,
                    (String) equipoJSON.get("coach")
                );

                listaEquipos.add(equipo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

 
        Predicate<Equipo> fundadosDespuesDe2000 = equipo -> equipo.getYearfoundation() > 2000;

       
        List<Equipo> equiposFiltrados = listaEquipos.stream()
                .filter(fundadosDespuesDe2000)
                .collect(Collectors.toList());

       
       
        System.out.println("---------------------------------------------");
        System.out.printf("%-5s | %-20s | %-15s %n", "ID", "Equipo", "Año de fundación");
        System.out.println("---------------------------------------------");
        for (Equipo equipo : equiposFiltrados) {
            System.out.printf("%-5d | %-20s | %-15d %n", equipo.getId(), equipo.getName(), equipo.getYearfoundation());
        }

        return equiposFiltrados;
    }
 
    public static void imprimirNombresEntrenadores() {
        List<Equipo> listaEquipos = new ArrayList<>();

        try {
           
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(JSON_PATH));
            JSONObject jsonObject = (JSONObject) obj;

        
            JSONArray equipos = (JSONArray) jsonObject.get("equipos");

           
            for (Object equipoObj : equipos) {
                JSONObject equipoJSON = (JSONObject) equipoObj;
                Equipo equipo = new Equipo(
                    Integer.parseInt(equipoJSON.get("id").toString()),
                    (String) equipoJSON.get("name"),
                    Integer.parseInt(equipoJSON.get("yearfoundation").toString()),
                    (String) equipoJSON.get("coach")
                );

                listaEquipos.add(equipo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

       
        Consumer<Equipo> imprimirEntrenador = equipo -> 
            System.out.printf("%-20s | %-15s %n", equipo.getName(), equipo.getCoach());

        
        System.out.println("\n===== Lista de Entrenadores =====");
        System.out.println("---------------------------------------");
        System.out.printf("%-20s | %-15s %n", "Equipo", "Entrenador");
        System.out.println("---------------------------------------");

        
        listaEquipos.forEach(imprimirEntrenador);
    }

}