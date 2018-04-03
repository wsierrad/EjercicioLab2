/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poo.lab1.panaderia.modelo;
import java.time.*;
/**
 *
 * @author willi_000
 */
public class Compra {
    private LocalDate fechaCompra;
    private double precio;
    private int cantidad;
    private Producto producto;

    public Compra(LocalDate fechaCompra, double precio, int cantidad, Producto producto) {
        this.fechaCompra = fechaCompra;
        this.precio = precio;
        this.cantidad = cantidad;
        this.producto = producto;
    }
    
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
    
    
    
}
