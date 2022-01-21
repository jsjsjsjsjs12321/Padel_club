package com.example.Layers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class UsuarioDAO extends MyOpenHelper {

    private final static String TABLE = "usuarios";

    public UsuarioDAO(Context context) {
        super(context);
        dameBD();
    }

    public long insertarUsuarios(Usuario nuevoUsuario) {

        long resultado = 0;
        ArrayList<Usuario> clientesExistentes = mostrarUsuarios();

        if (!clientesExistentes.contains(nuevoUsuario)) {
            try {
                ContentValues values = new ContentValues();

                values.put("nombre", nuevoUsuario.getNombre());
                values.put("apellidos", nuevoUsuario.getApellidos());
                values.put("dni", nuevoUsuario.getDni());
                values.put("telefono", nuevoUsuario.getTelefono());
                values.put("direccion", nuevoUsuario.getDireccion());
                values.put("email", nuevoUsuario.getEmail());
                values.put("edad", nuevoUsuario.getEdad());
                values.put("dado_de_baja", nuevoUsuario.getDe_baja());

                resultado = db.insert(TABLE, null, values);
            } catch (Exception ex) {
            }
        }
        return resultado;
    }

    public ArrayList<Usuario> mostrarUsuarios() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE + " ORDER BY nombre ASC", null);
        Usuario usuario;

        if (cursor.moveToFirst()) {
            do {
                usuario = new Usuario(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getInt(8));
                listaUsuarios.add(usuario);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return listaUsuarios;
    }

    public static Usuario dameUsuario(int id) {

        if (db == null) {
            dameBD();
        }

        Usuario usuario = null;
        Cursor cursor;

        cursor = db.rawQuery("SELECT * FROM " + TABLE + " WHERE id = " + id + " LIMIT 1", null);

        if (cursor.moveToFirst()) {
            usuario = new Usuario(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7), cursor.getInt(8));
        }
        cursor.close();
        return usuario;
    }

    public static boolean eliminarUsuario(int id) {
        boolean correcto;
        if (db == null) {
            dameBD();
        }

        try {
            db.execSQL("DELETE FROM " + TABLE + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            correcto = false;
        }

        cerrarBD();
        return correcto;
    }


    public static int editarUsuario(Usuario usuario) {

        int resultado = 0;

        if (db == null) {
            dameBD();
        }

        try {

            ContentValues registro = new ContentValues();
            registro.put("id", usuario.getId());
            registro.put("nombre", usuario.getNombre());
            registro.put("apellidos", usuario.getApellidos());
            registro.put("dni", usuario.getDni());
            registro.put("telefono", usuario.getTelefono());
            registro.put("direccion", usuario.getDireccion());
            registro.put("email", usuario.getEmail());
            registro.put("edad", usuario.getEdad());
            registro.put("dado_de_baja", usuario.getDe_baja());

            resultado = db.update("usuarios", registro, "id = " + usuario.getId(), null);
        } catch (Exception ex) {
        }

        return resultado;
    }
}