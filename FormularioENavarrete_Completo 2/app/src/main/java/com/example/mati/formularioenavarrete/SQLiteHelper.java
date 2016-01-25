package com.example.mati.formularioenavarrete;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    String crearTablaU = "CREATE TABLE IF NOT EXISTS 'usuarios' (" +
            "  'id' INTEGER NOT NULL PRIMARY KEY," +
            "  'usuario' TEXT NOT NULL UNIQUE," +
            "  'clave' TEXT NOT NULL" +
            "  );";

    String crearTablaPedidos = "CREATE TABLE IF NOT EXISTS 'pedidos' (" +
            "  'id' INTEGER NOT NULL PRIMARY KEY," +
            "  'nombre' TEXT NOT NULL," +
            "  'precio' TEXT NOT NULL," +
            "  'sesion' TEXT NOT NULL," +
            "  'unidades' TEXT NOT NULL," +
            "  'envio' TEXT NOT NULL," +
            "  'imagen' INTEGER NOT NULL" +
            "  );";

    public SQLiteHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory basededatos, int version){
        super(contexto, nombre, basededatos, version);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crearTablaU);
        db.execSQL(crearTablaPedidos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}




