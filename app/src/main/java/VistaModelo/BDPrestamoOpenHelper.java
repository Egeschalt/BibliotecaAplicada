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
            "Teléfono VARCHAR(9) NOT NULL," +
            "imagen BYTE)";

    String tabla_bibliotecario = "CREATE TABLE Bibliotecario(" +
            "IdBibliotecario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "Nombre VARCHAR(40) NOT NULL UNIQUE," +
            "Apellidos VARCHAR(40) NOT NULL," +
            "Teléfono VARCHAR(9) NOT NULL," +
            "descripcion TEXT," +
            "imagen BYTE)";


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
