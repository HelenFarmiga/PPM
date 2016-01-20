package com.example.mati.formularioenavarrete;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Pedidos " + "(id INTEGER PRIMARY KEY, Nombre TEXT, Precio DOUBLE," +
                "sesion TEXT, unidades INTEGER, envio TEXT,Imagen INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionPosterior) {
        db.execSQL("DROP TABLE IF EXISTS Pedidos");
        db.execSQL("CREATE TABLE Pedidos " + "(id INTEGER PRIMARY KEY, Nombre TEXT, Precio DOUBLE, " +
                "sesion TEXT, unidades INTEGER, envio TEXT,Imagen INTEGER)");
    }
    }
