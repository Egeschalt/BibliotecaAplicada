package VistaModelo;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class BDPrestamoOpenHelper extends SQLiteOpenHelper {
    String tabla_estudiante = "CREATE TABLE Estudiante(" +
            "IdEstudiante INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "Nombre VARCHAR(40) NOT NULL UNIQUE," +
            "Apellidos VARCHAR(40) NOT NULL," +
            "Dni VARCHAR(8) NOT NULL," +
            "Carrera VARCHAR(40) NOT NULL," +
            "Correo VARCHAR(40) NOT NULL," +
            "Teléfono VARCHAR(9) NOT NULL," +
            "Imagen BYTE)";

    String tabla_bibliotecario = "CREATE TABLE Bibliotecario(" +
            "IdBibliotecario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "Nombre VARCHAR(40) NOT NULL UNIQUE," +
            "Apellidos VARCHAR(40) NOT NULL," +
            "Teléfono VARCHAR(9) NOT NULL," +
            "descripcion TEXT," +
            "Imagen BYTE)";

    String tabla_libro = "CREATE TABLE Libro(" +
            "IdLibro INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "Título VARCHAR(40) NOT NULL UNIQUE," +
            "Autor VARCHAR(40) NOT NULL," +
            "Editorial VARCHAR(40) NOT NULL," +
            "Género VARCHAR(20) NOT NULL," +
            "Idioma VARCHAR(20) NOT NULL," +
            "Fecha de publicación VARCHAR(20) NOT NULL," +
            "Disponibilidad BOOLEAN NOT NULL," +
            "Imagen BYTE)";

    public BDPrestamoOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla_estudiante);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Estudiante");
        onCreate(db);
    }
}
