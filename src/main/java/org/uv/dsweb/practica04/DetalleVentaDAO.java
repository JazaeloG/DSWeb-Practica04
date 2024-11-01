package org.uv.dsweb.practica04;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DetalleVentaDAO implements IDAOGeneral<PojoDetalleVenta, Long> {

    @Override
    public PojoDetalleVenta buscarPorID(Long id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession(); 
        Transaction transaction = null;
        PojoDetalleVenta detalleVenta = null;

        try {
            transaction = session.beginTransaction();
            detalleVenta = session.get(PojoDetalleVenta.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close(); // Cerrar sesión
        }

        return detalleVenta;
    }

    @Override
    public List<PojoDetalleVenta> buscarTodos() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        List<PojoDetalleVenta> detallesVenta = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            detallesVenta = session.createQuery("FROM detalles_venta", PojoDetalleVenta.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return detallesVenta;
    }

    @Override
    public boolean guardar(PojoDetalleVenta detalleVenta) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession(); 
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(detalleVenta);
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
    public boolean modificar(Long id, PojoDetalleVenta detalleVenta) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            PojoDetalleVenta existingDetalle = session.get(PojoDetalleVenta.class, id);
            if (existingDetalle != null) {
                existingDetalle.setVenta(detalleVenta.getVenta());
                existingDetalle.setProducto(detalleVenta.getProducto());
                existingDetalle.setPrecio(detalleVenta.getPrecio());
                existingDetalle.setCantidad(detalleVenta.getCantidad());
                existingDetalle.setDescripcion(detalleVenta.getDescripcion());
                session.update(existingDetalle);
                transaction.commit();
                return true;
            } else {
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
            PojoDetalleVenta detalleVenta = session.get(PojoDetalleVenta.class, id);
            if (detalleVenta != null) {
                session.delete(detalleVenta);
                transaction.commit();
                return true;
            } else {
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

    public List<PojoDetalleVenta> cargarTodosLosDetalles() {
        try {
            return buscarTodos();
        } catch (Exception e) {
            e.printStackTrace(); 
            return new ArrayList<>();
        }
    }
}
