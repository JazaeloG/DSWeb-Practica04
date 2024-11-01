package org.uv.dsweb.practica04;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name="productos")
public class PojoProducto implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name="productos_id_producto_seq", sequenceName="productos_id_producto_seq", initialValue=1,
            allocationSize=1)
    @Column(name="id_producto")
    private long idProducto;
    
    @Column(name="nombre", nullable=false)
    private String nombre;    

    @Column(name="precio", nullable=false)
    private double precio;

    @Column(name="descripcion", nullable=false)
    private String descripcion;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
