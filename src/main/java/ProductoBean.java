package org.uv.dsweb.practica04;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

@Named(value = "productoBean")
@SessionScoped
public class ProductoBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private PojoProducto producto;
    private List<PojoProducto> listaProductos;
    
    private ProductoDAO productoDAO;

    public ProductoBean() {
        producto = new PojoProducto();
        productoDAO = new ProductoDAO();
        listaProductos = cargarTodosLosProductos();
    }

    public PojoProducto getProducto() {
        return producto;
    }

    public void setProducto(PojoProducto producto) {
        this.producto = producto;
    }

    public List<PojoProducto> getListaProductos() {
        return listaProductos;
    }

    private List<PojoProducto> cargarTodosLosProductos() {
        return productoDAO.buscarTodos();
    }

    public String crearProducto() {
        if (productoDAO.guardar(producto)) {
            listaProductos = cargarTodosLosProductos();
            producto = new PojoProducto();
            return "productos.xhtml"; 
        }
        return null;
    }

    public String actualizarProducto() {
        if (productoDAO.modificar(producto.getIdProducto(), producto)) {
            listaProductos = cargarTodosLosProductos();
            producto = new PojoProducto(); 
            return "productos.xhtml"; 
        }
        return null;
    }

    public String eliminarProducto(PojoProducto productoEliminar) {
        if (productoDAO.eliminar(productoEliminar.getIdProducto())) {
            listaProductos = cargarTodosLosProductos();
            return "productos.xhtml"; 
        }
        return null;
    }

    public String seleccionarProducto(PojoProducto productoSeleccionado) {
        this.producto = productoSeleccionado;
        return null; 
    }
}
