package org.uv.dsweb.practica04;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

@Named(value = "detalleVentaBean")
@SessionScoped
public class DetalleVentaBean implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private PojoDetalleVenta detalleVenta;
    private List<PojoDetalleVenta> listaDetallesVenta;
    
    private DetalleVentaDAO detalleVentaDAO;

    public DetalleVentaBean() {
        detalleVenta = new PojoDetalleVenta();
        detalleVentaDAO = new DetalleVentaDAO();
        listaDetallesVenta = cargarTodosLosDetalles();
    }

    public PojoDetalleVenta getDetalleVenta() {
        return detalleVenta;
    }

    public void setDetalleVenta(PojoDetalleVenta detalleVenta) {
        this.detalleVenta = detalleVenta;
    }

    public List<PojoDetalleVenta> getListaDetallesVenta() {
        return listaDetallesVenta;
    }

    private List<PojoDetalleVenta> cargarTodosLosDetalles() {
        return detalleVentaDAO.buscarTodos();
    }

    public String crearDetalleVenta() {
        detalleVentaDAO.guardar(detalleVenta);
        listaDetallesVenta = cargarTodosLosDetalles();
        detalleVenta = new PojoDetalleVenta();
        return "detallesVenta";
    }

    public String actualizarDetalleVenta() {
        detalleVentaDAO.modificar(detalleVenta.getIdDetalleVenta(), detalleVenta);
        listaDetallesVenta = cargarTodosLosDetalles();
        return "detallesVenta";
    }

    public String eliminarDetalleVenta(PojoDetalleVenta detalleEliminar) {
        detalleVentaDAO.eliminar(detalleEliminar.getIdDetalleVenta());
        listaDetallesVenta = cargarTodosLosDetalles();
        return "detallesVenta"; 
    }

    public String seleccionarDetalleVenta(PojoDetalleVenta detalleSeleccionado) {
        this.detalleVenta = detalleSeleccionado;
        return "editarDetalleVenta";
    }
}
