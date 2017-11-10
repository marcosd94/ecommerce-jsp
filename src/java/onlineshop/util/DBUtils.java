/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author mauricio
 */
public class DBUtils {

    public static Connection getConnection() throws SQLException, Exception {
        try {
        Context ctx = new InitialContext();
        if (ctx == null)
            throw new Exception("Boom - No Context");
        Context envCtx = (Context) ctx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup("jdbc/DefaultDB");
        
        Connection conn = ds.getConnection();
        return conn;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
