/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineshop.ec;

import java.util.Date;

/**
 *
 * @author marcos
 */
public class TransaccionesCab {
    
Integer idTransacción;
Date fecha;
Integer idUsuario;
Integer total;
String direccionDeEnvio;
Integer idMedioPago; //0 Efectivo, 1 Tarjeta de Credito
String nroTarjeta; //solo si id_medio_pago == 1
String estado; //I Ingresado

    public TransaccionesCab() {
    }

    public Integer getIdTransacción() {
        return idTransacción;
    }

    public void setIdTransacción(Integer idTransacción) {
        this.idTransacción = idTransacción;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getDireccionDeEnvio() {
        return direccionDeEnvio;
    }

    public void setDireccionDeEnvio(String direccionDeEnvio) {
        this.direccionDeEnvio = direccionDeEnvio;
    }

    public Integer getIdMedioPago() {
        return idMedioPago;
    }

    public void setIdMedioPago(Integer idMedioPago) {
        this.idMedioPago = idMedioPago;
    }

    public String getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(String nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}
