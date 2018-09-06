/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author leoflores
 */
public class CompuertaProductos {
    
    private static final String PROD_ID = "id";
    private static final String PROD_MARCA = "marca";
    private static final String PROD_NOM_MARCA = "marcanom";
    private static final String PROD_DESC = "descripcion";
    private static final String PROD_PRECIO = "precio";
    private static final String PROD_CANT = "cantidad";
    private static final String SELECT_PRODUCTOS = "SELECT id, (SELECT nombre FROM marcas WHERE id=marca) marcanom, descripcion, precio, cantidad FROM Productos";
    private static final String POR_ID = " WHERE id=?";
    private static final String POR_MARCA = " WHERE marca=?";
    private static final String POR_NOMBRE_MARCA = " WHERE marca=(SELECT id FROM Marcas WHERE nombre=?)";
    private static final String POR_DESCRIPCION = " WHERE descripcion LIKE %?%";
    private static final String DELETE_PRODUCTO = "DELETE * FROM Productos WHERE id=?";
    
    public ArrayList<Producto> obtenerTodos(){
        ArrayList<Producto> prods = new ArrayList();
        Connection con = Conexion.getConnection();
        Statement stmt =  null;
        ResultSet rs = null;
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SELECT_PRODUCTOS);
            
            while(rs.next()){
                Producto p = toProducto(rs);
                prods.add(p);
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            prods = null;
            System.err.println(e.getMessage());
        } finally {
            Conexion.cerrarConexion(con);
        }
        
        return prods;
    }
    
    public Producto obtenerPorID(int id){
        Producto prod = null;
        Connection con = Conexion.getConnection();
        PreparedStatement stmt =  null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement(SELECT_PRODUCTOS+POR_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                prod = toProducto(rs);
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            Conexion.cerrarConexion(con);
        }
        
        return prod;
    }
    
    public ArrayList<Producto> obtenerPorMarca(int marca){
        ArrayList<Producto> prods = new ArrayList();
        Connection con = Conexion.getConnection();
        PreparedStatement stmt =  null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement(SELECT_PRODUCTOS+POR_MARCA);
            stmt.setInt(1, marca);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Producto p = toProducto(rs);
                prods.add(p);
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            prods = null;
            System.err.println(e.getMessage());
        } finally {
            Conexion.cerrarConexion(con);
        }
        
        return prods;
    }
    
    public ArrayList<Producto> obtenerPorNombreMarca(String marca){
        ArrayList<Producto> prods = new ArrayList();
        Connection con = Conexion.getConnection();
        PreparedStatement stmt =  null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement(SELECT_PRODUCTOS+POR_NOMBRE_MARCA);
            stmt.setString(1, marca);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Producto p = toProducto(rs);
                prods.add(p);
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            prods = null;
            System.err.println(e.getMessage());
        } finally {
            Conexion.cerrarConexion(con);
        }
        
        return prods;
    }
    
    public ArrayList<Producto> obtenerPorDescripcion(String des){
        ArrayList<Producto> prods = new ArrayList();
        Connection con = Conexion.getConnection();
        PreparedStatement stmt =  null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement(SELECT_PRODUCTOS+POR_DESCRIPCION);
            stmt.setString(1, des);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Producto p = toProducto(rs);
                prods.add(p);
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            prods = null;
            System.err.println(e.getMessage());
        } finally {
            Conexion.cerrarConexion(con);
        }
        
        return prods;
    }
    
    public boolean eliminarProducto(int id){
        boolean res = false;
        Connection con = Conexion.getConnection();
        PreparedStatement stmt =  null;
        int count = 0;
        
        try {
            stmt = con.prepareStatement(DELETE_PRODUCTO);
            stmt.setInt(1, id);
            count = stmt.executeUpdate();
            
            if(count > 0){
                res = true;
            }
            
            stmt.close();
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            Conexion.cerrarConexion(con);
        }
        return res;
    }

    private Producto toProducto(ResultSet rs) {
        Producto p = new Producto();
        
        try {
            p.setId(rs.getInt(PROD_ID));
            p.setMarca(rs.getNString(PROD_NOM_MARCA));
            p.setDescripcion(rs.getNString(PROD_DESC));
            p.setPrecio(rs.getFloat(PROD_PRECIO));
            p.setCantidad(rs.getInt(PROD_CANT));
        } catch (SQLException ex) {
            p = null;
        }
        return p;
    }
    
}
