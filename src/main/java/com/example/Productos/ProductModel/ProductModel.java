package com.example.Productos.ProductModel;

import jakarta.persistence.*;


@Entity
@Table(name= "producto")
public class ProductModel {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(unique = true)
     private Long id;
     private String nombre;
     private String categoria;
     private int valor;
    public ProductModel() {
    }
    public ProductModel(Long id, String nombre, String categoria, int valor) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.valor = valor;
    }

    public ProductModel(String nombre, String categoria, int valor) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
