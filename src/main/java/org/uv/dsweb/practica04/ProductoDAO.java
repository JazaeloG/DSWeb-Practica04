/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.dsweb.practica04;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ProductoDAO implements IDAOGeneral<PojoProducto, Long>{
    @Override
    public PojoProducto buscarPorID(Long id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession(); 
        Transaction transaction = null;
        PojoProducto producto = null;

        try {
            transaction = session.beginTransaction();
            producto = (PojoProducto) session.get(PojoProducto.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return producto;
    }

    @Override
    public List<PojoProducto> buscarTodos() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        List<PojoProducto> productos = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            productos = session.createQuery("FROM productos", PojoProducto.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return productos;
    }

    @Override
    public boolean guardar(PojoProducto producto) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(producto);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean modificar(Long id, PojoProducto producto) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession(); 
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            PojoProducto existingProducto = (PojoProducto) session.get(PojoProducto.class, id);
            if (existingProducto != null) {
                existingProducto.setNombre(producto.getNombre());
                existingProducto.setPrecio(producto.getPrecio());
                existingProducto.setDescripcion(producto.getDescripcion());
                session.update(existingProducto);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false; 
        } finally {
            session.close();
        }
    }

    @Override
    public boolean eliminar(Long id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            PojoProducto producto = (PojoProducto) session.get(PojoProducto.class, id);
            if (producto != null) {
                session.delete(producto);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); 
            return false; 
        } finally {
            session.close();
        }
    }

    public List<PojoProducto> cargarTodosLosProductos() {
        try {
            return buscarTodos();
        } catch (Exception e) {
            e.printStackTrace(); 
            return new ArrayList<>();
        }
    }
}