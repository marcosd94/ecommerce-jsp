/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineshop.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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
import onlineshop.ec.TransaccionesCab;
import onlineshop.ec.TransaccionesDet;
import onlineshop.ec.Usuario;
import onlineshop.mng.ProductoManager;
import onlineshop.mng.TransaccionesCabManager;

/**
 *
 * @author mrcpe
 */
public class TransaccionesCabServlet extends HttpServlet {

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
            throws ServletException, IOException, ParseException, Exception {
        
        HttpSession session = request.getSession();
        String vaccion = request.getParameter("vaccion");
        request.setAttribute("vaccion", vaccion);
        if (vaccion == null) {
            
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if(usuario != null){                
                //modo grilla...se muestran todos los registros
//                ArrayList<Producto> productos = productoManager.getAll();
//                ArrayList<Categoria> categorias = productoManager.getAllCategorias();
//                request.setAttribute("productos", productos);
//                request.setAttribute("categorias", categorias);
                TransaccionesCabManager transaccionesCabManager = new TransaccionesCabManager();
                ArrayList<TransaccionesCab> transaccionesCab = transaccionesCabManager.getCompras(usuario.getIdUsuario());
                request.setAttribute("compras", transaccionesCab);
                session.setAttribute("usuario", usuario);

                RequestDispatcher rd = request.getRequestDispatcher("/transaccionesCab/TransaccionesCab.jsp");
                if (rd != null) {
                    rd.forward(request, response);
                }
            }else{
                response.sendRedirect(request.getContextPath() + "/LoginServlet");              
            }
        }
        if ("Ver".equals(vaccion)) {
            
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if(usuario != null){
                Integer transaccionId = Integer.valueOf(request.getParameter("transaccionId"));
                TransaccionesCabManager transaccionesCabManager = new TransaccionesCabManager();
                ArrayList<TransaccionesDet> transaccionesDet = transaccionesCabManager.getComprasdetalles(transaccionId);
                
                ProductoManager productoManager = new ProductoManager();
                
                ArrayList<Producto> productos = productoManager.getAll();
                ArrayList<Categoria> categorias = productoManager.getAllCategorias();
                
                request.setAttribute("productos", productos);
                request.setAttribute("categorias", categorias);
                request.setAttribute("detallesCompras", transaccionesDet);
                session.setAttribute("usuario", usuario);

                RequestDispatcher rd = request.getRequestDispatcher("/transaccionesCab/TransaccionesDet.jsp");
                if (rd != null) {
                    rd.forward(request, response);
                }
            }else{
                response.sendRedirect(request.getContextPath() + "/LoginServlet");            
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
            Logger.getLogger(TransaccionesCabServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(TransaccionesCabServlet.class.getName()).log(Level.SEVERE, null, ex);
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
