/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineshop.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import onlineshop.ec.Usuario;
import onlineshop.mng.LoginManager;

/**
 *
 * @author mrcpe
 */
public class LoginServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        session.setAttribute("usuario",usuario);
        String vaccion = request.getParameter("vaccion");
        request.setAttribute("vaccion", vaccion);
        
        if (vaccion == null) {            
            if(usuario != null){
                response.sendRedirect(request.getContextPath() + "/");
            }else{       
                RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/login/Login.jsp");
                if (rd != null) {
                    rd.forward(request, response);
                }
            }
        }
        if ("Iniciar".equals(vaccion)) {
            String nombreUsuario = request.getParameter("nombreUsuario");
            String pass = request.getParameter("pass");
            
            LoginManager loginManager = new LoginManager();
            Usuario  usuarioLogueado = loginManager.verificarUsuario(nombreUsuario, pass);
            if(usuarioLogueado != null){
                System.out.println("Loggin correcto");
                HttpSession sesion= request.getSession(true);
                sesion.setAttribute("usuario", usuarioLogueado);
                response.sendRedirect(request.getContextPath() + "/");
            }else{       
                System.out.println("Loggin incorrecto");
                
                request.setAttribute("nombreUsuario", null);
                request.setAttribute("pass", null);
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
