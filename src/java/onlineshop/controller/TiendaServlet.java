/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineshop.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import onlineshop.ec.Categoria;
import onlineshop.ec.Producto;
import onlineshop.ec.ProductosCargados;
import onlineshop.ec.Usuario;
import onlineshop.mng.TiendaManager;

/**
 *
 * @author mrcpe
 */
public class TiendaServlet extends HttpServlet {

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
        ArrayList<ProductosCargados> productosCargados = new ArrayList<ProductosCargados>();
        String vaccion = request.getParameter("vaccion");
        ArrayList<ProductosCargados> lectura  = new ArrayList<ProductosCargados>();
        lectura = (ArrayList<ProductosCargados>)  session.getAttribute("data");
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
            session.setAttribute("productosCargados", productosCargados);
            request.setAttribute("productosCargados", productosCargados);
            request.setAttribute("nombre", "");
            request.setAttribute("idCategoria", "0");

            RequestDispatcher rd = request.getRequestDispatcher("/tienda/Tienda.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }
        if ("Cargar".equals(vaccion)) {
            
            Integer idProducto = Integer.parseInt(request.getParameter("idProducto"));
            Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
            String desc = request.getParameter("descripcion");
            Integer idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
            Integer precioUnit = Integer.parseInt(request.getParameter("precioUnit"));
            
            ProductosCargados productosCargadosObj = new ProductosCargados();
            productosCargadosObj.setIdProducto(idProducto);
            productosCargadosObj.setCantidad(cantidad);
            productosCargadosObj.setDescripcion(desc);
            productosCargadosObj.setIdCategoria(idCategoria);
            productosCargadosObj.setPrecioUnit(precioUnit);
            Boolean nuevo = true;
            for(ProductosCargados c : productosCargados){
                if(c.getIdProducto() == idProducto){
                    c.setCantidad(c.getCantidad()+cantidad);
                    nuevo = false;
                }
            }
            if(nuevo){
                productosCargados.add(productosCargadosObj);
            }
            ArrayList<Producto> productos = tiendaManager.getAll();
            ArrayList<Categoria> categorias = tiendaManager.getAllCategorias();
            request.setAttribute("productos", productos);
            request.setAttribute("categorias", categorias);
            request.setAttribute("productosCargados", productosCargados);
            session.setAttribute("productosCargados", productosCargados);
            request.setAttribute("nombre", "");
            request.setAttribute("idCategoria", "0");

            RequestDispatcher rd = request.getRequestDispatcher("/tienda/Tienda.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }
        if ("Eliminar".equals(vaccion)) {
            System.out.println("Elminar item");
            Integer idProducto = Integer.parseInt(request.getParameter("idProducto"));
            System.out.println(idProducto);
            int index = 0;
            for(int i = 0 ; i < productosCargados.size() ; i++){
                ProductosCargados item = productosCargados.get(i);
                if(item.getIdProducto() == idProducto){
                    System.out.println(i);
                    index = i;
                    break;
                }
            }
            System.out.println(index);
            productosCargados.remove(index);
            ArrayList<Producto> productos = tiendaManager.getAll();
            ArrayList<Categoria> categorias = tiendaManager.getAllCategorias();
            request.setAttribute("productos", productos);
            request.setAttribute("categorias", categorias);
            request.setAttribute("productosCargados", productosCargados);
            session.setAttribute("productosCargados", productosCargados);
            session.setAttribute("data", productosCargados);
            request.setAttribute("nombre", "");
            request.setAttribute("idCategoria", "0");

            RequestDispatcher rd = request.getRequestDispatcher("/tienda/Tienda.jsp");
            if (rd != null) {
                rd.forward(request, response);
            }
        }
        if ("Comprar".equals(vaccion)) {
            HttpSession sesion = request.getSession();
            Usuario usuarioLogueado = (Usuario) sesion.getAttribute("usuario");
            
            if(usuarioLogueado != null){
                System.out.println("puede comprar");
                response.sendRedirect(request.getContextPath() + "/CajaServlet");                
            }else{  
                System.out.println("no puede comprar");                     
                RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/login/Login.jsp");
                if (rd != null) {
                    rd.forward(request, response);
                }
            }            
        }
        if ("Filtrar".equals(vaccion)) {
            String nombre = request.getParameter("nombre");
            String idCategoria = request.getParameter("idCategoria");
            
            ArrayList<Producto> productos = tiendaManager.getFilter(nombre, idCategoria);
            ArrayList<Categoria> categorias = tiendaManager.getAllCategorias();
            request.setAttribute("productos", productos);
            request.setAttribute("categorias", categorias);
            session.setAttribute("productosCargados", productosCargados);
            request.setAttribute("productosCargados", productosCargados);
            request.setAttribute("nombre", nombre);
            request.setAttribute("idCategoria", idCategoria);

            RequestDispatcher rd = request.getRequestDispatcher("/tienda/Tienda.jsp");
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
