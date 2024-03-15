package Model;


import android.os.Parcel;
import android.os.Parcelable;


import androidx.annotation.NonNull;


import java.io.Serializable;
import java.util.Date;


public class Prestamo implements Serializable {
    int idPrestamo;
    int idLibro;
    String idEstudiante;
    int idBibliotecario;
    String fechaPrestamo;
    String fechaDevolucion;
    String fechaVencimiento;


    public int getIdPrestamo() {
        return idPrestamo;
    }


    public int getIdLibro() {
        return idLibro;
    }


    public String getIdEstudiante() {
        return idEstudiante;
    }


    public int getIdBibliotecario() {
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






    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }


    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }


    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
    }


    public void setIdBibliotecario(int idBibliotecario) {
        this.idBibliotecario = idBibliotecario;
    }


    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }


    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }


    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }


    public Prestamo(int idLibro, String idEstudiante, int idBibliotecario, String fechaPrestamo, String fechaDevolucion, String fechaVencimiento) {
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
        idBibliotecario = in.readInt();
        fechaPrestamo = in.readString();
        fechaDevolucion = in.readString();
        fechaVencimiento = in.readString();
    }


}