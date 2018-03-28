/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poo.lab1.panaderia.modelo;

import java.time.*;
import java.util.ArrayList;

/**
 *
 * @author willi_000
 */
public class Promocion {
    private int descuento;
    private LocalDate inicio;
    private LocalDate fin;
    private ArrayList <Producto> productos;

    public Promocion(int descuento, LocalDate inicio, LocalDate fin, ArrayList<Producto> productos) {
        this.descuento = descuento;
        this.inicio = inicio;
        this.fin = fin;
        this.productos = productos;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
    
    
}
