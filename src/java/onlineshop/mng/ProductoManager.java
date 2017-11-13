/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineshop.mng;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import onlineshop.ec.Categoria;
import onlineshop.ec.Producto;
import onlineshop.util.DBUtils;

/**
 *
 * @author Mauricio
 */
public class ProductoManager {

    public boolean insertar(Producto c) throws Exception {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;
        /*
        Integer   idProducto;
        String descripción;
        Integer idCategoria;
        Integer precioUnit;
        Integer cantidad;
        */
        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("insert into producto (descripcion , id_categoria, precio_unit, cantidad) "
                    + "values (?, ?, ?, ?)");
            pstmt.setString(1, c.getDescripcion());
            pstmt.setInt(2, c.getIdCategoria());
            pstmt.setInt(3, c.getPrecioUnit());
            pstmt.setInt(4, c.getCantidad());
            pstmt.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(ProductoManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductoManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public boolean update(Producto c) throws Exception  {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("update producto set descripcion = ?, id_categoria = ?, precio_unit = ?, cantidad = ?  where id_producto = ?");            
            pstmt.setString(1, c.getDescripcion());
            pstmt.setInt(2, c.getIdCategoria());
            pstmt.setInt(3, c.getPrecioUnit());
            pstmt.setInt(4, c.getCantidad());
            pstmt.setInt(5, c.getIdProducto());
            pstmt.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(ProductoManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public boolean delete(Producto c) throws Exception  {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("delete from producto where id_producto = ?");
            pstmt.setInt(1, c.getIdProducto());
            pstmt.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(ProductoManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public Producto getProductoById(int id)  throws Exception {
        Producto retValue = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("select descripcion, id_categoria, cantidad, precio_unit from producto where id_producto = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                retValue =  new Producto();
                retValue.setIdProducto(id);
                retValue.setDescripcion(rs.getString(1));
                retValue.setIdCategoria(rs.getInt(2));
                retValue.setCantidad(rs.getInt(3));
                retValue.setPrecioUnit(rs.getInt(4));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }
    
    public ArrayList<Producto> getAll()  throws Exception {
        ArrayList<Producto> retValue = new ArrayList<Producto>();
        //ArrayList<Categoria> retCategoria = new ArrayList<Categoria>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("select id_producto, descripcion, id_categoria,  cantidad, precio_unit from producto");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Producto c = new Producto();
                c.setIdProducto(rs.getInt(1));
                c.setDescripcion(rs.getString(2));
                c.setIdCategoria(rs.getInt(3));
                c.setCantidad(rs.getInt(4));
                c.setPrecioUnit(rs.getInt(5));
                retValue.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }    
    public ArrayList<Categoria> getAllCategorias()  throws Exception {
        //ArrayList<Producto> retValue = new ArrayList<Producto>();
        ArrayList<Categoria> retCategoria = new ArrayList<Categoria>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("select id_categoria, descripcion from categoria");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt(1));
                c.setDescripcion(rs.getString(2));                
                retCategoria.add(c);
            }


        } catch (SQLException ex) {
            Logger.getLogger(ProductoManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retCategoria;
    }    
}
