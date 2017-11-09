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
import java.util.logging.Level;
    import java.util.logging.Logger;
import onlineshop.ec.Usuario;
import onlineshop.util.DBUtils;
import onlineshop.util.Utils;

public class LoginManager {

    public Usuario verificarUsuario(String nombreUsuario, String pass) {
        Usuario c = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("select id_usuario, nombre, apellido, login_name, contrasenha, tipo_usuario from usuario where login_name=? and contrasenha=?");
            pstmt.setString(1, nombreUsuario);
            pstmt.setString(2, Utils.md5(pass));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                c = new Usuario();
                c.setIdUsuario(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setApellido(rs.getString(3));
                c.setLoginName(rs.getString(4));
                c.setContrasenha(rs.getString(5));
                c.setTipoUsuario(rs.getInt(6));
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return c;
    }
}
