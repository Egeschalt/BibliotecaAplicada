package VistaModelo;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BDPrestamoOpenHelper extends SQLiteOpenHelper {
    String tabla_estudiante = "CREATE TABLE Estudiante(" +
            "IdEstudiante INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "Nombre VARCHAR(40) NOT NULL UNIQUE," +
            "Apellidos VARCHAR(40) NOT NULL," +
            "Dni VARCHAR(8) NOT NULL," +
            "Carrera VARCHAR(40) NOT NULL," +
            "Correo VARCHAR(40) NOT NULL," +
            "Telefono VARCHAR(9) NOT NULL," +
            "fotoEstudiante BYTE)";

    String tabla_bibliotecario = "CREATE TABLE Bibliotecario(" +
            "IdBibliotecario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "Nombre VARCHAR(40) NOT NULL UNIQUE," +
            "Apellidos VARCHAR(40) NOT NULL," +
            "Telefono VARCHAR(9) NOT NULL," +
            "fotoBibliotecario BYTE)";

    String tabla_libro = "CREATE TABLE Libro(" +
            "IdLibro INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "Título VARCHAR(40) NOT NULL UNIQUE," +
            "Autor VARCHAR(40) NOT NULL," +
            "Editorial VARCHAR(40) NOT NULL," +
            "Género VARCHAR(20) NOT NULL," +
            "Idioma VARCHAR(20) NOT NULL," +
            "FechaDePublicación VARCHAR(20) NOT NULL," +
            "Disponibilidad BOOLEAN NOT NULL," +
            "fotoLibro BYTE)";
    String tabla_prestamo = "CREATE TABLE Prestamo(" +
            "IdPrestamo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "IdLibro INTEGER NOT NULL," +
            "IdEstudiante INTEGER NOT NULL," +
            "IdBibliotecario INTEGER NOT NULL," +
            "FechaDePréstamo VARCHAR(20) NOT NULL," +
            "FechaDeDevolución VARCHAR(20) NOT NULL," +
            "FechaDeVencimiento VARCHAR(20) NOT NULL," +
            "FOREIGN KEY(IdLibro) REFERENCES Libro(IdLibro)," +
            "FOREIGN KEY(IdEstudiante) REFERENCES Estudiante(IdEstudiante)," +
            "FOREIGN KEY(IdBibliotecario) REFERENCES Bibliotecario(IdBibliotecario)" +
            ");";

    public BDPrestamoOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla_estudiante);
        db.execSQL(tabla_bibliotecario);
        db.execSQL(tabla_libro);
        db.execSQL(tabla_prestamo);
        enableForeignKeys(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Estudiante");
        db.execSQL("DROP TABLE IF EXISTS Bibliotecario");
        db.execSQL("DROP TABLE IF EXISTS Libro");
        db.execSQL("DROP TABLE IF EXISTS Prestamo");
        onCreate(db);
    }

    private void enableForeignKeys(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON");
        System.out.println("PRAGMA maybe ok");
    }
}
