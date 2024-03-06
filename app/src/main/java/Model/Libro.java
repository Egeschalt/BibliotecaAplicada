package Model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Libro implements Parcelable {

    String titulo;
    String autor;
    String editorial;
    String genero;
    String idioma;
    String fechaPublicacion;
    boolean disponibilidad;
    byte[] imgLibro;

    public Libro( String titulo, String autor, String editorial, String genero, String idioma, String fechaPublicacion, boolean disponibilidad, byte[] imgLibro) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.genero = genero;
        this.idioma = idioma;
        this.fechaPublicacion = fechaPublicacion;
        this.disponibilidad = disponibilidad;
        this.imgLibro = imgLibro;
    }

    protected Libro(Parcel in) {

        titulo = in.readString();
        autor = in.readString();
        editorial = in.readString();
        genero = in.readString();
        idioma = in.readString();
        disponibilidad = in.readByte() != 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            imgLibro = in.readBlob();
        }
    }

    public static final Creator<Libro> CREATOR = new Creator<Libro>() {
        @Override
        public Libro createFromParcel(Parcel in) {
            return new Libro(in);
        }

        @Override
        public Libro[] newArray(int size) {
            return new Libro[size];
        }
    };



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public byte[] getImgLibro() {
        return imgLibro;
    }

    public void setImgLibro(byte[] imgLibro) {
        this.imgLibro = imgLibro;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

        parcel.writeString(titulo);
        parcel.writeString(autor);
        parcel.writeString(editorial);
        parcel.writeString(genero);
        parcel.writeString(idioma);
        parcel.writeByte((byte) (disponibilidad ? 1 : 0));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            parcel.writeBlob(imgLibro);
        }
    }
}
