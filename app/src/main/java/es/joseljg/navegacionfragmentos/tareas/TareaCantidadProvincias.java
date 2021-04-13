package es.joseljg.navegacionfragmentos.tareas;

import java.util.concurrent.Callable;

import es.joseljg.navegacionfragmentos.modelos.ProvinciaDB;


public class TareaCantidadProvincias  implements Callable<Integer> {
    private int cantidadProvincias = 0;


    @Override
    public Integer call() throws Exception {
        cantidadProvincias = ProvinciaDB.obtenerCantidadProvincias();
        return cantidadProvincias;
    }
}
