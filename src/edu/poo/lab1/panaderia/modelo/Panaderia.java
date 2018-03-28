/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poo.lab1.panaderia.modelo;
import java.util.*;
import java.time.*;

/**
 *
 * @author willi_000
 */
public class Panaderia {
    public static String nombre;
    public static String ubicacion;
    public static String horario;
    private final ArrayList <Venta> ventas;
    private final ArrayList <Compra> compras;
    private final ArrayList <VentaMayorista> ventasMayoristas;
    private final HashMap <String, Producto> inventario;
    
    public double valorGanancias(LocalDate fechaInicio, LocalDate fechaFin){ 
        double ganacia = this.valorVentas(fechaInicio, fechaFin) - 
                this.valorCompras(fechaInicio, fechaFin);
        return ganacia;
    }
    
    public double valorVentas(LocalDate fechaInicio, LocalDate fechaFin){ 
        double valorVentas = 0;
        for (Venta venta : ventas) {
            LocalDate f = venta.getFechaVenta();
            if (f.isAfter(fechaInicio) && f.isBefore(fechaFin)){
                valorVentas+=venta.getValor();
            }
        }
        for (VentaMayorista venta : ventasMayoristas) {
            LocalDate f = venta.getFechaVenta();
            if (f.isAfter(fechaInicio) && f.isBefore(fechaFin)){
                valorVentas+=venta.getPrecio();
            }
        }
        return valorVentas;
    }
    
    public double valorCompras(LocalDate fechaInicio, LocalDate fechaFin){ 
        double valorCompras = 0;
        for (Compra compra : compras) {
            LocalDate f = compra.getFechaCompra();
            if (f.isAfter(fechaInicio) && f.isBefore(fechaFin)){
                valorCompras+=compra.getPrecio();
            }
        }
        return valorCompras;
    }

    public ArrayList<Venta> getVentasPeriodo (LocalDate fechaInicio, LocalDate fechaFin){
        ArrayList<Venta> ventasPeriodo = new ArrayList<>();
        for (Venta venta : ventas) {
            LocalDate f = venta.getFechaVenta();
            if (f.isAfter(fechaInicio) && f.isBefore(fechaFin))
                ventasPeriodo.add(venta);
        }
        return ventasPeriodo;
    }
    
    public ArrayList<VentaMayorista> getVentasMPeriodo (LocalDate fechaInicio, LocalDate fechaFin){
        ArrayList<VentaMayorista> ventasPeriodo = new ArrayList<>();
        for (VentaMayorista venta : ventasMayoristas) {
            LocalDate f = venta.getFechaVenta();
            if (f.isAfter(fechaInicio) && f.isBefore(fechaFin))
                ventasPeriodo.add(venta);
        }
        return ventasPeriodo;
    }
    
    public ArrayList<Compra> getComprasPeriodo (LocalDate fechaInicio, LocalDate fechaFin){
        ArrayList<Compra> comprasPeriodo = new ArrayList<>();
        for (Compra compra : compras) {
            LocalDate f = compra.getFechaCompra();
            if (f.isAfter(fechaInicio) && f.isBefore(fechaFin))
                comprasPeriodo.add(compra);
        }
        return comprasPeriodo;
    }
    
    public Panaderia(String nombre, String ubicacion, String horario) {
        Panaderia.nombre = nombre;
        Panaderia.ubicacion = ubicacion;
        Panaderia.horario = horario;
        ventas = new ArrayList<>();
        compras = new ArrayList<>();
        ventasMayoristas = new ArrayList<>();
        inventario = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getHorario() {
        return horario;
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public boolean addVenta(Producto produtoVenta, 
                        int cantidad,
                        LocalDate fechaVenta) {
        Venta venta = new Venta(produtoVenta, cantidad, fechaVenta);
        return ventas.add(venta);
    }

    public ArrayList<VentaMayorista> getVentasMayoristas() {
        return ventasMayoristas;
    }

    public boolean addVentaMayorista(Producto produtoVenta, 
                        int cantidad,
                        LocalDate fechaVenta,
                        String nombreCliente,
                        double precio) {
        VentaMayorista ventaMayorista = new VentaMayorista(nombreCliente, 
                precio, produtoVenta, cantidad, fechaVenta);
        return ventasMayoristas.add(ventaMayorista);
    }
    
    public HashMap<String, Producto> getInventario() {
        return inventario;
    }

    public boolean addProducto(Producto producto) {
        Producto p = inventario.put(producto.getNombreProducto(), producto);
        return p.equals(producto);
    }
    
    public boolean removeProducto(Producto producto) {
        return inventario.remove(producto.getNombreProducto(),producto);
    }
    
    public Producto consultaInventario (String nombre) {
        return inventario.get(nombre);
    }

    public ArrayList<Compra> getCompras() {
        return compras;
    } 
    
}
