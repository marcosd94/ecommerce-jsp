/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineshop.controller;

import java.io.IOException;
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
import onlineshop.ec.Usuario;
import onlineshop.mng.CategoriaManager;

/**
 *
 * @author Mauricio
 */
public class CategoriaServlet extends HttpServlet {

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

        String vaccion = request.getParameter("vaccion");
        request.setAttribute("vaccion", vaccion);

        HttpSession sesion = request.getSession();
        Usuario usuarioLogueado = (Usuario) sesion.getAttribute("usuario");

        if(usuarioLogueado == null){                  
            RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/login/Login.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }              
        } 
        CategoriaManager cm = new CategoriaManager();

        if (vaccion == null) {
            //modo grilla...se muestran todos los registros
            ArrayList<Categoria> categorias = cm.getAll();
            request.setAttribute("categorias", categorias);

            RequestDispatcher rd = request.getRequestDispatcher("/categoria/Categoria.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        if ("Eliminar".equals(vaccion)) {
            int idCat = Integer.valueOf(request.getParameter("vid"));
            Categoria c = new Categoria();
            c.setIdCategoria(idCat);

            cm.delete(c);

            ArrayList<Categoria> categorias = cm.getAll();
            request.setAttribute("categorias", categorias);

            RequestDispatcher rd = request.getRequestDispatcher("/categoria/Categoria.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        if ("GrabarNuevo".equals(vaccion)) {
            String desc = request.getParameter("descripcion");
            Categoria c = new Categoria();
            c.setDescripcion(desc);

            cm.insertar(c);

            ArrayList<Categoria> categorias = cm.getAll();
            request.setAttribute("categorias", categorias);

            RequestDispatcher rd = request.getRequestDispatcher("/categoria/Categoria.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        if ("Editar".equals(vaccion)) {
            int idCat = Integer.valueOf(request.getParameter("vid"));
            Categoria c = cm.getCategoriaById(idCat);

            request.setAttribute("categoria", c);

            RequestDispatcher rd = request.getRequestDispatcher("/categoria/CategoriaEdit.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        if ("GrabarModificado".equals(vaccion)) {
            int idCat = Integer.valueOf(request.getParameter("vid"));
            String desc = request.getParameter("descripcion");
            Categoria c = new Categoria();
            c.setIdCategoria(idCat);
            c.setDescripcion(desc);

            cm.update(c);

            ArrayList<Categoria> categorias = cm.getAll();
            request.setAttribute("categorias", categorias);

            RequestDispatcher rd = request.getRequestDispatcher("/categoria/Categoria.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }
        /*
         response.setContentType("text/html;charset=UTF-8");
         try (PrintWriter out = response.getWriter()) {
         out.println("<!DOCTYPE html>");
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet CategoriaServlet</title>");            
         out.println("</head>");
         out.println("<body>");
         out.println("<h1>Servlet CategoriaServlet at " + request.getContextPath() + "</h1>");
         out.println("</body>");
         out.println("</html>");
         }*/
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
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
