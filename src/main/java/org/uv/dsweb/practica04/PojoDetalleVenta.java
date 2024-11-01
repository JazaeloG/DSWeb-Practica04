package org.uv.dsweb.practica04;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name="detalles_venta")
public class PojoDetalleVenta implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name="detalles_venta_id_detalleventa_seq", sequenceName="detalles_venta_id_detalleventa_seq", initialValue=1,
            allocationSize=1)
    @Column(name="id_detalleventa")
    private long idDetalleVenta;
    
    @ManyToOne
    @JoinColumn(name="venta_id", nullable=false)
    private PojoVenta venta;

    @ManyToOne
    @JoinColumn(name="producto_id", nullable=false)
    private PojoProducto producto;

    @Column(name="precio", nullable=false)
    private double precio;
    
    @Column(name="cantidad", nullable=false)
    private int cantidad;

    @Column(name="descripcion")
    private String descripcion;

    public long getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(long idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public PojoVenta getVenta() {
        return venta;
    }

    public void setVenta(PojoVenta venta) {
        this.venta = venta;
    }

    public PojoProducto getProducto() {
        return producto;
    }

    public void setProducto(PojoProducto producto) {
        this.producto = producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
