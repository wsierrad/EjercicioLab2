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
    private String nombre;
    private String ubicacion;
    private String horario;
    private final ArrayList <Venta> ventas;
    private final ArrayList <Compra> compras;
    private final ArrayList <VentaMayorista> ventasMayoristas;
    private final HashMap <String, Producto> inventario;
    
    public double valorGanancias(LocalDate fechaInicio, LocalDate fechaFin){ 
        LocalDate FI = fechaInicio.minusDays(1);
        LocalDate FF = fechaFin.plusDays(1);
        double ganacia = this.valorVentas(FI, FF) - 
                this.valorCompras(FI, FF);
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
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.horario = horario;
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

    public boolean addVenta(Producto productoVenta, 
                        int cantidad,
                        LocalDate fechaVenta) {
        
        Venta venta=null;
        int remanentes;
        if(productoVenta!=null){
            Producto p = this.consultaInventario(productoVenta.getNombreProducto());
            venta = new Venta(productoVenta, cantidad, fechaVenta);
            remanentes = p.getCantidad();
            productoVenta.setCantidad(remanentes - cantidad);
            this.inventario.remove(p.getNombreProducto());
            this.inventario.put(productoVenta.getNombreProducto(), productoVenta);
        }
        if (venta == null)
            return false;
        else return ventas.add(venta);
    }

    public ArrayList<VentaMayorista> getVentasMayoristas() {
        return ventasMayoristas;
    }

    public boolean addVentaMayorista(Producto productoVenta, 
        int cantidad,
        LocalDate fechaVenta,
        String nombreCliente,
        double precio) {
        VentaMayorista ventaMayorista=null;
        int remanentes;
        if(productoVenta!=null){
            //System.out.println("entro");
            Producto p = this.consultaInventario(productoVenta.getNombreProducto());
            ventaMayorista = new VentaMayorista(nombreCliente, 
                precio, productoVenta, cantidad, fechaVenta);
            remanentes = p.getCantidad();
            productoVenta.setCantidad(remanentes - cantidad);
            this.inventario.remove(p.getNombreProducto());
            this.inventario.put(productoVenta.getNombreProducto(), productoVenta);
        }
        if (ventaMayorista == null)
            return false;
        else return ventasMayoristas.add(ventaMayorista);
    }
    
    public HashMap<String, Producto> getInventario() {
        return inventario;
    }

    public boolean addProducto(Producto producto) {
        boolean result; 
        Producto p = inventario.put(producto.getNombreProducto(), producto); 
        if (p !=null){
            String n1 = p.getNombreProducto();
            String n2 = producto.getNombreProducto();
            result=n1.equals(n2);
        }else result = false;
        return result;
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
    
    public boolean addCompra(LocalDate fechaCompra, double precio, int cantidad, Producto producto) {
        Compra c=null;
        int remanentes;
        if(producto!=null){
            //System.out.println("entro");
            Producto p = this.consultaInventario(producto.getNombreProducto());
            c = new Compra(fechaCompra, precio, cantidad, producto);
            remanentes = p.getCantidad();
            producto.setCantidad(remanentes + cantidad);
            this.inventario.remove(p.getNombreProducto());
            this.inventario.put(producto.getNombreProducto(), producto);
        }
        if (c==null)
            return false;
        else return this.compras.add(c);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    
    
}
