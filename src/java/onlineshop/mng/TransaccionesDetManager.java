/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineshop.mng;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import onlineshop.ec.TransaccionesDet;
import onlineshop.util.DBUtils;

public class TransaccionesDetManager {
    

    public Integer insertar(TransaccionesDet c) throws Exception  {
        Integer idTransaccionesCab = 0;

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("  INSERT INTO public.transacciones_det( item, id_producto, cantidad, precio, subtotal, id_transacciones_cab)" +
                " VALUES (?, ?, ?, ?, ?, ?)");
            pstmt.setInt(1, c.getItem());
            pstmt.setInt(2, c.getIdProducto());
            pstmt.setInt(3, c.getCantidad());
            pstmt.setInt(4, c.getPrecio());
            pstmt.setInt(5, c.getSubTotal());
            pstmt.setInt(6, c.getIdTransaccionesCab());
            pstmt.execute();
        } catch (SQLException ex) {
            idTransaccionesCab = null;
            Logger.getLogger(TransaccionesDetManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TransaccionesDetManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            DBUtils.closeConnection(conn);
        }

        return idTransaccionesCab;
    }
}
