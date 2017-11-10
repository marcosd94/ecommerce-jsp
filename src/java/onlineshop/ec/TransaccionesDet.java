/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineshop.ec;

/**
 *
 * @author marcos
 */
public class TransaccionesDet {
    Integer idTransacción;
    Integer item;
    Integer idProducto;
    Integer cantidad;
    Integer precio;
    Integer subTotal;
    Integer idTransaccionesCab;

    public TransaccionesDet() {
    }

    public Integer getIdTransacción() {
        return idTransacción;
    }

    public void setIdTransacción(Integer idTransacción) {
        this.idTransacción = idTransacción;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal;
    }      

    public Integer getIdTransaccionesCab() {
        return idTransaccionesCab;
    }

    public void setIdTransaccionesCab(Integer idTransaccionesCab) {
        this.idTransaccionesCab = idTransaccionesCab;
    }
}
