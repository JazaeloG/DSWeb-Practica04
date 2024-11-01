import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import org.uv.dsweb.practica04.PojoVenta;
import org.uv.dsweb.practica04.VentaDAO;

@Named(value = "ventaBean")
@SessionScoped
public class VentaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private PojoVenta venta;
    private List<PojoVenta> listaVentas;
    
    private final VentaDAO ventaDAO;

    public VentaBean() {
        venta = new PojoVenta();
        ventaDAO = new VentaDAO();
        cargarTodasLasVentas();
    }

    public PojoVenta getVenta() {
        return venta;
    }

    public void setVenta(PojoVenta venta) {
        this.venta = venta;
    }

    public List<PojoVenta> getListaVentas() {
        return listaVentas;
    }

    private void cargarTodasLasVentas() {
        listaVentas = ventaDAO.buscarTodos();
    }

    
    public String crearVenta() {
        if (ventaDAO.guardar(venta)) {
            cargarTodasLasVentas(); 
            venta = new PojoVenta(); 
            return "ventas";
        } else {
            return null;
        }
    }

    public String actualizarVenta() {
        if (ventaDAO.modificar(venta.getIdVenta(), venta)) { 
            cargarTodasLasVentas();
            return "ventas"; 
        } else {
            return null;
        }
    }

    public String eliminarVenta(PojoVenta ventaEliminar) {
        if (ventaDAO.eliminar(ventaEliminar.getIdVenta())) {
            cargarTodasLasVentas();
            return "ventas";
        } else {
            return null;
        }
    }

    public String seleccionarVenta(PojoVenta ventaSeleccionada) {
        this.venta = ventaSeleccionada;
        return "editarVenta"; 
    }
}
