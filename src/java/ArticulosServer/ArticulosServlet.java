/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArticulosServer;

import Conexiones.ManagerServlet;
import Model.Producto;
import Model.Carrito;
import Model.Item;
import Model.ItemCarrito;
import Model.Tarjeta;
import Model.TarjetaNombre;
import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author nacho
 */
public class ArticulosServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String mensaje = "";
            if (request.getParameter("accion") != null && request.getParameter("accion") != "") {
                String accion = request.getParameter("accion");

                switch (accion) {
                    case "a":
                        mensaje = cargarListaProductos(request, response);
                        break;
                    case "b":
                        mensaje = cargarProductoEspecifico(request, response);
                        break;
                    case "c":
                        mensaje = agregarItem(request, response);
                        break;
                    case "d":
                        mensaje = cargarListaCarrito(request, response);
                        break;
                    case "e":
                        mensaje = eliminarItem(request, response);
                        break;
                    case "f":
                        mensaje = actualizarItem(request, response);
                        break;
                    case "l":
                        mensaje = cargarListaTarjetas(request, response);
                        break;
                    case "m":
                        mensaje = checkout(request, response);
                        break;
                    case "n":
                        mensaje = agregarTarjeta(request, response);
                        break;

                    case "createUser":
                        mensaje = createUser(request, response);
                        break;

                    case "userLogin":
                        mensaje = userLogin(request, response);
                        break;

                    case "logout":
                        mensaje = logout(request, response);
                        break;

                    default:
                        mensaje = "sin accion";
                }
            } else {
                mensaje = "Sin accion";
            }

            out.println(mensaje);

        }
    }

    public String metodo1(HttpServletRequest request, HttpServletResponse response) {

        JSONObject nodoData = new JSONObject();
        nodoData.put("respuesta", "metodo1");
        nodoData.put("estado", true);
        return nodoData.toString();
    }

    public String metodo2(HttpServletRequest request, HttpServletResponse response) {

        JSONObject nodoData = new JSONObject();
        nodoData.put("respuesta", "metodo2");
        nodoData.put("estado", true);
        return nodoData.toString();
    }

    public String metodo3(HttpServletRequest request, HttpServletResponse response) {

        JSONObject nodoData = new JSONObject();
        nodoData.put("respuesta", "metodo3");
        nodoData.put("estado", true);
        return nodoData.toString();
    }

    private String cargarListaProductos(HttpServletRequest request, HttpServletResponse response) {
        /* Formato JSON */
        response.setContentType("application/json, charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        JSONArray jsonArr = new JSONArray();
        JSONObject nodoProducto = new JSONObject();

        ManagerServlet mngServlet = new ManagerServlet();
        ArrayList<Producto> listaProductos = mngServlet.consultarProductos(request.getParameter("filtro"));

        for (int i = 0; i < listaProductos.size(); i++) {
            nodoProducto = new JSONObject();
            nodoProducto.put("id", listaProductos.get(i).getId());
            nodoProducto.put("marca", listaProductos.get(i).getMarca());
            nodoProducto.put("descripcion", listaProductos.get(i).getDescripcion());
            nodoProducto.put("precio", listaProductos.get(i).getPrecio());
            nodoProducto.put("cantidad", listaProductos.get(i).getCantidad());
            nodoProducto.put("imagen", listaProductos.get(i).getImagen());
            jsonArr.add(nodoProducto);
        }

        JSONObject mainObj = new JSONObject();
        //try {
        mainObj.put("productos", jsonArr);
        //mainObj.put("total", mngFacturar.getMaximo());

        return mainObj.toString();
        /*} catch (JSONException e) {
			e.printStackTrace();
			return "{}";
		}	*/
    }

    private String cargarProductoEspecifico(HttpServletRequest request, HttpServletResponse response) {
        /* Formato JSON */
        response.setContentType("application/json, charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        JSONArray jsonArr = new JSONArray();
        JSONObject nodoProducto = new JSONObject();

        ManagerServlet mngServlet = new ManagerServlet();
        Producto producto = mngServlet.consultarProductoEspecifico(Integer.parseInt(request.getParameter("idProducto")));

        nodoProducto = new JSONObject();
        nodoProducto.put("id", producto.getId());
        nodoProducto.put("marca", producto.getMarca());
        nodoProducto.put("descripcion", producto.getDescripcion());
        nodoProducto.put("precio", producto.getPrecio());
        nodoProducto.put("cantidad", producto.getCantidad());
        nodoProducto.put("imagen", producto.getImagen());
        jsonArr.add(nodoProducto);

        JSONObject mainObj = new JSONObject();
        //try {
        mainObj.put("producto", jsonArr);
        //mainObj.put("total", mngFacturar.getMaximo());

        return nodoProducto.toString();
        /*} catch (JSONException e) {
			e.printStackTrace();
			return "{}";
		}	*/
    }

    private String agregarItem(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        /* Formato JSON */

        response.setContentType("application/json, charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        JSONObject nodoItem = new JSONObject();
        nodoItem.put("estado", false);

        ManagerServlet mngServlet = new ManagerServlet();
        Item item = new Item();
        Carrito carrito = new Carrito();
        Producto producto = new Producto();

        carrito.setId(Integer.parseInt(request.getParameter("idCarrito")));
        producto.setId(Integer.parseInt(request.getParameter("idProducto")));

        item.setCarrito(carrito);
        item.setProducto(producto);
        item.setCantidad(Integer.parseInt(request.getParameter("cantidad")));

        if (mngServlet.agregarItem(item)) {
            nodoItem.put("estado", true);
        }

        return nodoItem.toString();
    }

    private String cargarListaCarrito(HttpServletRequest request, HttpServletResponse response) {
        /* Formato JSON */
        response.setContentType("application/json, charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        JSONArray jsonArr = new JSONArray();
        JSONObject nodoItemCarrito = new JSONObject();

        ManagerServlet mngServlet = new ManagerServlet();
        ArrayList<ItemCarrito> listaItemCarrito = mngServlet.consultarItemsCarrito(Integer.parseInt(request.getParameter("idCarrito")));
        float subtotal = 0;
        float total = 0;
        for (int i = 0; i < listaItemCarrito.size(); i++) {
            nodoItemCarrito = new JSONObject();
            nodoItemCarrito.put("id", listaItemCarrito.get(i).getId());
            nodoItemCarrito.put("imagen", listaItemCarrito.get(i).getImagenProducto());
            nodoItemCarrito.put("descripcion", listaItemCarrito.get(i).getDescripcion());
            nodoItemCarrito.put("precio", listaItemCarrito.get(i).getPrecio());
            nodoItemCarrito.put("cantidad", listaItemCarrito.get(i).getCantidad());
            subtotal = listaItemCarrito.get(i).getCantidad() * listaItemCarrito.get(i).getPrecio();
            nodoItemCarrito.put("subtotal", subtotal);
            total += subtotal;
            jsonArr.add(nodoItemCarrito);
        }

        JSONObject mainObj = new JSONObject();
        //try {
        mainObj.put("ItemsCarrito", jsonArr);
        mainObj.put("total", total);
        //mainObj.put("total", mngFacturar.getMaximo());

        return mainObj.toString();
        /*} catch (JSONException e) {
			e.printStackTrace();
			return "{}";
		}	*/
    }

    private String createUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        /* Formato JSON */

        response.setContentType("application/json, charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        JSONObject nodoUsuario = new JSONObject();
        nodoUsuario.put("estado", false);

        ManagerServlet mngServlet = new ManagerServlet();
        Usuario usuario = new Usuario();

        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellido1(request.getParameter("apellido1"));
        usuario.setApellido2(request.getParameter("apellido2"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setPass(request.getParameter("pass"));

        if (mngServlet.createUser(usuario) && mngServlet.crearCarrito(usuario.getEmail())) {
            nodoUsuario.put("estado", true);
        }

        return nodoUsuario.toString();
    }

    private String userLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        response.setContentType("application/json, charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        JSONObject nodoUsuario = new JSONObject();
        nodoUsuario.put("estado", false);

        ManagerServlet mngServlet = new ManagerServlet();
        Usuario usuario = mngServlet.UserLogin(request.getParameter("uEmail"), request.getParameter("uPass"));

        nodoUsuario.put("nombre", usuario.getNombre());
        nodoUsuario.put("apellido1", usuario.getApellido1());
        nodoUsuario.put("apellido2", usuario.getApellido2());
        nodoUsuario.put("email", usuario.getEmail());

        if (usuario.getEmail() != null && usuario.getEmail().length() != 0) {
            nodoUsuario.put("estado", true);
            HttpSession session = request.getSession(true);
            session.setAttribute("sUser", usuario);

            Carrito carrito = mngServlet.CarritoUsuario(usuario.getEmail());

            if (carrito.getId() == 0) {
                mngServlet.crearCarrito(usuario.getEmail());
                carrito = mngServlet.CarritoUsuario(usuario.getEmail());
            }
            if (carrito.getUsuario().getEmail() != null && carrito.getUsuario().getEmail().length() != 0) {
                session.setAttribute("carrito", carrito);
            }
        }

        return nodoUsuario.toString();
    }

    private String eliminarItem(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        /* Formato JSON */

        response.setContentType("application/json, charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        JSONObject nodoItem = new JSONObject();
        nodoItem.put("estado", false);

        ManagerServlet mngServlet = new ManagerServlet();
        if (mngServlet.eliminarItem(Integer.parseInt(request.getParameter("idItem")))) {
            nodoItem.put("estado", true);
        }

        return nodoItem.toString();
    }

    private String actualizarItem(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        /* Formato JSON */

        response.setContentType("application/json, charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        JSONObject nodoItem = new JSONObject();
        nodoItem.put("estado", false);

        ManagerServlet mngServlet = new ManagerServlet();
        if (mngServlet.actualizarItem(Integer.parseInt(request.getParameter("idItem")), Integer.parseInt(request.getParameter("cantidad")))) {
            nodoItem.put("estado", true);
        }

        return nodoItem.toString();
    }

    private String cargarListaTarjetas(HttpServletRequest request, HttpServletResponse response) {
        /* Formato JSON */
        response.setContentType("application/json, charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        JSONArray jsonArr = new JSONArray();
        JSONObject nodoTarjeta;

        ManagerServlet mngServlet = new ManagerServlet();
        ArrayList<TarjetaNombre> listaTarjetas = mngServlet.consultarTarjetasUsuario(request.getParameter("idUsuario"));

        for (int i = 0; i < listaTarjetas.size(); i++) {
            nodoTarjeta = new JSONObject();
            nodoTarjeta.put("numero", listaTarjetas.get(i).getNumero());
            nodoTarjeta.put("usuario", listaTarjetas.get(i).getUsuario());
            nodoTarjeta.put("fecha_exp", listaTarjetas.get(i).getFecha_Exp().toString());
            nodoTarjeta.put("ccv", listaTarjetas.get(i).getCcv());

            jsonArr.add(nodoTarjeta);
        }

        JSONObject mainObj = new JSONObject();
        mainObj.put("tarjetas", jsonArr);

        return mainObj.toString();
    }

    private String logout(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        response.setContentType("application/json, charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        JSONObject nodoUsuario = new JSONObject();
        nodoUsuario.put("estado", false);

        HttpSession misession = (HttpSession) request.getSession();
        misession.invalidate();

        nodoUsuario.put("estado", true);

        return nodoUsuario.toString();
    }

    private String checkout(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        response.setContentType("application/json, charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        JSONObject nodoItem = new JSONObject();
        nodoItem.put("estado", false);

        ManagerServlet mngServlet = new ManagerServlet();
        if (mngServlet.actualizarCarrito(Integer.parseInt(request.getParameter("idCarrito")), true)) {
            nodoItem.put("estado", true);

            if (mngServlet.crearCarrito(request.getParameter("idUsuario"))) {
                Carrito carrito = mngServlet.CarritoUsuario(request.getParameter("idUsuario"));
                HttpSession misession = (HttpSession) request.getSession();
                misession.setAttribute("carrito", carrito);
            }
        }

        return nodoItem.toString();
    }

    private String agregarTarjeta(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        /* Formato JSON */

        response.setContentType("application/json, charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        JSONObject nodoItem = new JSONObject();
        nodoItem.put("estado", false);

        ManagerServlet mngServlet = new ManagerServlet();
        TarjetaNombre tarjeta = new TarjetaNombre();

        tarjeta.setNumero(Integer.parseInt(request.getParameter("numero")));
        tarjeta.setUsuario(request.getParameter("usuario"));
        tarjeta.setFecha_Exp(Date.valueOf(request.getParameter("fecha_exp")));
        tarjeta.setCcv(Integer.parseInt(request.getParameter("ccv")));

        if (mngServlet.agregarTarjeta(tarjeta)) {
            nodoItem.put("estado", true);
        }

        return nodoItem.toString();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ArticulosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
