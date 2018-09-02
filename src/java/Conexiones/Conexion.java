/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
    
    static Connection con = null;
    static String Servidor = "localhost";
    static String Database = "BD_Project1";
    static String UserName = "sa";
    static String Password = "123";
    
    public static Connection getConnection(){
        String url = "jdbc:sqlserver://"+Servidor+":1433;databaseName="+Database;
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch (ClassNotFoundException e){
            System.err.println("No se pudo establecer la conexion... revisar Driver "+e.getMessage());
        }
        try{
            Properties connectionProps = new Properties();
            connectionProps.put("user", UserName);
            connectionProps.put("password", Password);

            con = DriverManager.getConnection(url,connectionProps);
        }
        catch (SQLException e){
            System.err.println("Error "+e.getMessage());
        }        
        return con;
    }
    
    public void cerrarConexion() {
        try {
            con.close();
        } catch (SQLException e) {
          System.err.println("Error al cerrar la conexión "+e.getMessage());
        }
    }
    
    
}
