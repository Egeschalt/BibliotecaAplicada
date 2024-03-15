package Model;


import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;


import androidx.annotation.NonNull;


import java.io.Serializable;


public class Bibliotecario implements Serializable {
    int idBibliotecario;
    String nombre;
    String apellidos;
    String telefono;
    String correo;
    String contrasena;
    byte[] fotoBibliotecario;


    public Bibliotecario(){


    }
    public Bibliotecario( String nombre, String apellidos, String telefono, String correo, String contrasena) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasena = contrasena;
    }
    public Bibliotecario( String nombre, String apellidos, String telefono, String correo, String contrasena, byte[] fotoBibliotecario) {


        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.contrasena = contrasena;
        this.fotoBibliotecario = fotoBibliotecario;
    }




    public int getIdBibliotecario() {
        return idBibliotecario;
    }


    public String getNombre() {
        return nombre;
    }


    public String getApellidos() {
        return apellidos;
    }


    public String getTelefono() {
        return telefono;
    }


    public String getCorreo() {
        return correo;
    }


    public String getContrasena() {
        return contrasena;
    }


    public byte[] getFotoBibliotecario() {
        return fotoBibliotecario;
    }


    public void setIdBibliotecario(int idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


    public void setFotoBibliotecario(byte[] fotoBibliotecario) {
        this.fotoBibliotecario = fotoBibliotecario;
    }
}



