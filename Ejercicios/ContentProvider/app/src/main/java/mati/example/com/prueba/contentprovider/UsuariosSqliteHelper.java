package mati.example.com.prueba.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuariosSqliteHelper extends SQLiteOpenHelper{

    String sql = "CREATE TABLE Usuarios " +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "usuario TEXT, " +
            "password TEXT, " +
            "email TEXT )";

    public UsuariosSqliteHelper(Context contexto, String nombre, CursorFactory almacen, int version) {
        super(contexto, nombre, almacen, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sql);
        for (int i=1; i<=10; i++) {
            String usuario = "usuario" + i;
            String password = "passw" + i;
            String email = "email" + i + "@cefire.com";
            db.execSQL("INSERT INTO Usuarios (usuario, password, email) " +
                    " VALUES ('" + usuario + "', '" + password + "', '" +
                    email + "')");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //Eliminamos la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL(sql);
    }
}