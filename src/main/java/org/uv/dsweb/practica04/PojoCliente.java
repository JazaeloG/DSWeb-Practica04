package org.uv.dsweb.practica04;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name="clientes")
public class PojoCliente implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @SequenceGenerator(name="clientes_id_cliente_seq", sequenceName="clientes_id_cliente_seq", initialValue=1,
            allocationSize=1)
    @Column(name="id_cliente")
    private long idCliente;
    
    @Column(name="nombre", nullable=false)
    private String nombre;
    
    @Column(name="correo", unique=true, nullable=false)
    private String correo;
    
    @Column(name="numero")
    private String numero;
    
    public PojoCliente() {
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
