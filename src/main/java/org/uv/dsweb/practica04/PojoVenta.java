package org.uv.dsweb.practica04;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity(name="ventas")
public class PojoVenta implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name="ventas_id_venta_seq", sequenceName="ventas_id_venta_seq", initialValue=1,
            allocationSize=1)
    @Column(name="id_venta")
    private long idVenta;
    
    @Column(name="fecha_venta", nullable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVenta;
    
    @Column(name="total", nullable=false)
    private Double total;

    @ManyToOne
    @JoinColumn(name="cliente_id", nullable=false)
    private PojoCliente cliente;
    
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PojoDetalleVenta> detalles = new ArrayList<>();

    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public PojoCliente getCliente() {
        return cliente;
    }

    public void setCliente(PojoCliente cliente) {
        this.cliente = cliente;
    }
    
    public List<PojoDetalleVenta> getDetalles() {
        return detalles;
    }
    
    public void setDetalles(List<PojoDetalleVenta> detalles) {
        this.detalles = detalles;
    }

}
