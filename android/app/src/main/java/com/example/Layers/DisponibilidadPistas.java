package com.example.Layers;

public class DisponibilidadPistas {

    private int id;
    private String dia, franjaHoraria;
    private int pagado, idUsuario,idPista;
    private Usuario usuario;
    private Pista pista;

    public DisponibilidadPistas(String dia, String franjaHoraria, int pagado, Usuario usuario, Pista pista) {
        this.dia = dia;
        this.franjaHoraria = franjaHoraria;
        this.usuario = usuario;
        this.pagado = pagado;
        this.pista = pista;
    }

    public DisponibilidadPistas(String dia, String franjaHoraria, int pagado, int usuario, int pista) {
        this.dia = dia;
        this.franjaHoraria = franjaHoraria;
        this.pagado = pagado;
        this.idUsuario = usuario;
        this.idPista = pista;
    }

    public DisponibilidadPistas(int id, String dia, String franjaHoraria,int pagado, int usuario, int pista) {
        this(dia, franjaHoraria,pagado, usuario, pista);
        this.id = id;
    }

    public DisponibilidadPistas(int id, String dia, String franjaHoraria, int pagado, Usuario usuario, Pista pista) {
        this(dia, franjaHoraria, pagado, usuario, pista);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getFranjaHoraria() {
        return franjaHoraria;
    }

    public void setFranjaHoraria(String franjaHoraria) {
        this.franjaHoraria = franjaHoraria;
    }

    public int getPagado() {
        return pagado;
    }

    public void setPagado(int pagado) {
        this.pagado = pagado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pista getPista() {
        return pista;
    }

    public void setPista(Pista pista) {
        this.pista = pista;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPista() {
        return idPista;
    }

    public void setIdPista(int idPista) {
        this.idPista = idPista;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", dia='" + dia + '\'' +
                ", franjaHoraria='" + franjaHoraria + '\'' +
                ", usuario=" + usuario +
                ", pista=" + pista;
    }
}
