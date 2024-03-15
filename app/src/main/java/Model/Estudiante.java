package Model;


import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;


import androidx.annotation.RequiresApi;


import java.io.Serializable;


public class Estudiante implements Serializable {


    String codEstudiante;
    String nombre;
    String apellidos;
    String dni;//
    String carrera;
    String correo;
    String contrasena;
    String telefono;
    byte[] fotoEstudiante;//


    public Estudiante() {

    }

    public Estudiante(String codEstudiante, String nombre, String apellidos, String dni, String carrera, String correo, String contrasena, String telefono, byte[] fotoEstudiante) {
        this.codEstudiante = codEstudiante;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.carrera = carrera;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.fotoEstudiante = fotoEstudiante;
    }
    public Estudiante(String codEstudiante, String nombre, String apellidos, String dni, String carrera, String correo, String contrasena, String telefono) {
        this.codEstudiante = codEstudiante;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.carrera = carrera;
        this.correo = correo;
        this.contrasena = contrasena;
        this.telefono = telefono;
    }


    public String getCodEstudiante() {
        return codEstudiante;
    }


    public void setCodEstudiante(String codEstudiante) {
        this.codEstudiante = codEstudiante;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getApellidos() {
        return apellidos;
    }


    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }


    public String getDni() {
        return dni;
    }


    public void setDni(String dni) {
        this.dni = dni;
    }


    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getContrasena() {
        return contrasena;
    }


    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


    public String getTelefono() {
        return telefono;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public byte[] getFotoEstudiante() {
        return fotoEstudiante;
    }


    public void setFotoEstudiante(byte[] fotoEstudiante) {
        this.fotoEstudiante = fotoEstudiante;
    }
}



