package VistaModelo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;

import Model.Libro;

public class VMLibro {
    ArrayList<Libro> listaLibros;
    String nombreBD;
    Activity oActivity;
    int version;
    public VMLibro() {
        this.listaLibros =new ArrayList<Libro>();
        nombreBD="BDBiblioteca";
        version=1;
    }

    private void InsertarLibrosCargados() {

    }

    public boolean AgregarLibro(Activity oActivity, Libro libro) {
        boolean rpta = false;
        BDPrestamoOpenHelper openHelper = new BDPrestamoOpenHelper(oActivity, nombreBD, null, version);
        SQLiteDatabase oBD = null;

        try {
            oBD = openHelper.getWritableDatabase();
            if (oBD != null) {
                ContentValues oRegistro = new ContentValues();
                oRegistro.put("titulo", libro.getTitulo());
                oRegistro.put("autor", libro.getAutor());
                oRegistro.put("editorial", libro.getEditorial());
                oRegistro.put("genero", libro.getGenero());
                oRegistro.put("idioma", libro.getIdioma());
                oRegistro.put("FechaDePublicación", libro.getFechaPublicacion());
                oRegistro.put("fotoLibro", libro.getImgLibro());
                oRegistro.put("cantidadDisponible", libro.getCantidadDisponible());
                oRegistro.put("stock", libro.getStock());
                long fila = oBD.insert("Libro", null, oRegistro);
                Log.d("valor de fila", String.valueOf(fila));
                if (fila > 0) {
                    rpta = true;
                }
            }
        } catch (Exception e) {
            // Manejar la excepción adecuadamente (por ejemplo, loggearla)
        } finally {
            if (oBD != null) {
                oBD.close();
            }
        }

        return rpta;
    }

    public boolean CargarLibros(Activity oActivity){
        boolean rpta=false;
        listaLibros.clear();
        BDPrestamoOpenHelper bdPrestamoOpenHelper=new BDPrestamoOpenHelper(oActivity,nombreBD,null,version);
        SQLiteDatabase oBD=bdPrestamoOpenHelper.getReadableDatabase();
        Cursor oRegistros=oBD.rawQuery("select * from Libro",null);

        if (oRegistros.moveToFirst()){
            rpta=true;
            do{
                int id = oRegistros.getInt(0); // Suponiendo que el ID es el primer campo en tu tabla de libros
                String Titulo=oRegistros.getString(1);
                String Autor = oRegistros.getString(2);
                String Editorial = oRegistros.getString(3);
                String Genero = oRegistros.getString(4);
                String Idioma = oRegistros.getString(5);
                String FechaPublicacion = oRegistros.getString(6);
                byte[] Imagen= oRegistros.getBlob(7);
                int CantidadDisponible=oRegistros.getInt(8);
                int Stock=oRegistros.getInt(9);
                Libro libro = new Libro(id, Titulo, Autor, Editorial, Genero, Idioma, FechaPublicacion, Imagen, CantidadDisponible, Stock);
                listaLibros.add(libro);
            }while (oRegistros.moveToNext());
            oBD.close();
        }

        return rpta;
    }

    public boolean ModificarLibro(Activity oActivity, Libro oLibro, int id) {
        boolean rpta = false;
        BDPrestamoOpenHelper bdPrestamoOpenHelper = new BDPrestamoOpenHelper(oActivity, nombreBD, null, version);
        SQLiteDatabase oBD = bdPrestamoOpenHelper.getWritableDatabase();
        if (oBD != null) {
            ContentValues registro = new ContentValues();
            registro.put("titulo", oLibro.getTitulo());
            registro.put("autor", oLibro.getAutor());
            registro.put("editorial", oLibro.getEditorial());
            registro.put("genero", oLibro.getGenero());
            registro.put("idioma", oLibro.getIdioma());
            registro.put("FechaDePublicación", oLibro.getFechaPublicacion());
            registro.put("fotoLibro", oLibro.getImgLibro());
            registro.put("cantidadDisponible", oLibro.getCantidadDisponible());
            registro.put("stock", oLibro.getStock());

            // Corrige la sentencia SQL para usar el nombre correcto de la columna en la cláusula WHERE
            int filas = (int) oBD.update("Libro", registro, "IdLibro = ?", new String[]{String.valueOf(id)});
            oBD.close();
            if (filas > 0)
                rpta = true;
        }
        return rpta;
    }

    public Libro Buscar(Activity oActivity, int id){
        BDPrestamoOpenHelper bdLibrosOpenHelper = new BDPrestamoOpenHelper(oActivity,nombreBD,null,version);
        SQLiteDatabase oBD = bdLibrosOpenHelper.getReadableDatabase();
        Cursor oRegistros= oBD.rawQuery("select * from Libro where IdLibro="+id,null);
        Libro oLibro=null;

        if (oRegistros.moveToFirst()){
            String Titulo=oRegistros.getString(1);
            String Autor = oRegistros.getString(2);
            String Editorial = oRegistros.getString(3);
            String Genero = oRegistros.getString(4);
            String Idioma = oRegistros.getString(5);
            String FechaPublicacion = oRegistros.getString(6);
            byte[] Imagen= oRegistros.getBlob(7);
            int CantidadDisponible=oRegistros.getInt(8);
            int Stock=oRegistros.getInt(9);
            oLibro = new Libro(Titulo,Autor,Editorial,Genero,Idioma,FechaPublicacion,Imagen,CantidadDisponible,Stock);
        }
        return oLibro;
    }

    public ArrayList<Libro> listarLibro(){
        return listaLibros;
    }

    public int contarLibros(){
        return listaLibros.size();
    }

    public Libro obtenerLibro(int indice) {
        if (indice >= 0 && indice < listaLibros.size()) {
            return listaLibros.get(indice);
        } else {
            return null; // O puedes manejar este caso de otra manera, como lanzar una excepción
        }
    }

}
