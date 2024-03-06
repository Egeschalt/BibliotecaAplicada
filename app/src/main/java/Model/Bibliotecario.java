package Model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Bibliotecario implements Parcelable {
    String idBibliotecario;
    String nombre;
    String apellidos;
    String telefono;
    String correo;
    String usuario;
    String contrasena;
    byte[] fotoBibliotecario;

    public Bibliotecario(String idBibliotecario, String nombre, String apellidos, String telefono, String correo, String usuario, String contrasena, byte[] fotoBibliotecario) {
        this.idBibliotecario = idBibliotecario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.correo = correo;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.fotoBibliotecario = fotoBibliotecario;
    }

    protected Bibliotecario(Parcel in) {
        idBibliotecario = in.readString();
        nombre = in.readString();
        apellidos = in.readString();
        telefono = in.readString();
        correo = in.readString();
        usuario = in.readString();
        contrasena = in.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            fotoBibliotecario = in.readBlob();
        }
    }

    public static final Creator<Bibliotecario> CREATOR = new Creator<Bibliotecario>() {
        @Override
        public Bibliotecario createFromParcel(Parcel in) {
            return new Bibliotecario(in);
        }

        @Override
        public Bibliotecario[] newArray(int size) {
            return new Bibliotecario[size];
        }
    };

    public String getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setIdBibliotecario(String idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public byte[] getFotoBibliotecario() {
        return fotoBibliotecario;
    }

    public void setFotoBibliotecario(byte[] fotoBibliotecario) {
        this.fotoBibliotecario = fotoBibliotecario;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(idBibliotecario);
        parcel.writeString(nombre);
        parcel.writeString(apellidos);
        parcel.writeString(telefono);
        parcel.writeString(correo);
        parcel.writeString(usuario);
        parcel.writeString(contrasena);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcel.writeBlob(fotoBibliotecario);
        }
    }
}
