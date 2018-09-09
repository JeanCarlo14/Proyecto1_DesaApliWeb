/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Conexiones.Producto;

/**
 *
 * @author Ana
 */
<<<<<<< HEAD
public class ItemCarrito {

    private int Id;
    private Carrito carrito;
    private Producto producto;
    private int Cantidad;
    /**
     * @return the Id
     */

=======
public class ItemCarrito { 
    /**
     * @return the Id
     */
>>>>>>> origin/master
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    private String imagenProducto;
    private String nombreProducto;
    private String Descripcion;
    private float precio;
    private int cantidad; // cantidad del item
    /**
     * @return the Id
     */
    
}
