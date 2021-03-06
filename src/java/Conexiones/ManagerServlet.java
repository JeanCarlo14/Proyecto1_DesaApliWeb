/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

import Model.Producto;
import static Conexiones.Conexion.getConnection;
import Model.Carrito;
import Model.Item;
import Model.ItemCarrito;
import Model.TarjetaNombre;
import Model.Usuario;
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

    public ArrayList<Producto> consultarProductos(String filtro) {
        Producto producto = new Producto();
        ArrayList<Producto> listaProductos = new ArrayList<Producto>();
        // Conexion connection = new Conexion();
        String sql;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {

            if (connection != null) {
                sql = "{call PA001(?)}";
                callableStatement = connection.prepareCall(sql);
                callableStatement.setString(1, filtro);
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

    public boolean agregarItem(Item item) throws SQLException {

        boolean flag = true;
        String sql;
        CallableStatement callableStatement = null;

        Connection connection = getConnection();
        try {
            connection = getConnection();
            if (connection != null) {
                sql = "{call PA012(?,?,?)}";
                callableStatement = connection.prepareCall(sql);
                callableStatement.setInt(1, item.getCarrito().getId());
                callableStatement.setInt(2, item.getProducto().getId());
                callableStatement.setInt(3, item.getCantidad());

                callableStatement.execute();
            }
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
                callableStatement = null;
            }
            if (connection != null && connection.isClosed()) {
                connection.close();
                connection = null;
            }
        }
        return flag;
    }

    public ArrayList<ItemCarrito> consultarItemsCarrito(int idCarrito) {
        ItemCarrito itemCarrito = new ItemCarrito();
        ArrayList<ItemCarrito> listaItemCarrito = new ArrayList<ItemCarrito>();
        // Conexion connection = new Conexion();
        String sql;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {

            if (connection != null) {
                sql = "{call PA019(?)}";
                callableStatement = connection.prepareCall(sql);
                callableStatement.setInt(1, idCarrito);
                resultSet = callableStatement.executeQuery();
                //  System.out.print(resultSet.next());
                while (resultSet.next()) {
                    //System.out.println(resultSet.next());
                    itemCarrito = new ItemCarrito();
                    itemCarrito.setId(resultSet.getInt("id"));
                    itemCarrito.setImagenProducto(resultSet.getString("imagen"));
                    itemCarrito.setDescripcion(resultSet.getString("descripcion"));
                    itemCarrito.setPrecio(resultSet.getFloat("precio"));
                    itemCarrito.setCantidad(resultSet.getInt("cantidad"));

                    listaItemCarrito.add(itemCarrito);
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
        return listaItemCarrito;
    }

    public boolean eliminarItem(int idItem) throws SQLException {
        boolean flag = true;
        String sql;
        CallableStatement callableStatement = null;

        Connection connection = getConnection();
        try {
            connection = getConnection();
            if (connection != null) {
                sql = "{call PA015(?)}";
                callableStatement = connection.prepareCall(sql);
                callableStatement.setInt(1, idItem);

                callableStatement.execute();
            }
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
                callableStatement = null;
            }
            if (connection != null && connection.isClosed()) {
                connection.close();
                connection = null;
            }
        }
        return flag;
    }

    public boolean createUser(Usuario user) throws SQLException {
        System.out.println(user.getNombre());
        System.out.println(user.getEmail());
        boolean flag = true;
        String sql;
        CallableStatement callableStatement = null;

        Connection connection = getConnection();
        try {
            connection = getConnection();
            if (connection != null) {
                sql = "{call PA003(?,?,?,?,?)}";
                callableStatement = connection.prepareCall(sql);
                callableStatement.setString(1, user.getNombre());
                callableStatement.setString(2, user.getApellido1());
                callableStatement.setString(3, user.getApellido2());
                callableStatement.setString(4, user.getEmail());
                callableStatement.setString(5, user.getPass());
                callableStatement.execute();
            }
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
                callableStatement = null;
            }
            if (connection != null && connection.isClosed()) {
                connection.close();
                connection = null;
            }
        }
        return flag;
    }

    public Usuario UserLogin(String email, String pass) {
        Usuario usuario = new Usuario();
        String sql;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {

            if (connection != null) {
                sql = "{call PA020(?,?)}";
                callableStatement = connection.prepareCall(sql);
                callableStatement.setString(1, email);
                callableStatement.setString(2, pass);
                resultSet = callableStatement.executeQuery();
                //  System.out.print(resultSet.next());
                while (resultSet.next()) {
                    //System.out.println(resultSet.next());
                    usuario = new Usuario();
                    usuario.setNombre(resultSet.getString("Nombre"));
                    usuario.setApellido1(resultSet.getString("Apellido1"));
                    usuario.setApellido2(resultSet.getString("Apellido2"));
                    usuario.setEmail(resultSet.getString("Email"));
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
        return usuario;
    }

    public Carrito CarritoUsuario(String email) {
        Carrito carrito = new Carrito();
        String sql;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {

            if (connection != null) {
                sql = "{call PA010(?)}";
                callableStatement = connection.prepareCall(sql);
                callableStatement.setString(1, email);
                resultSet = callableStatement.executeQuery();
                //  System.out.print(resultSet.next());
                while (resultSet.next()) {
                    carrito = new Carrito();
                    Usuario u = new Usuario();
                    u.setEmail(resultSet.getString("Usuario"));
                    carrito.setUsuario(u);
                    carrito.setId(Integer.parseInt(resultSet.getString("Id")));
                    carrito.setCheckout(Boolean.parseBoolean(resultSet.getString("Checkout")));
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
        return carrito;
    }

    public boolean actualizarItem(int idItem, int cantidad) throws SQLException {

        boolean flag = true;
        String sql;
        CallableStatement callableStatement = null;

        Connection connection = getConnection();
        try {
            connection = getConnection();
            if (connection != null) {
                sql = "{call PA014(?,?)}";
                callableStatement = connection.prepareCall(sql);
                callableStatement.setInt(1, idItem);
                callableStatement.setInt(2, cantidad);
                callableStatement.execute();
            }
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
                callableStatement = null;
            }
            if (connection != null && connection.isClosed()) {
                connection.close();
                connection = null;
            }
        }
        return flag;
    }

    public ArrayList<TarjetaNombre> consultarTarjetasUsuario(String idUsuario) {
        TarjetaNombre tarjeta;
        ArrayList<TarjetaNombre> listaIdTarjetas = new ArrayList();
        String sql;
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;
        Connection connection = getConnection();
        try {

            if (connection != null) {
                sql = "{call PA007(?)}";
                callableStatement = connection.prepareCall(sql);
                callableStatement.setString(1, idUsuario);
                resultSet = callableStatement.executeQuery();
                while (resultSet.next()) {
                    tarjeta = new TarjetaNombre();
                    tarjeta.setNumero(resultSet.getInt("numero"));
                    tarjeta.setUsuario(resultSet.getString("nombre"));
                    tarjeta.setFecha_Exp(resultSet.getDate("fecha_exp"));
                    tarjeta.setCcv(resultSet.getInt("ccv"));

                    listaIdTarjetas.add(tarjeta);
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
        return listaIdTarjetas;
    }

    public boolean actualizarCarrito(int carrito, boolean i) throws SQLException {
        boolean flag = true;
        String sql;
        CallableStatement callableStatement = null;

        Connection connection = getConnection();
        try {
            connection = getConnection();
            if (connection != null) {
                sql = "{call PA009(?,?)}";
                callableStatement = connection.prepareCall(sql);
                callableStatement.setInt(1, carrito);
                callableStatement.setBoolean(2, i);
                callableStatement.execute();
            }
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
                callableStatement = null;
            }
            if (connection != null && connection.isClosed()) {
                connection.close();
                connection = null;
            }
        }
        return flag;
    }

    public boolean crearCarrito(String username) throws SQLException {
        boolean flag = true;
        String sql;
        CallableStatement callableStatement = null;

        Connection connection = getConnection();
        try {
            connection = getConnection();
            if (connection != null) {
                sql = "{call PA008(?,?)}";
                callableStatement = connection.prepareCall(sql);
                callableStatement.setString(1, username);
                callableStatement.setInt(2, 0);
                callableStatement.execute();
            }
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
                callableStatement = null;
            }
            if (connection != null && connection.isClosed()) {
                connection.close();
                connection = null;
            }
        }
        return flag;
    }

    public boolean agregarTarjeta(TarjetaNombre tarjeta) throws SQLException {
        boolean flag = true;
        String sql;
        CallableStatement callableStatement = null;

        Connection connection = getConnection();
        try {
            connection = getConnection();
            if (connection != null) {
                sql = "{call PA005(?,?,?,?)}";
                callableStatement = connection.prepareCall(sql);
                callableStatement.setInt(1, tarjeta.getNumero());
                callableStatement.setString(2, tarjeta.getUsuario());
                callableStatement.setDate(3, tarjeta.getFecha_Exp());
                callableStatement.setInt(4, tarjeta.getCcv());
                callableStatement.execute();
            }
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        } finally {
            if (callableStatement != null) {
                callableStatement.close();
                callableStatement = null;
            }
            if (connection != null && connection.isClosed()) {
                connection.close();
                connection = null;
            }
        }
        return flag;
    }

}
