import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import org.uv.dsweb.practica04.ClienteDAO;
import org.uv.dsweb.practica04.PojoCliente;

@Named(value = "clienteBean")
@SessionScoped
public class ClienteBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private PojoCliente cliente;
    private List<PojoCliente> listaClientes;
    
    private ClienteDAO clienteDAO;
    
    public ClienteBean() {
        cliente = new PojoCliente();
        clienteDAO = new ClienteDAO();
        listaClientes = cargarTodosLosClientes();
    }

    public PojoCliente getCliente() {
        return cliente;
    }

    public void setCliente(PojoCliente cliente) {
        this.cliente = cliente;
    }

    public List<PojoCliente> getListaClientes() {
        return listaClientes;
    }

    
    private List<PojoCliente> cargarTodosLosClientes() {
        return clienteDAO.buscarTodos();
    }
    public String crearCliente() {
        clienteDAO.guardar(cliente);
        listaClientes = cargarTodosLosClientes();
        cliente = new PojoCliente(); 
        return "clientes"; 
    }

    public String actualizarCliente() {
        clienteDAO.modificar(cliente.getIdCliente(), cliente);
        listaClientes = cargarTodosLosClientes();
        return "clientes";
    }

    public String eliminarCliente(PojoCliente clienteEliminar) {
        clienteDAO.eliminar(clienteEliminar.getIdCliente());
        listaClientes = cargarTodosLosClientes();
        return "clientes";
    }

    public String seleccionarCliente(PojoCliente clienteSeleccionado) {
        this.cliente = clienteSeleccionado;
        return "editarCliente"; 
    }
}
