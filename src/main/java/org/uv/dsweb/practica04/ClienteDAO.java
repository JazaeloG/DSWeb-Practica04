package org.uv.dsweb.practica04;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ClienteDAO implements IDAOGeneral<PojoCliente, Long> {

    @Override
    public PojoCliente buscarPorID(Long id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null; 
        PojoCliente cliente = null;

        try {
            transaction = session.beginTransaction();
            cliente = session.get(PojoCliente.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return cliente;
    }

    @Override
    public List<PojoCliente> buscarTodos() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;
        List<PojoCliente> clientes = new ArrayList<>();

        try {
            transaction = session.beginTransaction();
            clientes = session.createQuery("FROM clientes", PojoCliente.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); 
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return clientes;
    }

    @Override
    public boolean guardar(PojoCliente cliente) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(cliente);
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
    public boolean modificar(Long id, PojoCliente cliente) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession(); 
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            PojoCliente existingCliente = session.get(PojoCliente.class, id);
            if (existingCliente != null) {
                existingCliente.setNombre(cliente.getNombre());
                existingCliente.setCorreo(cliente.getCorreo());
                existingCliente.setNumero(cliente.getNumero());
                session.update(existingCliente);
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
            PojoCliente cliente = session.get(PojoCliente.class, id);
            if (cliente != null) {
                session.delete(cliente);
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

    public List<PojoCliente> cargarTodosLosClientes() {
        try {
            return buscarTodos();
        } catch (Exception e) {
            e.printStackTrace(); 
            return new ArrayList<>();
        }
    }
}
