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
    byte[] fotoBibliotecario;

    public Bibliotecario(String idBibliotecario, String nombre, String apellidos, String telefono, byte[] fotoBibliotecario) {
        this.idBibliotecario = idBibliotecario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fotoBibliotecario = fotoBibliotecario;
    }

    protected Bibliotecario(Parcel in) {
        idBibliotecario = in.readString();
        nombre = in.readString();
        apellidos = in.readString();
        telefono = in.readString();
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcel.writeBlob(fotoBibliotecario);
        }
    }
}
