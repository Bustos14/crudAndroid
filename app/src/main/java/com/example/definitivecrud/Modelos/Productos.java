package com.example.definitivecrud.Modelos;

public class Productos {
    String nomProducto;
    String descripcion;
    String precio;
    String stock;
    String nomLocal;

    public String getNomLocal() {
        return nomLocal;
    }

    public void setNomLocal(String nomLocal) {
        this.nomLocal = nomLocal;
    }

    public Productos(String nomProducto, String descripcion, String precio, String stock, String nomLocal) {
        this.nomProducto = nomProducto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.nomLocal = nomLocal;
    }

    public Productos() {

    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }


}
