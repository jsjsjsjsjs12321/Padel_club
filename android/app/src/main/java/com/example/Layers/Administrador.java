package com.example.Layers;

import java.util.Objects;

public class Administrador {

    private int id;
    private String usuario, contrasenia;

    public Administrador(int id, String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public Administrador(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrador administrador = (Administrador) o;
        return usuario.equals(administrador.getUsuario()) && contrasenia.equals(administrador.getContrasenia());
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario);
    }
}
