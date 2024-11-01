package org.uv.dsweb.practica04;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class VentaDAO implements IDAOGeneral<PojoVenta, Long> {

    @Override
    public PojoVenta buscarPorID(Long id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.getCurrentSession();
        Transaction transaction = null;
        PojoVenta venta = null;

        try {
            transaction = session.beginTransaction();
            venta = (PojoVenta) session.get(PojoVenta.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return venta;
    }

    @Override
    public List<PojoVenta> buscarTodos() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.getCurrentSession();
        Transaction transaction = null;
        List<PojoVenta> ventas = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            ventas = session.createQuery("FROM ventas").list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return ventas;
    }

    @Override
    public boolean guardar(PojoVenta venta) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.getCurrentSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(venta);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Long id, PojoVenta venta) { 
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.getCurrentSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            PojoVenta existingVenta = (PojoVenta) session.get(PojoVenta.class, id);
            if (existingVenta != null) {
                if (venta.getFechaVenta() != null) existingVenta.setFechaVenta(venta.getFechaVenta());
                if (venta.getTotal() != null) existingVenta.setTotal(venta.getTotal());
                if (venta.getCliente() != null) existingVenta.setCliente(venta.getCliente());

                session.update(existingVenta);
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
        }
    }



    @Override
    public boolean eliminar(Long id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.getCurrentSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            PojoVenta venta = (PojoVenta) session.get(PojoVenta.class, id);
            if (venta != null) {
                session.delete(venta);
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
        }
    }


    public List<PojoVenta> cargarTodasLasVentas() {
        try {
            return buscarTodos();
        } catch (Exception e) {
            e.printStackTrace(); 
            return new ArrayList<>();
        }
    }
}
