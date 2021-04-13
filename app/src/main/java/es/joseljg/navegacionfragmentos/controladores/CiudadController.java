package es.joseljg.navegacionfragmentos.controladores;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import es.joseljg.navegacionfragmentos.clases.Ciudad;
import es.joseljg.navegacionfragmentos.tareas.TareaCantidadCiudades;
import es.joseljg.navegacionfragmentos.tareas.TareaMostrarCiudades;


public class CiudadController {

    public static ArrayList<Ciudad> obtenerCiudades(int pagina)
    {
        ArrayList<Ciudad> ciudadesDevueltas = null;
        FutureTask t = new FutureTask (new TareaMostrarCiudades(pagina));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            ciudadesDevueltas= (ArrayList<Ciudad>)t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ciudadesDevueltas;
    }
    //---------------------------------------------------------------------------
    public static int obtenerCantidadCiudades( )
    {
        int cantidadCiudades = 0;
        FutureTask t = new FutureTask (new TareaCantidadCiudades());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            cantidadCiudades = (int)t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return cantidadCiudades;
    }
}
