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
import onlineshop.ec.Usuario;
import onlineshop.mng.UsuarioManager;

/**
 *
 * @author mrcpe
 */
public class UsuarioServlet extends HttpServlet {

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

        HttpSession sesion = request.getSession();
        Usuario usuarioLogueado = (Usuario) sesion.getAttribute("usuario");

        if(usuarioLogueado == null){                  
            RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/login/Login.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }              
        }         
        request.setAttribute("vaccion", vaccion);

        UsuarioManager usuarioManager = new UsuarioManager();

        if (vaccion == null) {
            //modo grilla...se muestran todos los registros
            ArrayList<Usuario> usuarios = usuarioManager.getAll();
            request.setAttribute("usuarios", usuarios);

            RequestDispatcher rd = request.getRequestDispatcher("/usuario/Usuario.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        if ("Eliminar".equals(vaccion)) {
            int idCat = Integer.valueOf(request.getParameter("vid"));
            Usuario producto = new Usuario();
            producto.setIdUsuario(idCat);

            usuarioManager.delete(producto);

            ArrayList<Usuario> usuarios = usuarioManager.getAll();
            request.setAttribute("usuarios", usuarios);

            RequestDispatcher rd = request.getRequestDispatcher("/usuario/Usuario.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        if ("GrabarNuevo".equals(vaccion)) {
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String loginName = request.getParameter("loginName");
            String contrasenha = request.getParameter("contrasenha");
            Integer tipoUsuario = Integer.parseInt(request.getParameter("tipoUsuario"));
            Usuario c = new Usuario();
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setLoginName(loginName);
            c.setContrasenha(contrasenha);
            c.setTipoUsuario(tipoUsuario);

            usuarioManager.insertar(c);

            ArrayList<Usuario> usuarios = usuarioManager.getAll();
            request.setAttribute("usuarios", usuarios);

            RequestDispatcher rd = request.getRequestDispatcher("/usuario/Usuario.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        if ("Editar".equals(vaccion)) {
            int idCat = Integer.valueOf(request.getParameter("vid"));
            System.out.println(idCat);
            Usuario c = usuarioManager.getUsuarioById(idCat);

            request.setAttribute("usuario", c);

            RequestDispatcher rd = request.getRequestDispatcher("/usuario/UsuarioEdit.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }

        if ("GrabarModificado".equals(vaccion)) {
            int idUsuario = Integer.valueOf(request.getParameter("vid"));
            
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String loginName = request.getParameter("loginName");
            String contrasenha = request.getParameter("contrasenha");
            Integer tipoUsuario = Integer.parseInt(request.getParameter("tipoUsuario"));
           
            Usuario c = new Usuario();
            c.setIdUsuario(idUsuario);
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setLoginName(loginName);
            c.setContrasenha(contrasenha);
            c.setTipoUsuario(tipoUsuario);

            usuarioManager.update(c);

            ArrayList<Usuario> usuarios = usuarioManager.getAll();
            request.setAttribute("usuarios", usuarios);

            RequestDispatcher rd = request.getRequestDispatcher("/usuario/Usuario.jsp");
            if (rd != null) {
                rd.forward(request, response);
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
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
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
