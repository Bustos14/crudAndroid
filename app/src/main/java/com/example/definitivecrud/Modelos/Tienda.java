package com.example.definitivecrud.Modelos;

public class Tienda {
    private String nomLocal;
    private String telefono;
    private String tipo;


    public String getNomLocal() {
        return nomLocal;
    }

    public void setNomLocal(String nomLocal) {
        this.nomLocal = nomLocal;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Tienda(String nomLocal, String tipo, String telefono) {
        this.nomLocal = nomLocal;
        this.tipo = tipo;
        this.telefono = telefono;
    }
    public Tienda() {

    }



}
