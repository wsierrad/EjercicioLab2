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
public class VentaMayorista extends Venta {
    private String nombreCliente;
    private double precio;

    public VentaMayorista(String nombreCliente, double precio, 
            Producto producto, int cantidad, LocalDate fechaVenta) {
        super(producto, cantidad, fechaVenta);
        this.nombreCliente = nombreCliente;
        this.precio = precio;
        super.setValor(precio/cantidad);
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
    
}
