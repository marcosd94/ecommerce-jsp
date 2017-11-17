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
import java.util.logging.Level;
    import java.util.logging.Logger;
import onlineshop.ec.Usuario;
import onlineshop.util.DBUtils;
import onlineshop.util.Utils;

/**
 *
 * @author Mauricio
 */
public class UsuarioManager {

    public boolean insertar(Usuario c)  throws Exception {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;
        /*
        Integer   idUsuario;
        String descripción;
        Integer idCategoria;
        Integer precioUnit;
        Integer cantidad;
        */
        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("insert into usuario (nombre, apellido, login_name, contrasenha, tipo_usuario) "
                    + "values (?, ?, ?, ?, ?)");
            pstmt.setString(1, c.getNombre());
            pstmt.setString(2, c.getApellido());
            pstmt.setString(3, c.getLoginName());
            pstmt.setString(4, Utils.md5(c.getContrasenha()));
            pstmt.setInt(5, c.getTipoUsuario());
            pstmt.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public boolean update(Usuario c) throws Exception  {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtils.getConnection();
            
            if(c.getContrasenha().length() == 32){
                pstmt = conn.prepareStatement("update usuario set nombre = ?, apellido = ?, login_name = ?, tipo_usuario = ?  where id_usuario = ?");            
                pstmt.setString(1, c.getNombre());
                pstmt.setString(2, c.getApellido());
                pstmt.setString(3, c.getLoginName());
                pstmt.setInt(4, c.getTipoUsuario());
                pstmt.setInt(5, c.getIdUsuario());
            }else{
                pstmt = conn.prepareStatement("update usuario set nombre = ?, apellido = ?, login_name = ?, contrasenha = ?, tipo_usuario = ?  where id_usuario = ?");            
                pstmt.setString(1, c.getNombre());
                pstmt.setString(2, c.getApellido());
                pstmt.setString(3, c.getLoginName());
                pstmt.setString(4, Utils.md5(c.getContrasenha()));
                pstmt.setInt(5, c.getTipoUsuario());
                pstmt.setInt(6, c.getIdUsuario());
            }
            pstmt.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public boolean delete(Usuario c) throws Exception  {
        boolean retValue = true;

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("delete from usuario where id_usuario = ?");
            pstmt.setInt(1, c.getIdUsuario());
            pstmt.execute();

        } catch (SQLException ex) {
            retValue = false;
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }

    public Usuario getUsuarioById(int id)  throws Exception {
        Usuario c = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("select id_usuario, nombre, apellido, login_name, contrasenha, tipo_usuario from usuario where id_usuario = ?");
            pstmt.setInt(1, id);
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
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return c;
    }
    
    public ArrayList<Usuario> getAll() throws Exception  {
        ArrayList<Usuario> retValue = new ArrayList<Usuario>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            pstmt = conn.prepareStatement("select id_usuario, nombre, apellido, login_name, contrasenha, tipo_usuario from usuario");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Usuario c = new Usuario();
                c.setIdUsuario(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setApellido(rs.getString(3));
                c.setLoginName(rs.getString(4));
                c.setContrasenha(rs.getString(5));
                c.setTipoUsuario(rs.getInt(6));
                retValue.add(c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBUtils.closeConnection(conn);
        }

        return retValue;
    }    
}
