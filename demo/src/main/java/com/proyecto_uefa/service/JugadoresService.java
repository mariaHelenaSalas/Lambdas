package com.proyecto_uefa.service;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.proyecto_uefa.model.Equipo;
import com.proyecto_uefa.model.Jugadores;

public class JugadoresService {
    private static final String JSON_PATH = "src/main/java/resources/equipos.json";

    public static void calcularPromedioEdadPorEquipo() {
        List<Equipo> listaEquipos = new ArrayList<>();

        try {

            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(JSON_PATH));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray equipos = (JSONArray) jsonObject.get("equipos");

            // Recorrer los equipos y obtener la lista de jugadores
            for (Object equipoObj : equipos) {
                JSONObject equipoJSON = (JSONObject) equipoObj;
                String nombreEquipo = (String) equipoJSON.get("name");

                // Obtener la lista de jugadores
                JSONArray jugadoresJSON = (JSONArray) equipoJSON.get("players");
                List<Jugadores> listaJugadores = new ArrayList<>();

                for (Object jugadorObj : jugadoresJSON) {
                    JSONObject jugadorJSON = (JSONObject) jugadorObj;

                    final Jugadores jugador = new Jugadores(
                            Integer.parseInt(jugadorJSON.get("age").toString()),
                            (String) jugadorJSON.get("name"),
                            (String) jugadorJSON.get("position"));

                    listaJugadores.add(jugador);
                }

                // Crear objeto Equipo
                Equipo equipo = new Equipo(
                        Integer.parseInt(equipoJSON.get("id").toString()),
                        nombreEquipo,
                        Integer.parseInt(equipoJSON.get("yearfoundation").toString()),
                        (String) equipoJSON.get("coach"));

                listaEquipos.add(equipo);

                // BiConsumer para calcular y mostrar el promedio de edad
                BiConsumer<Equipo, List<Jugadores>> calcularPromedioEdad = (eq, jugadores) -> {
                    double promedioEdad = jugadores.stream()
                            .collect(Collectors.averagingInt(Jugadores::getAge));

                    System.out.printf("Equipo: %-20s | Promedio Edad: %.2f %n", eq.getName(), promedioEdad);
                };

                // Aplicar el BiConsumer
                calcularPromedioEdad.accept(equipo, listaJugadores);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
