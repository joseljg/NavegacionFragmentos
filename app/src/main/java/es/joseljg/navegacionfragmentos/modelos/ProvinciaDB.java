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

import es.joseljg.navegacionfragmentos.clases.ConfiguracionesGenerales;
import es.joseljg.navegacionfragmentos.clases.Provincia;
import es.joseljg.navegacionfragmentos.utilidades.ImagenesBlobBitmap;


public class ProvinciaDB {


    //-----------------------------------------------------------
    public static ArrayList<Provincia> obtenerProvincias() {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            return null;
        }
        ArrayList<Provincia> provinciasDevueltas = new ArrayList<Provincia>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "select * from provincias";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next()) {
                int idprovincia = resultado.getInt("idprovincia");
                String nombre = resultado.getString("nombre");
                Blob foto = resultado.getBlob("foto");
                Bitmap bm_foto;
                Provincia p ;
                if(foto != null){
                    bm_foto = ImagenesBlobBitmap.blob_to_bitmap(foto, ConfiguracionesGenerales.ANCHO_IMAGENES_BITMAP, ConfiguracionesGenerales.ALTO_IMAGENES_BITMAP);
                    p = new Provincia(idprovincia, nombre, bm_foto);
                }
                else{
                    p = new Provincia(idprovincia, nombre, null);
                }

                provinciasDevueltas.add(p);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return provinciasDevueltas;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return null;
        }
    }
    //--------------------------------------------------------------
    //-----------------------------------------------------------
    public static ArrayList<Provincia> obtenerProvincias(int pagina) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            return null;
        }
        ArrayList<Provincia> provinciasDevueltas = new ArrayList<Provincia>();
        try {
            Statement sentencia = conexion.createStatement();
            int desplazamiento = pagina * ConfiguracionesGenerales.ELEMENTOS_POR_PAGINA;
            String ordenSQL = "select * from provincias LIMIT " + desplazamiento  + " , " + ConfiguracionesGenerales.ELEMENTOS_POR_PAGINA;
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next()) {
                int idprovincia = resultado.getInt("idprovincia");
                String nombre = resultado.getString("nombre");
                Blob foto = resultado.getBlob("foto");
                Bitmap bm_foto;
                Provincia p ;
                if(foto != null){
                    bm_foto = ImagenesBlobBitmap.blob_to_bitmap(foto, ConfiguracionesGenerales.ANCHO_IMAGENES_BITMAP, ConfiguracionesGenerales.ALTO_IMAGENES_BITMAP);
                    p = new Provincia(idprovincia, nombre, bm_foto);
                }
                else{
                    p = new Provincia(idprovincia, nombre, null);
                }

                provinciasDevueltas.add(p);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return provinciasDevueltas;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return null;
        }
    }
    //--------------------------------------------------------------
    //-----------------------------------------------------------
    public static int obtenerCantidadProvincias() {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            return 0;
        }
        int cantidadProvincias = 0;
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "select count(*) as cantidad from provincias";
            ResultSet resultado  = sentencia.executeQuery(ordenSQL);
            while (resultado.next()) {
                cantidadProvincias = resultado.getInt("cantidad");
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return cantidadProvincias;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return 0;
        }
    }
    //------------------------------------------------------------------------------------------------
    public static Provincia buscarProvinciaTabla(String nombre) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            return null;
        }
        //---------------------------------
        Provincia provinciaEncontrada = null;
        try {
            String ordensql = "select * from provincias where nombre like ? LIMIT 1"; // LA BUSQUEDA SE LIMITA A UNA PROVINCIA
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            String nombreBuscado = "%"+nombre+"%";
            pst.setString(1, nombreBuscado);
            ResultSet resultado = pst.executeQuery();
            //------------------------------------------------
            while (resultado.next()) {
                int idprovincia = resultado.getInt("idprovincia");
                String nombrep = resultado.getString("nombre");
                Blob foto = resultado.getBlob("foto");
                Bitmap bm_foto;
                Provincia p ;
                if(foto != null){
                    bm_foto = ImagenesBlobBitmap.blob_to_bitmap(foto, ConfiguracionesGenerales.ANCHO_IMAGENES_BITMAP, ConfiguracionesGenerales.ALTO_IMAGENES_BITMAP);
                    p = new Provincia(idprovincia, nombre, bm_foto);
                }
                else{
                    p = new Provincia(idprovincia, nombrep, null);
                }
                provinciaEncontrada = p;
            }
            resultado.close();
            pst.close();
            conexion.close();
            return provinciaEncontrada;
        } catch (SQLException e) {
            return null;
        }
    }

}
