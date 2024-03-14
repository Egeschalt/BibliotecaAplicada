package Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Prestamo implements Parcelable {
    int idPrestamo;
    int idLibro;
    String idEstudiante;
    String idBibliotecario;
    String fechaPrestamo;
    String fechaDevolucion;
    String fechaVencimiento;

    public Prestamo(int idPrestamo, int idLibro, String idEstudiante, String  idBibliotecario, String fechaPrestamo, String fechaDevolucion, String fechaVencimiento) {
        this.idPrestamo = idPrestamo;
        this.idLibro = idLibro;
        this.idEstudiante = idEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaVencimiento = fechaVencimiento;
    }
    public Prestamo( int  idLibro, String  idEstudiante, String  idBibliotecario, String fechaPrestamo, String fechaDevolucion, String fechaVencimiento) {
        this.idLibro = idLibro;
        this.idEstudiante = idEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaVencimiento = fechaVencimiento;
    }

    protected Prestamo(Parcel in) {
        idPrestamo = in.readInt();
        idLibro = in.readInt();
        idEstudiante = in.readString();
        idBibliotecario = in.readString();
        fechaPrestamo = in.readString();
        fechaDevolucion = in.readString();
        fechaVencimiento = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idPrestamo);
        dest.writeInt(idLibro);
        dest.writeString(idEstudiante);
        dest.writeString(idBibliotecario);
        dest.writeString(fechaPrestamo);
        dest.writeString(fechaDevolucion);
        dest.writeString(fechaVencimiento);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Prestamo> CREATOR = new Creator<Prestamo>() {
        @Override
        public Prestamo createFromParcel(Parcel in) {
            return new Prestamo(in);
        }

        @Override
        public Prestamo[] newArray(int size) {
            return new Prestamo[size];
        }
    };

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public String getIdEstudiante() {
        return idEstudiante;
    }

    public String getIdBibliotecario() {
        return idBibliotecario;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }
}
