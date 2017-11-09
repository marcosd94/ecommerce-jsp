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
    Double precio;
    Double subTotal;

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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }      
}
