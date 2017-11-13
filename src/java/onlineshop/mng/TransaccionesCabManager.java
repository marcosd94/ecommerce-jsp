/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineshop.mng;

import onlineshop.ec.TransaccionesCab;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import onlineshop.ec.Producto;
import onlineshop.ec.TransaccionesDet;
import onlineshop.util.DBUtils;


public class TransaccionesCabManager {
    
    public Integer insertar(TransaccionesCab c) throws Exception  {
        Integer idTransaccionesCab = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("  INSERT INTO transacciones_cab( fecha, id_usuario, total, direccion_de_envio, id_medio_pago, nro_tarjeta, estado, id_transacciones_cab)" +
                        " VALUES ( now(), ?, ?, ?, ?, ?, ?, ?)");
            Integer maximo = buscarMaximo();
            pstmt.setInt(1, c.getIdUsuario());
            pstmt.setInt(2, c.getTotal());
            pstmt.setString(3, c.getDireccionDeEnvio());
            pstmt.setInt(4, c.getIdMedioPago());
            pstmt.setString(5, c.getNroTarjeta());
            pstmt.setString(6, c.getEstado());
            pstmt.setInt(7, maximo );
            pstmt.execute();
            idTransaccionesCab = maximo;
        } catch (SQLException ex) {
            idTransaccionesCab = null;
            Logger.getLogger(TransaccionesCabManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaccionesCabManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            DBUtils.closeConnection(conn);
        }

        return idTransaccionesCab;
    }

    private int buscarMaximo()  throws Exception {
        Integer siguiente = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("SELECT COALESCE(  max(id_transacciones_cab), '0' ) +1 FROM transacciones_cab");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                siguiente = (rs.getInt(1));
            }


        } catch (SQLException ex) {
            Logger.getLogger(TransaccionesCabManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return siguiente;
    }

    public ArrayList<TransaccionesCab> getCompras(Integer idUsuario) throws ParseException, Exception {
        
        ArrayList<TransaccionesCab> retValue = new ArrayList<TransaccionesCab>();
        //ArrayList<Categoria> retCategoria = new ArrayList<Categoria>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("SELECT id_transacciones_cab, fecha, total, direccion_de_envio, id_medio_pago, nro_tarjeta, estado, id_usuario FROM transacciones_cab  where id_usuario = ?");
            pstmt.setInt(1, idUsuario);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                TransaccionesCab c = new TransaccionesCab();
                
                c.setIdTransacción(rs.getInt(1));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(rs.getString(2));
                c.setFecha(date);                
                c.setTotal(rs.getInt(3));
                c.setDireccionDeEnvio(rs.getString(4));                
                c.setIdMedioPago(rs.getInt(5));
                c.setNroTarjeta(rs.getString(6));   
                c.setEstado(rs.getString(7));   
                c.setIdUsuario(rs.getInt(8));
                retValue.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public ArrayList<TransaccionesDet> getComprasdetalles(Integer transaccionId)  throws Exception {
        ArrayList<TransaccionesDet> retValue = new ArrayList<TransaccionesDet>();
        //ArrayList<Categoria> retCategoria = new ArrayList<Categoria>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("SELECT id_transacciones_det, item, id_producto, cantidad, precio, subtotal, id_transacciones_cab" +
"	FROM transacciones_det where id_transacciones_cab = ?");
            pstmt.setInt(1, transaccionId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                TransaccionesDet c = new TransaccionesDet();
                
                c.setIdTransacción(rs.getInt(1));                
                c.setItem(rs.getInt(2));                
                c.setIdProducto(rs.getInt(3));                
                c.setCantidad(rs.getInt(4));                
                c.setPrecio(rs.getInt(5));            
                c.setSubTotal(rs.getInt(6));              
                c.setIdTransaccionesCab(rs.getInt(7));
                retValue.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }
}
