/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineshop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import onlineshop.ec.Categoria;
import onlineshop.ec.Producto;
import onlineshop.ec.ProductosCargados;
import onlineshop.ec.TransaccionesCab;
import onlineshop.ec.Usuario;
import onlineshop.mng.CajaManager;
import onlineshop.mng.TiendaManager;

/**
 *
 * @author mrcpe
 */
public class CajaServlet extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        HttpSession session = request.getSession();
        ArrayList<ProductosCargados> productosCargados = new ArrayList<ProductosCargados>();
        String vaccion = request.getParameter("vaccion");
        ArrayList<ProductosCargados> lectura  = new ArrayList<ProductosCargados>();
        lectura = (ArrayList<ProductosCargados>)  session.getAttribute("productosCargados");
        if(lectura!= null){
            productosCargados = lectura;
        }
        request.setAttribute("vaccion", vaccion);

        TiendaManager tiendaManager = new TiendaManager();

        if (vaccion == null) {
            //modo grilla...se muestran todos los registros
            ArrayList<Producto> productos = tiendaManager.getAll();
            ArrayList<Categoria> categorias = tiendaManager.getAllCategorias();
            request.setAttribute("productos", productos);
            request.setAttribute("categorias", categorias);
            request.setAttribute("productosCargados", productosCargados);

            RequestDispatcher rd = request.getRequestDispatcher("/caja/Caja.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }
        if ("Pagar".equals(vaccion) ){
            //modo grilla...se muestran todos los registros
            ArrayList<Producto> productos = tiendaManager.getAll();
            ArrayList<Categoria> categorias = tiendaManager.getAllCategorias();
            request.setAttribute("productos", productos);
            request.setAttribute("categorias", categorias);
            
            
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if(usuario != null){
                CajaManager cajaManager = new CajaManager();
                
                Integer idMedioPago = Integer.parseInt(request.getParameter("idMedioPago"));
                String nroTarjeta = request.getParameter("nroTarjeta");
                String direccionDeEnvio = request.getParameter("direccionDeEnvio");
                
                
                TransaccionesCab transaccionesCab = new TransaccionesCab();
                transaccionesCab.setFecha(new Date());
                transaccionesCab.setDireccionDeEnvio(direccionDeEnvio);
                transaccionesCab.setIdMedioPago(idMedioPago);
                transaccionesCab.setNroTarjeta(nroTarjeta);
                
                
                cajaManager.confirmarCompra(productosCargados, usuario, transaccionesCab);
                
                //SE borra el carrito
                ArrayList<ProductosCargados> productosCargadosClear = new ArrayList<ProductosCargados>();
                request.setAttribute("productosCargados", productosCargadosClear);
                session.setAttribute("productosCargados",productosCargadosClear);
                session.setAttribute("data",productosCargadosClear);
                System.out.println("Compra exitosa");
                response.sendRedirect(request.getContextPath() + "/");
            }else{                
                RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/login/Login.jsp");
            }
            
            
        }
        
        
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
        } catch (Exception ex) {
            Logger.getLogger(CajaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(CajaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
