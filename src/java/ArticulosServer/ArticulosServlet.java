/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArticulosServer;

import Conexiones.CompuertaMarcas;
import Conexiones.CompuertaProductos;
import Conexiones.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractMap;
import java.util.function.Consumer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String mensaje ="";
            if(request.getParameter("accion") != null && request.getParameter("accion") != ""){
                String accion = request.getParameter("accion");
                
      switch(accion) {
         case "a" :
            mensaje = metodo1(request,response); 
            break;
         case "b" :
            mensaje = metodo2(request,response); 
            break;
         case "c" :
            mensaje = metodo3(request,response); 
            break;
         
         default :
            mensaje = "sin accion"; 
      }
            }
            else{
                mensaje = "Sin accion"; 
            }
              
            out.println(mensaje);

        }
    }
    
    public String metodo1(HttpServletRequest request, HttpServletResponse response){
        
        //------ Prueba ------
        CompuertaProductos cp = new CompuertaProductos();
        Consumer action = new Consumer() {
            @Override
            public void accept(Object t) {
                if(t.getClass() == Producto.class){
                    System.out.println(((Producto)t).getId()+"  "+((Producto)t).getMarca()+"  "+((Producto)t).getDescripcion()+"  "+((Producto)t).getPrecio()+"  "+((Producto)t).getCantidad());  
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
    
    public String metodo2(HttpServletRequest request, HttpServletResponse response){
    
        JSONObject nodoData = new JSONObject();
        nodoData.put("respuesta", "metodo2");
        nodoData.put("estado", true);
    return nodoData.toString();
    }
    public String metodo3(HttpServletRequest request, HttpServletResponse response){
    
        JSONObject nodoData = new JSONObject();
        nodoData.put("respuesta", "metodo3");
        nodoData.put("estado", true);
    return nodoData.toString();
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
        processRequest(request, response);
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
        processRequest(request, response);
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
