package com.proyecto_uefa;

import com.proyecto_uefa.service.DataProcessor;

public class Main {
    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor("src/resources/equipos.json");
        processor.listarEquiposFundadosDespuesDe2000();
    }
}


