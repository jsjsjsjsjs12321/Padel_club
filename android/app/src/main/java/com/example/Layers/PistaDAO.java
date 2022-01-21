package com.example.Layers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class PistaDAO extends MyOpenHelper {

    private final static String TABLE = "pistas";

    public PistaDAO(Context context) {
        super(context);
        dameBD();
    }

    public long insertarPista(Pista pista) {

        long resultado = 0;
        ArrayList<Pista> pistasExistentes = mostrarPistas();

        if (!pistasExistentes.contains(pista)) {
            try {
                ContentValues values = new ContentValues();
                values.put("numPista", pista.getNumPista());
                values.put("en_mantenimiento", pista.getEn_mantenimiento());

                resultado = db.insert(TABLE, null, values);
            } catch (Exception ex) {
            }
        }
        return resultado;
    }

    public ArrayList<Pista> mostrarPistas() {
        ArrayList<Pista> listaPistas = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                listaPistas.add(new Pista(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaPistas;
    }

    public static Pista damePista(int id) {

        Pista pista = null;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + TABLE + " WHERE id = " + id + " LIMIT 1", null);

        if (cursor.moveToFirst()) {
            pista = new Pista(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2));
        }

        cursor.close();

        return pista;
    }
}
