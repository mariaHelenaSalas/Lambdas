package com.proyecto_uefa.utils;

import java.util.List;
import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto_uefa.model.equipos;

public class JSONReader {
    
    public static List<equipos> cargarEquipos(String ruta) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return List.of(mapper.readValue(new File(ruta), equipos[].class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
