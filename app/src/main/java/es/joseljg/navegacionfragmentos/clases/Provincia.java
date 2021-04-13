package es.joseljg.navegacionfragmentos.clases;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Provincia implements Serializable {
    private int idprovincia;
    private String nombre;
    private Bitmap fotoProvincia;

    public Provincia(int idprovincia, String nombre) {
        this.idprovincia = idprovincia;
        this.nombre = nombre;
    }

    public Provincia(int idprovincia, String nombre, Bitmap fotoProvincia) {
        this.idprovincia = idprovincia;
        this.nombre = nombre;
        this.fotoProvincia = fotoProvincia;
    }

    public Provincia() {
        this.idprovincia= 0;
        this.nombre = "";
        this.fotoProvincia = null;
    }
    //------------------------------

    public int getIdprovincia() {
        return idprovincia;
    }

    public void setIdprovincia(int idprovincia) {
        this.idprovincia = idprovincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Bitmap getFotoProvincia() {
        return fotoProvincia;
    }

    public void setFotoProvincia(Bitmap fotoProvincia) {
        this.fotoProvincia = fotoProvincia;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idprovincia;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Provincia other = (Provincia) obj;
        if (idprovincia != other.idprovincia)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Provincia{" +
                "idprovincia=" + idprovincia +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
