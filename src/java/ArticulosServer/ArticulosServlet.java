/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArticulosServer;

import Conexiones.CompuertaMarcas;
import Conexiones.CompuertaProductos;
import Conexiones.ManagerServlet;
import Conexiones.Producto;
import Model.Carrito;
import Model.Item;
import Model.ItemCarrito;
import Model.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
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
                    case "getProds":
                        mensaje = getAllProds(request, response);
                        break;
                        
                     case "createUser":
                        mensaje = createUser(request, response);
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

        //------ Prueba ------
        CompuertaProductos cp = new CompuertaProductos();
        Consumer action = new Consumer() {
            @Override
            public void accept(Object t) {
                if (t.getClass() == Producto.class) {
                    System.out.println(((Producto) t).getId() + "  " + ((Producto) t).getMarca() + "  " + ((Producto) t).getDescripcion() + "  " + ((Producto) t).getPrecio() + "  " + ((Producto) t).getCantidad());
                }
            }
        };
        cp.obtenerTodos().forEach(action);
        action.accept(cp.obtenerPorID(2));
        cp.obtenerPorMarca(5).forEach(action);
        cp.obtenerPorNombreMarca("NIKE").forEach(action);
        cp.obtenerPorDescripcion("3").forEach(action);
        //-------

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

    public String getAllProds(HttpServletRequest request, HttpServletResponse response) {
        JSONObject nodoData = new JSONObject();
        CompuertaProductos cp = new CompuertaProductos();

        JSONArray ja = new JSONArray();
        ArrayList<Producto> prods = cp.obtenerTodos();

        if (prods != null) {
            for (Producto prod : prods) {
                JSONObject jp = new JSONObject();
                jp.put("id",prod.getId());
                jp.put("marca",prod.getMarca());
                jp.put("descripcion",prod.getDescripcion());
                jp.put("precio",prod.getPrecio());
                jp.put("cantidad",prod.getCantidad());
                ja.add(jp);
            }
        }

        nodoData.put("respuesta", ja);
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
		ArrayList<Producto> listaProductos = mngServlet.consultarProductos();

		for (int i = 0; i < listaProductos.size(); i++) {
			nodoProducto = new JSONObject();
			nodoProducto.put("id", listaProductos.get(i).getId());
			nodoProducto.put("marca",listaProductos.get(i).getMarca() );
			nodoProducto.put("descripcion",listaProductos.get(i).getDescripcion() );
			nodoProducto.put("precio", listaProductos.get(i).getPrecio());
                        nodoProducto.put("cantidad",listaProductos.get(i).getCantidad() );
                        nodoProducto.put("imagen",listaProductos.get(i).getImagen() );
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
			nodoProducto.put("marca",producto.getMarca() );
			nodoProducto.put("descripcion",producto.getDescripcion() );
			nodoProducto.put("precio", producto.getPrecio());
                        nodoProducto.put("cantidad",producto.getCantidad() );
                        nodoProducto.put("imagen",producto.getImagen() );
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

		for (int i = 0; i < listaItemCarrito.size(); i++) {
			nodoItemCarrito = new JSONObject();
			nodoItemCarrito.put("id", listaItemCarrito.get(i).getId());
			nodoItemCarrito.put("imagen",listaItemCarrito.get(i).getImagenProducto() );
			nodoItemCarrito.put("descripcion",listaItemCarrito.get(i).getDescripcion() );
			nodoItemCarrito.put("precio", listaItemCarrito.get(i).getPrecio());
                        nodoItemCarrito.put("cantidad",listaItemCarrito.get(i).getCantidad() );
                        
			jsonArr.add(nodoItemCarrito);
		}

		JSONObject mainObj = new JSONObject();
		//try {
			mainObj.put("ItemsCarrito", jsonArr);
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
                
		if (mngServlet.createUser(usuario)) {
			nodoUsuario.put("estado", true);
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

