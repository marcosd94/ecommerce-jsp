/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlineshop.mng;

import java.util.ArrayList;
import onlineshop.ec.Producto;
import onlineshop.ec.ProductosCargados;
import onlineshop.ec.TransaccionesCab;
import onlineshop.ec.TransaccionesDet;
import onlineshop.ec.Usuario;


public class CajaManager {

    public Integer confirmarCompra(ArrayList<ProductosCargados> productosCargados, Usuario usuario, TransaccionesCab transaccionesCab) throws Exception {
        transaccionesCab.setEstado("I");
        transaccionesCab.setIdUsuario(usuario.getIdUsuario());
        transaccionesCab.setTotal(calcularTotal(productosCargados));
        TransaccionesCabManager transaccionesCabManager = new TransaccionesCabManager();
        Integer idTransacionesCab = transaccionesCabManager.insertar(transaccionesCab);
        
        Integer item = 1 ;
        for(ProductosCargados c : productosCargados){
            manejarStock(c.getIdProducto(), c);
            insertarDetalles(c, idTransacionesCab, item );
            item++;
        }
        
        return idTransacionesCab;
    }

    private Integer calcularTotal(ArrayList<ProductosCargados> productosCargados) {
            Integer total = 0; 
            for(ProductosCargados c : productosCargados){
                total += c.getCantidad()*c.getPrecioUnit();
            }
            return total;
    }

    private void manejarStock(Integer idProducto, ProductosCargados productosCargados) throws Exception {        
        ProductoManager productoManager = new ProductoManager();
        Producto c = productoManager.getProductoById(idProducto);
        c.setCantidad(c.getCantidad()-productosCargados.getCantidad());
        productoManager.update(c);
    }

    private void insertarDetalles(ProductosCargados c, Integer idTransacionesCab, Integer item) throws Exception {
        TransaccionesDet transaccionesDet = new TransaccionesDet();
        transaccionesDet.setCantidad(c.getCantidad());
        
        transaccionesDet.setIdProducto(c.getIdProducto());
        transaccionesDet.setIdTransaccionesCab(idTransacionesCab);
        transaccionesDet.setItem(item);
        transaccionesDet.setPrecio(c.getPrecioUnit());
        transaccionesDet.setSubTotal(c.getCantidad() * c.getPrecioUnit());
        
        TransaccionesDetManager transaccionesDetManager = new TransaccionesDetManager();
        transaccionesDetManager.insertar(transaccionesDet);
        
    }
}
