/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poo.lab1.panaderia.vista;
import edu.poo.lab1.panaderia.modelo.*;
import java.time.*;
import java.util.*;

/**
 *
 * @author willi_000
 */
public class Principal {
   
    static Panaderia p;
   
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion;
        System.out.println("Bienvenido\nPor favor ingrese los datos de la panaderia: ");
        System.out.print("Ingrese el nombre: ");
        String nombre = teclado.nextLine();
        System.out.print("Ingrese la direccion: ");
        String dir = teclado.nextLine();
        System.out.print("Ingrese los horarios de atención: ");
        String horarios = teclado.nextLine();
        p = new Panaderia(nombre,dir,horarios);
        do{
            opcion = selecionarOpcion();
            switch(opcion){
                case 1 : 
                     //Ingresar Venta
                    System.out.println("Bienvenido al menu de registro de ventas ");
                    menuVentas();
                    break;
                    
                case 2 : 
                      //Registra compra
                    System.out.println("Bienvenido al menu de registro de compras ");
                    menuCompras();
                    break;
                
                case 3 : 
                     //Mostrar el inventario de productos
                    HashMap productos = p.getInventario();
                    List<Producto> inventario = new ArrayList<>(productos.values());
                    System.out.println("Nombre\tCantidad\tPrecio");
                    for(Producto p : inventario){
                        System.out.println(p.getNombreProducto()+"\t"+
                                p.getCantidad()+"\t\t"+p.getPrecio());
                    }
                    break;
                case 4 : 
                     //Retornar e imprimir ganacias del dia
                    System.out.println("Bienvenido al menu de caja");
                    double caja = p.valorGanancias(LocalDate.now(), LocalDate.now());
                    System.out.println("El valor del dia de hoy de la caja es de: " + caja);
                    break;
                case 5 : 
                     //Revisar ganacias
                    System.out.println("Bienvenido al menu de ganacias: ");
                    System.out.println("Ingrese el dia desde el que quiere contar sus ganacias: ");
                    LocalDate FI = formatoFecha();
                    System.out.println("Ingrese el dia hasta el que quiere contar sus ganacias: ");
                    LocalDate FF = formatoFecha();
                    double ganancias = p.valorGanancias(FI, FF);
                    System.out.println("Sus ganacias han sido de: " + ganancias);
                    break;
                case 6 : 
                      //Cambiar Datos panaderia
                    int opcionD;
                    do{
                        opcionD = selecionarOpcionDatos();
                        switch (opcionD){
                        case 1:
                            System.out.print("Ingrese el nuevo nombre: ");
                            String nombrePan = teclado.nextLine();
                            p.setNombre(nombrePan);
                            break;
                        case 2:
                            System.out.print("Ingrese la nueva dirrección: ");
                            String ubicacion = teclado.nextLine();
                            p.setUbicacion(ubicacion);
                            break;
                        case 3:
                            System.out.print("Ingrese el nuevo horario: ");
                            String horario = teclado.nextLine();
                            p.setHorario(horario);
                            break;
                        default:
                            break;
                    }
                    }while(opcionD!=4);              
                    break;
                case 7:
                    ArrayList<Venta> ventas = p.getVentas();
                    System.out.println("Nombre\tCantidad\tPrecio\tFecha");
                    for(Venta p : ventas){
                        System.out.println(p.getProducto().getNombreProducto()+"\t"+
                                p.getCantidad()+"\t\t"+p.getValor()+ "\t" + p.getFechaVenta());
                    }
                    
                    break;
                case 8:
                    ArrayList<VentaMayorista> ventasM = p.getVentasMayoristas();
                    System.out.println("Nombre\tCantidad\tPrecio\tFecha");
                    for(Venta p : ventasM) {
                        System.out.println(p.getProducto().getNombreProducto()+"\t"+
                                p.getCantidad()+"\t\t"+p.getValor()+ "\t" + p.getFechaVenta());
                    }
                    break;
                case 9:
                    ArrayList<Compra> compras = p.getCompras();
                    System.out.println("Nombre\tCantidad\tPrecio\tFecha");
                    for(Compra p : compras){
                        System.out.println(p.getProducto().getNombreProducto()+"\t"+
                                p.getCantidad()+"\t\t"+p.getPrecio()+ "\t" + p.getFechaCompra());
                    }
                    break;
                default:
                   break;
            }
         }while(opcion != 10);
    }
    
    public static int selecionarOpcion(){
        Scanner teclado = new Scanner(System.in);
        int opcion = -1; 
        System.out.println(p.getNombre() + "\t" + p.getUbicacion());
        System.out.println("Presione: ");
        System.out.print("1.Registrar Venta\n2.Registrar Compra a proveedor\n3.Ver inventario");
        System.out.print("\n4.Ver estado de caja\n5.Revisar ganancias\n6.Cambiar datos Panaderia");
        System.out.print("\n7.Ver todas las ventas\n8.Ver todas las ventas Mayoristas");
        System.out.println("\n9.Ver todas las compras\n10.Salir");
        System.out.println("Horarios\t"+p.getHorario());
        opcion = teclado.nextInt();
        return opcion;  
    }
    
    public static int selecionarOpcionDatos(){
        Scanner teclado = new Scanner(System.in);
        int opcion = -1; 
        System.out.println("Bienvenido a la configuracion de los datos del negocio");
        System.out.println("Seleccione la opcion que quiera cambiar: ");
        System.out.print("1.Nombre panaderia\n2.Dirrecion\n3.Horarios");
        System.out.println("\n4.Volver");
        opcion = teclado.nextInt();
        return opcion;  
    }
    
    public static void menuCompras(){
        Scanner teclado = new Scanner(System.in);
        System.out.print("Ingrese el nombre del producto a buscar: ");
        String nombreP = teclado.nextLine();
        Producto productoCompra = p.consultaInventario(nombreP);
        if (productoCompra==null){
            System.out.println("Producto no encontrado. Se registrar como un producto nuevo");
            System.out.println("Nombre del producto: " + nombreP);
            System.out.println("Ingrese el precio de venta de cada unidad del producto");
            double precio1 = teclado.nextDouble();
            Producto pr = new Producto(nombreP, precio1, 0);
            if(pr.getNombreProducto() != null)
                p.addProducto(pr);
            productoCompra=pr;
        }else System.out.println("El producto es: " + 
                productoCompra.getNombreProducto());
        System.out.println("¿Cuántas unidades compro de este producto?");
        int cant = teclado.nextInt();
        System.out.println("Compro el producto el dia de hoy: (S/N)");
        String SiNo = teclado.next();
        LocalDate FC;
        if (SiNo.equals("s") || SiNo.equals("S") )
            FC = LocalDate.now();
        else{
            FC = formatoFecha();
        }
        System.out.println("Ingrese el valor total de la compra de este producto: ");
        double precio = teclado.nextDouble();
        boolean result = p.addCompra(FC, precio, cant, productoCompra);
        if(result == true){
            System.out.println("Ingreso exitoso");
        }else{
            System.out.println("Problema al realizar la insercion");
        }
    }
    
    public static Producto buscaProducto(){
        Scanner teclado = new Scanner(System.in);
        Producto producto;
        System.out.print("Ingrese el nombre del producto a buscar: ");
        String nombreP = teclado.nextLine();
        producto = p.consultaInventario(nombreP);
        return producto;
    }
    
    public static LocalDate formatoFecha(){
    Scanner teclado = new Scanner(System.in);
    System.out.println("Ingrese la fecha separada por espacios en el siguiente formato (aaaa mm dd)");
    String fecha = teclado.nextLine();
    String[] f = fecha.split(" ");
    LocalDate FV = LocalDate.of(Integer.parseInt(f[0]),Integer.parseInt(f[1]),Integer.parseInt(f[2]));  
    return FV;
}

public static void menuVentas() {
    Scanner teclado = new Scanner(System.in);
    Producto productoVenta;
    do{
    productoVenta = buscaProducto();
    if(productoVenta==null)
        System.out.println("Producto no encontrado");
    }while(productoVenta == null);
    System.out.println("¿Cuántas unidades vendio de este producto?");
    int cant = teclado.nextInt();
    System.out.println("Vendio el producto el dia de hoy: (S/N)");
    String SiNo = teclado.next();
    LocalDate FV;
    if (SiNo.equals("s") || SiNo.equals("S") )
        FV = LocalDate.now();
    else{
        FV = formatoFecha();
    }
    System.out.println("Es una venta al por mayor (S/N)");
    SiNo = teclado.next();
    boolean result;
    double precio;
    if (SiNo.equals("s")|| SiNo.equals("S")){
        System.out.println("Ingrese el nombre del cliente: ");
        String cliente = teclado.nextLine();
        System.out.println("Ingrese el precio total de la venta: ");
        precio = teclado.nextDouble();
        result = p.addVentaMayorista(productoVenta, cant, FV, cliente, precio);
    }
    else
         result = p.addVenta(productoVenta, cant, FV);                     
    if(result == true)
        System.out.println("Ingreso exitoso");
    else
        System.out.println("Problema al realizar la insercion");
    }
}
