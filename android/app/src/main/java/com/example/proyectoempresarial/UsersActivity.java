package com.example.proyectoempresarial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import com.example.Layers.*;

public class UsersActivity extends AppCompatActivity {

    private SearchView buscador;
    private RecyclerView listaClientes;
    private UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buscador = findViewById(R.id.buscador);
        listaClientes = findViewById(R.id.listaClientes);
        listaClientes.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UsersAdapter(new UsuarioDAO(this).mostrarUsuarios());
        listaClientes.setAdapter(adapter);
        buscador.setQueryHint("Filtrar por DNI");
        eventoBuscador();
    }

    private void eventoBuscador() {
        buscador.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filtrarClientes(newText);
                return false;
            }
        });
    }
}