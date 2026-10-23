

package com.mycompany.producto;

import java.util.Scanner;

public class Producto {
      private String nombre;
      private String categoria;
      private int codigo;
      private int cantidad;
      private double precio;
      
      static Scanner sc=new Scanner(System.in);
      static Producto[] productos=new  Producto[10];
      static int contador=0;
      static int opcion;

    public Producto(String nombre, String categoria, int codigo, int cantidad, double precio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.codigo = codigo;
        setPrecio(precio);
        setCantidad(cantidad);
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(double precio) {
        if(precio<0){
            System.out.println("Error ese precio no es valido");
            this.precio=0;
        }
        else{
            this.precio = precio;
        }
    }

    public void setCantidad(int cantidad) {
       if(cantidad<1){
           System.out.println("Error la cantidad debe ser mayor a 1 ya que se esta registrando un producto");
           this.cantidad =1; 
       }
       else{
            this.cantidad = cantidad;
       }
       
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }
    
    static void menu_principal(){
        
      do{
                  
            System.out.println("\n========== MENÚ INVENTARIO TECHSTORE ==========");
            System.out.println("1. Registrar Producto");
            System.out.println("2. Mostrar Productos (Recursivo)");
            System.out.println("3. Buscar Producto (Secuencial)");
            System.out.println("4. Buscar Producto (Binaria)");
            System.out.println("5. Ordenar por Precio (Burbuja)");
            System.out.println("6. Ordenar por Cantidad (Selección)");
            System.out.println("7. Ordenar por Nombre (Inserción)");
            System.out.println("8. Calcular Valor Total Inventario (Recursivo)");
            System.out.println("9. Contar por Categoría (Recursivo)");
            System.out.println("10. Encontrar Producto Más Costoso (Recursivo)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion=sc.nextInt();
            
            sc.nextLine();
            
            switch(opcion){
                case 1->{
                   registrar_producto(); 
                }
                
                case 2->{
                    if(contador==0){
                       System.out.println("No hay productos registrados"); 
                    }
                    else{
                        mostrar_productos( productos , 0);
                    }
                   
                }
                
                case 3->{
                   System.out.println("Ingrese el codigo del producto");  
                   int codigo_producto=sc.nextInt();
                   busqueda_secuencial(codigo_producto);
                 
                    
                }
                
                case 4->{
                     System.out.println("Ingrese el codigo del producto");  
                     int codigo_producto_buscar=sc.nextInt();
                    ordenar_codigos();
                     Producto encontrado = busqueda_binaria(productos, codigo_producto_buscar, 0, contador-1);
                   if(encontrado==null){
                    System.out.println("No hay producto con ese codigo");
                    } else {
                    System.out.println("Código: " + encontrado.getCodigo());
                    System.out.println("Nombre: "+ encontrado.getNombre());
                    System.out.println("Categoría: " + encontrado.getCategoria());
                    System.out.println("Precio: $" + encontrado.getPrecio());
                    System.out.println("Cantidad: " + encontrado.getCantidad());
                    }
                } 
                
                case 5->{
                    ordenar_precio();
                }
                
                case 6->{
                    ordenar_cantidad();
                }
                
                case 7->{
                    ordenarPorNombre();
                }
                
                case 8->{
                   if(contador==0){
                      System.out.println("no hay productos el cual calcular total del iventario");
                   }else{
                      double total = valor_total(productos, 0);
                      int unidades = total_unidades(productos, 0);
                      System.out.println("Valor total del inventario: $" + total);
                      System.out.println("Total de unidades en inventario: " + unidades);
                   }
                }
                
                case 9->{
                    System.out.println("Ingrese la categoria a la cual quiere saber cuantos productos tiene");
                    String categoria=sc.nextLine();
                    int resultado_categoria=conteo_categoria( productos,categoria,0);
                    System.out.println("Total de productos en la categoría" + " " +categoria + ":" + resultado_categoria);
                }
                
                case 10->{
                    if(contador==0){
                      System.out.println("no hay productos registrados");
                    }else{
                       Producto masCostoso = producto_costoso(productos, 0);
                       System.out.println("\n--- Producto Más Costoso ---");
                       System.out.println("Código: " + masCostoso.getCodigo());
                       System.out.println("Nombre: " + masCostoso.getNombre());
                       System.out.println("Categoría: " + masCostoso.getCategoria());
                       System.out.println("Precio: $" + masCostoso.getPrecio());
                       System.out.println("Cantidad: " + masCostoso.getCantidad());
                    }
                }
            }
      }while(opcion!=0);  
    }
    
    static boolean existe_codigo(int codigo){
        for(int i=0 ; i<contador; i++){
             if(productos[i].getCodigo()==codigo)
                 return true;
        }
        return false;
    }
    
    static void registrar_producto(){
        if(contador>=productos.length){
        System.out.println("Error: El inventario está lleno (máximo 10 productos).");
        return;
        }
        
        System.out.println("\n--- REGISTRAR NUEVO PRODUCTO ---");

        System.out.print("Ingrese el código: ");
        int codigo = sc.nextInt();
        sc.nextLine(); 
        
        while(existe_codigo(codigo)){
        System.out.println("\nEl codigo ingresado ya existe para un producto");   
        System.out.println("Ingrese el código: ");
        codigo = sc.nextInt();
        sc.nextLine(); 
        }

        System.out.println("Ingrese el nombre: ");
        String nombre = sc.nextLine();

        System.out.println("Ingrese la categoría: ");
        String categoria = sc.nextLine();

        System.out.println("Ingrese el precio: ");
        double precio = sc.nextDouble();

        System.out.println("Ingrese la cantidad: ");
        int cantidad = sc.nextInt();
        sc.nextLine();
        
        productos[contador]= new Producto(nombre, categoria, codigo, cantidad, precio);
        
        contador ++;
        
        System.out.println("producto registrado correctamente");
                
                
    }
    
    static void mostrar_productos(Producto[] productos,int posicion){
        if(posicion==contador){
             return;
        }
            System.out.println("Código: " + productos[posicion].getCodigo());
            System.out.println("Nombre: "+ productos[posicion].getNombre());
            System.out.println("Categoría: " + productos[posicion].getCategoria());
            System.out.println("Precio: $" + productos[posicion].getPrecio());
            System.out.println("Cantidad: " + productos[posicion].getCantidad());
            System.out.println("\n");
            
           
            
            mostrar_productos(productos,posicion+1);
            
    }
    
    static void busqueda_secuencial(int codigo_producto){
        boolean encontrado=false;
        for(int i=0; i<contador;i++ ){
            if(productos[i].getCodigo()==codigo_producto){
            System.out.println("Código: " + productos[i].getCodigo());
            System.out.println("Nombre: "+ productos[i].getNombre());
            System.out.println("Categoría: " + productos[i].getCategoria());
            System.out.println("Precio: $" + productos[i].getPrecio());
            System.out.println("Cantidad: " + productos[i].getCantidad());
            encontrado=true;
            }
              
        }
        if(!encontrado){
          System.out.println("No hay producto registrado con ese codigo");
        }
    }
    
    static void ordenar_precio(){
        if(contador==0){
          System.out.println("No hay productos registrado para ordenar");  
          return;
        }
        
        if (contador == 1) {
        System.out.println("Solo hay 1 producto registrado, ya está ordenado.");
        return;
       }
        
        for(int vuelta=0; vuelta<contador-1; vuelta++ ){
            for(int i=0; i<contador-vuelta-1;i++ ){
            if(productos[i].getPrecio()>productos[i+1].getPrecio()){
                Producto temporal=productos[i];
                productos[i]= productos[i+1];
                productos[i+1]=temporal;
            }
        }
     }
       for(int i=0; i<contador;i++ ){
            System.out.println("Código: " + productos[i].getCodigo());
            System.out.println("Nombre: "+ productos[i].getNombre());
            System.out.println("Categoría: " + productos[i].getCategoria());
            System.out.println("Precio: $" + productos[i].getPrecio());
            System.out.println("Cantidad: " + productos[i].getCantidad());
            System.out.println("\n");
           }
    }
    
    static void ordenar_codigos(){
        if(contador==0){
          System.out.println("No hay productos registrado para ordenar");  
          return;
        }
        
        if (contador == 1) {
        System.out.println("Solo hay 1 producto registrado, ya está ordenado.");
        return;
       }
        
        for(int vuelta=0; vuelta<contador-1; vuelta++ ){
            for(int i=0; i<contador-vuelta-1;i++ ){
            if(productos[i].getCodigo()>productos[i+1].getCodigo()){
                Producto temporal=productos[i];
                productos[i]= productos[i+1];
                productos[i+1]=temporal;
            }
        }
     }  
    }
    
    static Producto busqueda_binaria(Producto [] productos,int codigo_producto_buscar, int inicio, int fin){
      
         
        if(inicio>fin){
        return null; 
        }
         
  
        int medio=(fin+inicio)/2;
        if(productos[medio].getCodigo()==codigo_producto_buscar){
            return productos[medio];
           }
        if(productos[medio].getCodigo()<codigo_producto_buscar){
           return busqueda_binaria (productos,codigo_producto_buscar, medio+1, fin);
        } else{
        return busqueda_binaria(productos, codigo_producto_buscar, inicio, medio-1);
        }
       
    }
    
    static void ordenar_cantidad(){
        if (contador == 0) {
        System.out.println("No hay productos registrados para ordenar.");
        return;
        }
        
        if (contador == 1) {
        System.out.println("Solo hay 1 producto registrado, ya está ordenado.");
        return;
        }
        
        for(int i=0; i<contador-1; i++){
            int cantidad_minima=i;
            for(int j=i+1; j<contador; j++){
                if(productos[j].getCantidad()<productos[cantidad_minima].getCantidad()){
                    cantidad_minima=j;
                }   
            }
            if(cantidad_minima!=i){
                  Producto temporal=productos[i];
                  productos[i]= productos[cantidad_minima];
                  productos[cantidad_minima]=temporal;
            }
        }
           for(int i=0; i<contador;i++ ){
            System.out.println("Código: " + productos[i].getCodigo());
            System.out.println("Nombre: "+ productos[i].getNombre());
            System.out.println("Categoría: " + productos[i].getCategoria());
            System.out.println("Precio: $" + productos[i].getPrecio());
            System.out.println("Cantidad: " + productos[i].getCantidad());
            System.out.println("\n");
           }
    }
    //este
    static void ordenarPorNombre(){
        if (contador == 0) {
        System.out.println("No hay productos registrados para ordenar.");
        return;
        }
        if (contador == 1) {
        System.out.println("Solo hay 1 producto registrado, ya está ordenado.");
        return;
        }
        
        for(int i = 1; i < contador; i++){
        Producto actual = productos[i];
        int j = i - 1;
        
        while(j >= 0 && productos[j].getNombre().compareToIgnoreCase(actual.getNombre()) > 0){
            productos[j + 1] = productos[j];
            j--;
        }
        
        productos[j + 1] = actual;
        }
        
         for(int i=0; i<contador;i++ ){
            System.out.println("Código: " + productos[i].getCodigo());
            System.out.println("Nombre: "+ productos[i].getNombre());
            System.out.println("Categoría: " + productos[i].getCategoria());
            System.out.println("Precio: $" + productos[i].getPrecio());
            System.out.println("Cantidad: " + productos[i].getCantidad());
            System.out.println("\n");
           }
    }
    
   static int total_unidades(Producto[] productos, int posicion){
    if(posicion==contador){
        return 0;
    }
    return productos[posicion].getCantidad() + total_unidades(productos, posicion+1);
    }
   
    static double valor_total(Producto[] productos, int posicion){
    if(posicion==contador){
        return 0;
    }
    double valor_actual = productos[posicion].getCantidad() * productos[posicion].getPrecio();
    return valor_actual + valor_total(productos, posicion+1);
    }
    
    static int conteo_categoria(Producto [] productos, String categoria,int posicion){
        
        if(posicion==contador){
            return 0;
        }
        int aporte;
        
        if(productos[posicion].getCategoria().equalsIgnoreCase(categoria)){
            System.out.println("Nombre:"+ productos[posicion].getNombre());
            aporte=1;
        }else{
           aporte=0;
        }
        return aporte + conteo_categoria(productos,categoria,posicion+1); 
    }
    
    static Producto producto_costoso(Producto [] productos,int posicion){
      if(contador-1==posicion){
      return productos[posicion];
      }
      
      Producto mayorResto = producto_costoso(productos, posicion + 1);

      if(productos[posicion].getPrecio() > mayorResto.getPrecio()){
         return productos[posicion];
    } else {
        return mayorResto;
    }
    }
    
    
    
    
 
    public static void main(String[] args) {
        
       menu_principal();
    }
}
