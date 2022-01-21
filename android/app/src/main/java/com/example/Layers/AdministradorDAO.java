package com.example.Layers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class AdministradorDAO extends MyOpenHelper{

    private final static String TABLE = "administradores";

    public AdministradorDAO(Context context) {
        super(context);
        dameBD();
    }

    public long insertarAdministrador(Administrador administrador) {

        long resultado = 0;
        ArrayList<Administrador> administradoresExistentes = mostrarAdministradores();

        if (!administradoresExistentes.contains(administrador)) {
            try {
                ContentValues values = new ContentValues();
                values.put("usuario", administrador.getUsuario());
                values.put("contrasenia", administrador.getContrasenia());
                resultado = db.insert(TABLE, null, values);
            } catch (Exception ex) {
            }
        }
        return resultado;
    }

    public ArrayList<Administrador> mostrarAdministradores() {
        ArrayList<Administrador> listaAdministradores = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                listaAdministradores.add(new Administrador(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaAdministradores;
    }
}
