package com.example.Layers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class DisponibilidadPistasDAO extends MyOpenHelper {

    private final static String TABLE = "disponibilidad_pistas";

    public DisponibilidadPistasDAO(Context context) {
        super(context);
        dameBD();
    }

    public long insertarDisponibilidadPista(DisponibilidadPistas disponibilidad) {

        long resultado = 0;
        ArrayList<DisponibilidadPistas> disponibilidadesExistentes = mostrarDisponibilidades();

        if (!disponibilidadesExistentes.contains(disponibilidad)) {
            try {
                ContentValues values = new ContentValues();
                values.put("dia", disponibilidad.getDia());
                values.put("franjaHoraria", disponibilidad.getFranjaHoraria());
                values.put("pagado", disponibilidad.getPagado());
                values.put("idUsuario", disponibilidad.getIdUsuario());
                values.put("idPista", disponibilidad.getIdPista());
                resultado = db.insert(TABLE, null, values);
            } catch (Exception ex) {
            }
        }
        return resultado;
    }

    public ArrayList<DisponibilidadPistas> mostrarDisponibilidades() {
        ArrayList<DisponibilidadPistas> listaPistas = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE + " ORDER BY id ASC", null);
        DisponibilidadPistas disponibilidadesPistas;

        if (cursor.moveToFirst()) {
            do {
                disponibilidadesPistas = new DisponibilidadPistas(cursor.getString(0), cursor.getString(1),cursor.getInt(2), UsuarioDAO.dameUsuario(cursor.getInt(3)), PistaDAO.damePista(cursor.getInt(4)));
                listaPistas.add(disponibilidadesPistas);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return listaPistas;
    }

    public static ArrayList<DisponibilidadPistas> damePistaAlquilada(int idUsuario) {

        ArrayList<DisponibilidadPistas> pistasAlquiladas = new ArrayList<>();
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + TABLE + " WHERE idUsuario = " + idUsuario, null);

        if (cursor.moveToFirst()) {
            do {
                pistasAlquiladas.add(new DisponibilidadPistas(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getInt(3), cursor.getInt(4), cursor.getInt(5)));
            } while (cursor.moveToNext());
        }

        cursor.close();

        return pistasAlquiladas;
    }
}
