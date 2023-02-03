package repository;

import entity.Flight;
import entity.Persistable;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class FlightRepo {

    public void save(Persistable object) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(object);
        transaction.commit();
        session.close();
    }
    public void saveAll(List<Flight> flights) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        flights.forEach(session::save);
        transaction.commit();
        session.close();
    }
    public void delete(Persistable object) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(object);
        transaction.commit();
        session.close();
    }
    public void update(Persistable object) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(object);
        transaction.commit();
        session.close();
    }

    public List<Flight> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from Flight", Flight.class).list();
    }


}
