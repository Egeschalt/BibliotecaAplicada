package Model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Estudiante implements Parcelable {

    String codEstudiante;
    String nombre;
    String apellidos;
    String dni;
    String carrera;
    String correo;
    String usuario;
    String contrasena;
    String telefono;
    byte[] fotoEstudiante;

    public Estudiante(String codEstudiante, String nombre, String apellidos, String dni, String carrera, String correo,  String usuario, String contrasena, String telefono, byte[] fotoEstudiante) {
        this.codEstudiante = codEstudiante;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.carrera = carrera;
        this.correo = correo;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.fotoEstudiante = fotoEstudiante;
    }
    public Estudiante(String usuario, String correo, String contrasena) {
        this.correo = correo;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
    protected Estudiante(Parcel in) {
        codEstudiante = in.readString();
        nombre = in.readString();
        apellidos = in.readString();
        dni = in.readString();
        carrera = in.readString();
        correo = in.readString();
        usuario = in.readString();
        contrasena = in.readString();
        telefono = in.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            fotoEstudiante = in.readBlob();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codEstudiante);
        dest.writeString(nombre);
        dest.writeString(apellidos);
        dest.writeString(dni);
        dest.writeString(carrera);
        dest.writeString(correo);
        dest.writeString(usuario);
        dest.writeString(contrasena);
        dest.writeString(telefono);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            dest.writeBlob(fotoEstudiante);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Estudiante> CREATOR = new Creator<Estudiante>() {
        @Override
        public Estudiante createFromParcel(Parcel in) {
            return new Estudiante(in);
        }

        @Override
        public Estudiante[] newArray(int size) {
            return new Estudiante[size];
        }
    };

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

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() { return contrasena; }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public byte[] getFoto() {
        return fotoEstudiante;
    }

    public void setFoto(byte[] fotoEstudiante) {
        this.fotoEstudiante = fotoEstudiante;
    }
}
