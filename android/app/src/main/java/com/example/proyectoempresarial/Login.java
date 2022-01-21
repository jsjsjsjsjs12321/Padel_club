package com.example.proyectoempresarial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Layers.*;

import java.util.List;


public class Login extends AppCompatActivity {

    private EditText etUsuario, etContrasenia;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_administrador);

        etUsuario = findViewById(R.id.etUsuario);
        etContrasenia = findViewById(R.id.etContrasenia);
        btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this::eventoBoton);


        if(!FDP.existeBD(this, MyOpenHelper.DB_NAME)){
            insertarDatosDeEjemplo(getApplicationContext());
        }
    }

    private void existeAdmin(Administrador admin) {
        AdministradorDAO ad = new AdministradorDAO(this);
        List<Administrador> administradoresExistentes = ad.mostrarAdministradores();
        for (Administrador i : administradoresExistentes) {
            if (i.equals(admin)) {
                startActivity(new Intent(this, UsersActivity.class));
                super.finish();
                return;
            }
        }
        Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
        etUsuario.setText("");
        etContrasenia.setText("");
    }

    private void eventoBoton(View view) {
        String usuario = etUsuario.getText().toString(), contrasenia = etContrasenia.getText().toString();

        if (usuario.isEmpty()) {
            etUsuario.setError("Requerido");
            return;
        } else if (contrasenia.isEmpty()) {
            etContrasenia.setError("Requerido");
            return;
        }
        existeAdmin(new Administrador(usuario, contrasenia));
    }


    public static void insertarDatosDeEjemplo(Context context) {

        //Administradores que pueden acceder a la informacion de los clientes
        AdministradorDAO ad = new AdministradorDAO(context);
        ad.insertarAdministrador(new Administrador("David1", "qwerty"));
        ad.insertarAdministrador(new Administrador("Alvaro", "qazwsx"));
        ad.insertarAdministrador(new Administrador("David2", "1234"));
        ad.close();

        UsuarioDAO c = new UsuarioDAO(context);
        c.insertarUsuarios(new Usuario("Mofo", "ヽ(^o^)ノ", "99237235Q", 681913002, "C/ Lulu", "jlaporta@fcbarcelona.com", 59,0));
        c.insertarUsuarios(new Usuario("Kerry", "Kaberga", "75843954A", 642357528, "C/ LibProcessGroup", "lm10@psgftw.com", 34,0));
        c.insertarUsuarios(new Usuario("Eulogio", "♫♪♩ヾ(*◎○◎)ﾉ♫♪♩", "75946852Q", 61129101, "C/ Regex", "tm@gmail.com", 10,0));
        c.insertarUsuarios(new Usuario("4fs", "(✌ﾟ∀ﾟ)☞", "642184837Z", 642357528, "C/ src", "cm@hotmail.com", 5,0));
        c.insertarUsuarios(new Usuario("jk", "(๑‵●‿●‵๑)", "6184329S", 606560789, "HVGA slider", "mm@icloud.com", 7,0));
        c.cerrarBD();

        PistaDAO p = new PistaDAO(context);
        for (int i = 1; i <= 3; i++) {
            p.insertarPista(new Pista(i,0));
        }
        p.cerrarBD();

        DisponibilidadPistasDAO d = new DisponibilidadPistasDAO(context);
        d.insertarDisponibilidadPista(new DisponibilidadPistas("Lunes", "12:30-13:30",0,1,1));
        d.insertarDisponibilidadPista(new DisponibilidadPistas("Lunes", "18:30-20:30",0,1,1));
        d.insertarDisponibilidadPista(new DisponibilidadPistas("Miercoles", "11:30-12:30",0,2,2));
        d.insertarDisponibilidadPista(new DisponibilidadPistas("Juernes", "19:30-20:30",0,3,3));

        d.cerrarBD();
    }
}