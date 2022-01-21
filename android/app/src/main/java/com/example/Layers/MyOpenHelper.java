package com.example.Layers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "padelclub.db";
    private static final int DB_VERSION = 1;
    protected static SQLiteDatabase db;

    private static Context context;

    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE administradores (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario TEXT NOT NULL," +
                "contrasenia TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "apellidos TEXT NOT NULL," +
                "dni TEXT NOT NULL," +
                "telefono INTEGER NOT NULL," +
                "direccion TEXT NOT NULL," +
                "email TEXT NOT NULL," +
                "edad INTEGER NOT NULL," +
                "dado_de_baja BOOLEAN NOT NULL CHECK (dado_de_baja IN (0,1)))");

        sqLiteDatabase.execSQL("CREATE TABLE pistas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "numPista INTEGER NOT NULL," +
                "en_mantenimiento BOOLEAN NOT NULL CHECK (en_mantenimiento IN (0,1)))");

        sqLiteDatabase.execSQL("CREATE TABLE disponibilidad_pistas (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "dia TEXT NOT NULL," +
                "franjaHoraria TEXT NOT NULL," +
                "pagado BOOLEAN NOT NULL CHECK (pagado IN (0,1))," +
                "idUsuario INTEGER NOT NULL," +
                "idPista INTEGER NOT NULL," +
                "FOREIGN KEY(idUsuario) REFERENCES usuarios(id)," +
                "FOREIGN KEY(idPista) REFERENCES pistas(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE administradores");
        sqLiteDatabase.execSQL("DROP TABLE pistas");
        sqLiteDatabase.execSQL("DROP TABLE usuarios");
        sqLiteDatabase.execSQL("DROP TABLE disponibilidad_pistas");
        onCreate(sqLiteDatabase);
    }

    public static void dameBD() {
        MyOpenHelper dbHelper = new MyOpenHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static void cerrarBD() {
        db.close();
    }
}
