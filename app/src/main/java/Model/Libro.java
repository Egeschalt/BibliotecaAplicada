package Model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Libro implements Parcelable {

    int IdLibro;
    String titulo;
    String autor;
    String editorial;
    String genero;
    String idioma;
    String fechaPublicacion;

    byte[] imgLibro;
    int cantidadDisponible;

    int Stock;
    public Libro(){

    }
    public Libro(int idLibro, String titulo, String autor, String editorial, String genero, String idioma, String fechaPublicacion, byte[] imgLibro, int cantidadDisponible, int stock) {
        IdLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.genero = genero;
        this.idioma = idioma;
        this.fechaPublicacion = fechaPublicacion;
        this.imgLibro = imgLibro;
        this.cantidadDisponible = cantidadDisponible;
        Stock = stock;
    }

    protected Libro(Parcel in) {
        IdLibro = in.readInt();
        titulo = in.readString();
        autor = in.readString();
        editorial = in.readString();
        genero = in.readString();
        idioma = in.readString();
        fechaPublicacion = in.readString();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            imgLibro = in.readBlob();
        }
        cantidadDisponible = in.readInt();
        Stock = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(IdLibro);
        dest.writeString(titulo);
        dest.writeString(autor);
        dest.writeString(editorial);
        dest.writeString(genero);
        dest.writeString(idioma);
        dest.writeString(fechaPublicacion);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            dest.writeBlob(imgLibro);
        }
        dest.writeInt(cantidadDisponible);
        dest.writeInt(Stock);
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

    public int getIdLibro() {
        return IdLibro;
    }

    public Libro(String titulo, String autor, String editorial, String genero, String idioma, String fechaPublicacion, byte[] imgLibro, int cantidadDisponible, int stock) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.genero = genero;
        this.idioma = idioma;
        this.fechaPublicacion = fechaPublicacion;
        this.imgLibro = imgLibro;
        this.cantidadDisponible=cantidadDisponible;
        this.Stock=stock;
    }



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





    public byte[] getImgLibro() {
        return imgLibro;
    }

    public void setImgLibro(byte[] imgLibro) {
        this.imgLibro = imgLibro;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
