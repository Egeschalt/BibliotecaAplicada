package Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Prestamo implements Parcelable {
    int idPrestamo;
    Libro idLibro;
    Estudiante idEstudiante;
    Bibliotecario idBibliotecario;
    Date fechaPrestamo;
    Date fechaDevolucion;
    Date fechaVencimiento;

    public Prestamo(int idPrestamo, Libro idLibro, Estudiante idEstudiante, Bibliotecario idBibliotecario, Date fechaPrestamo, Date fechaDevolucion, Date fechaVencimiento) {
        this.idPrestamo = idPrestamo;
        this.idLibro = idLibro;
        this.idEstudiante = idEstudiante;
        this.idBibliotecario = idBibliotecario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Libro getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Libro idLibro) {
        this.idLibro = idLibro;
    }

    public Estudiante getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Estudiante idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Bibliotecario getIdBibliotecario() {
        return idBibliotecario;
    }

    public void setIdBibliotecario(Bibliotecario idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    protected Prestamo(Parcel in) {
        idPrestamo = in.readInt();
        idLibro = in.readParcelable(Libro.class.getClassLoader());
        idEstudiante = in.readParcelable(Estudiante.class.getClassLoader());
        idBibliotecario = in.readParcelable(Bibliotecario.class.getClassLoader());
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(idPrestamo);
        parcel.writeParcelable(idLibro, i);
        parcel.writeParcelable(idEstudiante, i);
        parcel.writeParcelable(idBibliotecario, i);
    }
}
