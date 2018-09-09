/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

import static Conexiones.Conexion.getConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author nacho
 */
public class ManagerServlet {
    
public ArrayList<Producto> consultarProductos() {
		Producto producto = new Producto();
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
               // Conexion connection = new Conexion();
               String sql;
                CallableStatement callableStatement = null;
                ResultSet resultSet = null;
                Connection connection = getConnection();
		try {
                    
			if (connection != null) {
				sql = "{call PA001()}";
				callableStatement = connection.prepareCall(sql);
				resultSet = callableStatement.executeQuery();
                              //  System.out.print(resultSet.next());
				while (resultSet.next()) {
                                    //System.out.println(resultSet.next());
					producto = new Producto();
                                        producto.setId(resultSet.getInt("id"));
                                        producto.setMarca(resultSet.getString("marca"));
                                        producto.setDescripcion(resultSet.getString("DESCRIPCION"));
                                        producto.setPrecio(resultSet.getInt("PRECIO"));
                                        producto.setCantidad(resultSet.getInt("CANTIDAD"));
                                        producto.setImagen(resultSet.getString("IMAGEN"));

					listaProductos.add(producto);
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			//cmdMessage.SqlErrMessage("ManagerFacturar", "consultarReceptores", sqle);
		} finally {
			try {
				if (callableStatement != null) {
					callableStatement.close();
					callableStatement = null;
				}
				if (connection != null && !connection.isClosed()) {
					connection.close();
					connection = null;
				}
				if (resultSet != null) {
					resultSet.close();
					resultSet = null;
				}
			} catch (SQLException e) {
				//cmdMessage.SqlErrMessage("ManagerFacturar", "consultarReceptores", e);
			}
		}
		return listaProductos;
	}


public Producto consultarProductoEspecifico(int id) {
		Producto producto = new Producto();
		String sql;
                CallableStatement callableStatement = null;
                ResultSet resultSet = null;
                Connection connection = getConnection();
		try {
                    
			if (connection != null) {
				sql = "{call PA018(?)}";
				callableStatement = connection.prepareCall(sql);
                                callableStatement.setInt(1, id);
				resultSet = callableStatement.executeQuery();
                              //  System.out.print(resultSet.next());
				while (resultSet.next()) {
                                    //System.out.println(resultSet.next());
					producto = new Producto();
                                        producto.setId(resultSet.getInt("id"));
                                        producto.setMarca(resultSet.getString("marca"));
                                        producto.setDescripcion(resultSet.getString("DESCRIPCION"));
                                        producto.setPrecio(resultSet.getInt("PRECIO"));
                                        producto.setCantidad(resultSet.getInt("CANTIDAD"));
                                        producto.setImagen(resultSet.getString("IMAGEN"));

				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			//cmdMessage.SqlErrMessage("ManagerFacturar", "consultarReceptores", sqle);
		} finally {
			try {
				if (callableStatement != null) {
					callableStatement.close();
					callableStatement = null;
				}
				if (connection != null && !connection.isClosed()) {
					connection.close();
					connection = null;
				}
				if (resultSet != null) {
					resultSet.close();
					resultSet = null;
				}
			} catch (SQLException e) {
				//cmdMessage.SqlErrMessage("ManagerFacturar", "consultarReceptores", e);
			}
		}
		return producto;
	}

    
}
