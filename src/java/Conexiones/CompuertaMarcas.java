/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;

/**
 *
 * @author leoflores
 */
public class CompuertaMarcas {
    
    private static final String MARCA_ID = "id";
    private static final String MARCA_NOM = "nombre";
    private static final String SELECT_PRODUCTOS = "SELECT * FROM Marcas";
    
    public ArrayList<SimpleImmutableEntry> obtenerMarcas(){
        ArrayList<SimpleImmutableEntry> marcas = new ArrayList();
        Connection con = Conexion.getConnection();
        Statement stmt;
        ResultSet rs;
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SELECT_PRODUCTOS);
            
            while(rs.next()){
                SimpleImmutableEntry m = getEntry(rs);
                marcas.add(m);
            }
            
            rs.close();
            stmt.close();
            
        } catch (SQLException e) {
            marcas = null;
            System.err.println(e.getMessage());
        } finally {
            Conexion.cerrarConexion(con);
        }
        
        return marcas;
    }

    private SimpleImmutableEntry getEntry(ResultSet rs) {
        SimpleImmutableEntry e;
        try {
            e = new SimpleImmutableEntry(rs.getInt(MARCA_ID), rs.getNString(MARCA_NOM));
        } catch (SQLException ex) {
            e = null;
            System.err.println(ex.getMessage());
        }
        return e;
    }
}
