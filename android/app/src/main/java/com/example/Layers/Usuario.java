package com.example.Layers;

import java.util.Objects;

public class Usuario {
    private int id, telefono, edad, de_baja;
    private String nombre, apellidos, dni, direccion, email;

    public Usuario(String nombre, String apellidos, String dni, int telefono, String direccion, String email, int edad, int de_baja) {
        this.telefono = telefono;
        this.edad = edad;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.email = email;
        this.de_baja = de_baja;
    }

    public Usuario(int id, String nombre, String apellidos, String dni, int telefono, String direccion, String email, int edad, int de_baja) {
        this(nombre, apellidos, dni, telefono, direccion, email, edad, de_baja);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDe_baja() {
        return de_baja;
    }

    public void setDe_baja(int de_baja) {
        this.de_baja = de_baja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(dni.toLowerCase(), usuario.dni.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", telefono=" + telefono +
                ", edad=" + edad +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", dni='" + dni + '\'' +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                ", de_baja='" + de_baja;
    }
}
