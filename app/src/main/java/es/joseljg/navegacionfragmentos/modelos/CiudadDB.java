package es.joseljg.navegacionfragmentos.modelos;
import android.graphics.Bitmap;
import android.util.Log;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.joseljg.navegacionfragmentos.clases.Ciudad;
import es.joseljg.navegacionfragmentos.clases.ConfiguracionesGenerales;
import es.joseljg.navegacionfragmentos.utilidades.ImagenesBlobBitmap;


public class CiudadDB {


    //-----------------------------------------------------------
    public static ArrayList<Ciudad> obtenerCiudades() {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            return null;
        }
        ArrayList<Ciudad> ciudadesDevueltas = new ArrayList<Ciudad>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "select * from ciudades";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next()) {
                int idciudad = resultado.getInt("idciudad");
                String nombre = resultado.getString("nombre");
                int habitantes = resultado.getInt("habitantes");
                int idprovincia = resultado.getInt("idprovincia");
                Blob foto = resultado.getBlob("foto");
                Bitmap bm_foto;
                Ciudad c ;
                if(foto != null){
                    bm_foto = ImagenesBlobBitmap.blob_to_bitmap(foto, ConfiguracionesGenerales.ANCHO_IMAGENES_BITMAP, ConfiguracionesGenerales.ALTO_IMAGENES_BITMAP);
                    c = new Ciudad(idciudad, nombre, habitantes, idprovincia, bm_foto);
                }
                else{
                    c = new Ciudad(idciudad, nombre, habitantes, idprovincia, null);
                }

                ciudadesDevueltas.add(c);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return ciudadesDevueltas;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return null;
        }
    }
    //--------------------------------------------------------------
    //-----------------------------------------------------------
    public static ArrayList<Ciudad> obtenerCiudades(int pagina) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            return null;
        }
        ArrayList<Ciudad> ciudadesDevueltas = new ArrayList<Ciudad>();
        try {
            Statement sentencia = conexion.createStatement();
            int desplazamiento = pagina * ConfiguracionesGenerales.ELEMENTOS_POR_PAGINA;
            String ordenSQL = "select * from ciudades LIMIT " + desplazamiento  + " , " + ConfiguracionesGenerales.ELEMENTOS_POR_PAGINA;
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next()) {
                int idciudad = resultado.getInt("idciudad");
                String nombre = resultado.getString("nombre");
                int habitantes = resultado.getInt("habitantes");
                int idprovincia = resultado.getInt("idprovincia");
                Blob foto = resultado.getBlob("foto");
                Bitmap bm_foto;
                Ciudad c ;
                if(foto != null){
                    bm_foto = ImagenesBlobBitmap.blob_to_bitmap(foto, ConfiguracionesGenerales.ANCHO_IMAGENES_BITMAP, ConfiguracionesGenerales.ALTO_IMAGENES_BITMAP);
                    c = new Ciudad(idciudad, nombre, habitantes, idprovincia, bm_foto);
                }
                else{
                    c = new Ciudad(idciudad, nombre, habitantes, idprovincia, null);
                }

                ciudadesDevueltas.add(c);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return ciudadesDevueltas;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return null;
        }
    }
    //--------------------------------------------------------------
    //-----------------------------------------------------------
    public static int obtenerCantidadCiudades() {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            return 0;
        }
        int cantidadCiudades = 0;
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "select count(*) as cantidad from ciudades";
            ResultSet resultado  = sentencia.executeQuery(ordenSQL);
            while (resultado.next()) {
                cantidadCiudades = resultado.getInt("cantidad");
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return cantidadCiudades;
            } catch (SQLException e) {
            Log.i("sql", "error sql");
            return 0;
        }
    }
    //------------------------------------------------------------------------------------------------
    public static Ciudad buscarCiudadTabla(String nombre) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            return null;
        }
        //---------------------------------
        Ciudad ciudadEncontrada = null;
        try {
            String ordensql = "select * from ciudades where nombre like ? LIMIT 1"; // LIMITAMOS LA BUSQUEDA A UNO
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            String nombreBuscado = "%"+nombre+"%";
            pst.setString(1, nombreBuscado);
            ResultSet resultado = pst.executeQuery();
            //------------------------------------------------
            while (resultado.next()) {
                int idciudad = resultado.getInt("idciudad");
                String nombrec = resultado.getString("nombre");
                int habitantes = resultado.getInt("habitantes");
                int idprovincia = resultado.getInt("idprovincia");
                Blob foto = resultado.getBlob("foto");
                Bitmap bm_foto;
                Ciudad c ;
                if(foto != null){
                    bm_foto = ImagenesBlobBitmap.blob_to_bitmap(foto, ConfiguracionesGenerales.ANCHO_IMAGENES_BITMAP, ConfiguracionesGenerales.ALTO_IMAGENES_BITMAP);
                    c = new Ciudad(idciudad, nombrec, habitantes, idprovincia, bm_foto);
                }
                else{
                    c = new Ciudad(idciudad, nombrec, habitantes, idprovincia, null);
                }

                ciudadEncontrada = c;
            }
            resultado.close();
            pst.close();
            conexion.close();
            return ciudadEncontrada;
        } catch (SQLException e) {
            return null;
        }
    }
}
//--------------------------------------------------------------