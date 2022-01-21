package com.example.Layers;

import java.util.Objects;

public class Pista {

    private int id, numPista, en_mantenimiento;

    public Pista(int numPista, int en_mantenimiento) {
        this.numPista = numPista;
        this.en_mantenimiento = en_mantenimiento;
    }

    public Pista(int id, int numPista, int en_mantenimiento) {
        this(numPista, en_mantenimiento);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumPista() {
        return numPista;
    }

    public void setNumPista(int numPista) {
        this.numPista = numPista;
    }

    public int getEn_mantenimiento() {
        return en_mantenimiento;
    }

    public void setEn_mantenimiento(int en_mantenimiento) {
        this.en_mantenimiento = en_mantenimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pista pista = (Pista) o;
        return numPista == pista.numPista;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numPista);
    }

    @Override
    public String toString() {
        return "id=" + id + ", numPista=" + numPista;
    }
}
