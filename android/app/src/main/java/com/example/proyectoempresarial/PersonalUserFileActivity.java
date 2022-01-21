package com.example.proyectoempresarial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.Layers.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonalUserFileActivity extends AppCompatActivity {

    private Menu menu;
    private EditText etNombre, etDNI, etEmail, etDireccion, etTelefono, etEdad, ePistas;
    private List<EditText> editTextList;
    private ImageView imgView;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        ePistas = findViewById(R.id.ePistas);
        etNombre = findViewById(R.id.etNombreApellidos);
        etDNI = findViewById(R.id.etDNI);
        etEmail = findViewById(R.id.etEmail);
        etDireccion = findViewById(R.id.etDireccion);
        etTelefono = findViewById(R.id.etTelefono);
        etEdad = findViewById(R.id.etEdad);

        editTextList = Arrays.asList(etNombre, etDNI, etEmail, etDireccion, etTelefono, etEdad);

        imgView = findViewById(R.id.imgView);

        GetImageFromURL loadImagen = new GetImageFromURL();
        loadImagen.execute("https://thispersondoesnotexist.com/image");


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        UsuarioDAO dbUsuarios = new UsuarioDAO(PersonalUserFileActivity.this);
        Usuario usuario = UsuarioDAO.dameUsuario(id);

        if (usuario != null) {
            etNombre.setText(usuario.getNombre() + " " + usuario.getApellidos());
            etDNI.setText(usuario.getDni());
            etEmail.setText(usuario.getEmail());
            etEdad.setText(String.valueOf(usuario.getEdad()));
            etDireccion.setText(usuario.getDireccion());
            etTelefono.setText(String.valueOf(usuario.getTelefono()));
            rellenarPistas();
            noEditable();
        }

        ePistas.setFocusable(false);
    }

    private void noEditable() {

        for (EditText i : this.editTextList) {
            i.setInputType(InputType.TYPE_NULL);
        }

        ePistas.setInputType(InputType.TYPE_NULL);
    }

    private void editables() {
        etEdad.setInputType(InputType.TYPE_CLASS_NUMBER);
        etDNI.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
        etEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        etTelefono.setInputType(InputType.TYPE_CLASS_NUMBER);
        etNombre.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        etDireccion.setInputType(InputType.TYPE_TEXT_VARIATION_POSTAL_ADDRESS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                eventoDelete();
                break;
            case R.id.edit:
                editables();
                menu.clear();
                getMenuInflater().inflate(R.menu.menu2, menu);
                break;
            case R.id.update:
                if(eventoUpdate()) {
                    noEditable();
                    menu.clear();
                    getMenuInflater().inflate(R.menu.menu1, menu);
                    quitarFoco();
                }
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                super.finish();
                break;
        }
        return true;
    }

    public void rellenarPistas() {
        DisponibilidadPistasDAO bd = new DisponibilidadPistasDAO(this);
        ArrayList<DisponibilidadPistas> pistasAlquiladas = bd.damePistaAlquilada(id);
        if (pistasAlquiladas.isEmpty()) {
            ePistas.setText("Este usuario no ha alquilado ninguna pista");
        } else {
            ePistas.setSingleLine(false);
            for (int i = 0; i < pistasAlquiladas.size(); i++) {
                ePistas.append(pistasAlquiladas.get(i).getDia() + " -> " + pistasAlquiladas.get(i).getFranjaHoraria());
                ePistas.append(i != pistasAlquiladas.size() - 1 ? System.getProperty("line.separator"): "");//Faltan saltos de linea
            }
        }
        bd.close();
    }

    public void eventoDelete() {
        UsuarioDAO.eliminarUsuario(id);
        startActivity(new Intent(PersonalUserFileActivity.this, UsersActivity.class));
        Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show();
        super.finish();
    }

    public boolean eventoUpdate() {

        if (sinCamposVacios()) {
            String nombre = etNombre.getText().toString().trim().split(" ")[0];
            String apellidos = etNombre.getText().toString().trim().split(" ")[1];
            String dni = etDNI.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String direccion = etDireccion.getText().toString().trim();
            String telefono = etTelefono.getText().toString().trim();
            String edad = etEdad.getText().toString().trim();
            UsuarioDAO.editarUsuario(new Usuario(id, nombre, apellidos, dni, Integer.parseInt(telefono), direccion, email, Integer.parseInt(edad),0));
            Toast.makeText(this, "Datos modificados", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private boolean sinCamposVacios() {
        for (EditText i : this.editTextList) {
            if (i.getText().toString().equals("")) {
                i.setError("Requerido");
                return false;
            }
        }
        return true;
    }

    private void quitarFoco(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    private class GetImageFromURL extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... url) {
            Bitmap bitmap = null;
            try {
                InputStream srt = new java.net.URL(url[0]).openStream();
                bitmap = BitmapFactory.decodeStream(srt);
            } catch (Exception e) {
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap bitmap) {
            imgView.setImageBitmap(bitmap);
        }
    }
}