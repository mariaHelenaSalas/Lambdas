package com.proyecto_uefa.service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.proyecto_uefa.model.equipos;
import com.proyecto_uefa.utils.JSONReader;

public class DataProcessor {

    private List<equipos> equipos;

    public void DataProcessor(String rutaArchivo) {
        this.equipos = JSONReader.cargarEquipos(rutaArchivo);
    }

    public void listarEquiposFundadosDespuesDe2000() {
        Predicate<equipos> filtro = equipos -> Integer.parseInt  D(equipos.getYearfoundation()) > 2000;
        List<equipos> filtrados = equipos.stream().filter(filtro).collect(Collectors.toList());

        System.out.println("Equipos fundados después del 2000:");
        filtrados.forEach(e -> System.out.println(e.getName()));
    }
}



