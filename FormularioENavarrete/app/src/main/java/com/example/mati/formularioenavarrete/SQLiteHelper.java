package com.example.mati.formularioenavarrete;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    String pedidos = "CREATE TABLE IF NOT EXISTS 'pedidos' (" + "  'id' INTEGER NOT NULL PRIMARY KEY," + " " +
            " 'nombre' TEXT NOT NULL," + "  'precio' TEXT NOT NULL," + " 'iduser' TEXT NOT NULL,"
            + "  'sesion' TEXT NOT NULL," + "  'unidades' TEXT NOT NULL,"
            + "  'envio' TEXT NOT NULL," + "  'imagen' INTEGER NOT NULL,"+
                 "   FOREIGN KEY('iduser') REFERENCES usuarios('id') ON DELETE CASCADE" +
            "  );";
    String usuarios = "CREATE TABLE IF NOT EXISTS 'usuarios' (" +
            "  'id' INTEGER NOT NULL PRIMARY KEY," +
            "  'nombreusuario' TEXT NOT NULL UNIQUE," +
            "  'contra' TEXT NOT NULL," +
            "  'email' TEXT NOT NULL," +
            "  'ciudad' TEXT NOT NULL" +
            "  );";


    public SQLiteHelper(Context contexto, String nombre, SQLiteDatabase.CursorFactory basededatos, int version){
        super(contexto, nombre, basededatos, version);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(usuarios);
        db.execSQL(pedidos);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}




