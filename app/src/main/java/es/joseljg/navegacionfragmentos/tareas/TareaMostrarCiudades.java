package es.joseljg.navegacionfragmentos.tareas;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import es.joseljg.navegacionfragmentos.clases.Ciudad;
import es.joseljg.navegacionfragmentos.modelos.CiudadDB;


public class TareaMostrarCiudades  implements Callable<ArrayList<Ciudad>> {
    private ArrayList<Ciudad> ciudades = null;
    private int pagina;
    public TareaMostrarCiudades(int pagina) {
        this.pagina = pagina;
    }

    @Override
    public ArrayList<Ciudad> call() throws Exception {
        ciudades = CiudadDB.obtenerCiudades(this.pagina);
        return ciudades;
    }
}
