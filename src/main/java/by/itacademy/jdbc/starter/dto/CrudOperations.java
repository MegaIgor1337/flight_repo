package by.itacademy.jdbc.starter.dto;

import by.itacademy.jdbc.starter.entity.aircraft.Aircraft;
import by.itacademy.jdbc.starter.entity.airport.Airport;
import by.itacademy.jdbc.starter.mapper.Mapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudOperations<E, I extends Serializable> {
    default List<E> get() {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<E> eList = session.createQuery("FROM " + getEntityClass().getName(), getEntityClass()).list();
            session.getTransaction().commit();
            return eList;
        }
    }
    default Optional<E> find(I i) {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            E e = session.find(getEntityClass(), i);
            session.getTransaction().commit();
            return Optional.ofNullable(e);
        }
    }
    default boolean delete(I i) {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            E e = session.get(getEntityClass(), i);
            session.delete(e);
            session.getTransaction().commit();
            return true;
        }
    }

    default boolean update(E e) {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(e);
            session.getTransaction().commit();
            return true;
        }
    }

    default boolean save(E e) {
        Configuration configuration = new Configuration();
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(e);
            session.getTransaction().commit();
            return true;
        }
    }

    Class<E> getEntityClass();

}
