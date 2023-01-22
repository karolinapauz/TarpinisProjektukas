package repository;

import entity.Flight;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class FlightRepo {

    public void save(List<Object> objects) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        objects.forEach(session::save);
        transaction.commit();
        session.close();
    }

    public void delete(Flight flight) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(flight);
        transaction.commit();
        session.close();
    }

    public void update(Flight flight) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(flight);
        transaction.commit();
        session.close();
    }

    public List<Flight> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from Flight", Flight.class).list();
    }


}
