package com.shortthirdman.core.framework.hibernate;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;

import com.shortthirdman.core.framework.hibernate.model.Genre;

public class UniqueResult {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    public static void main(String[] args) {
        final Session session = getSession();
        try {
            Criteria criteria = session.createCriteria(Genre.class).add(Restrictions.eq("id", new Long(1)));

            // Convenience method to return a single instance that matches the
            // query, or null if the query returns no results.
            Object result = criteria.uniqueResult();
            if (result != null) {
                Genre genre = (Genre) result;
                System.out.println("Genre = " + genre.getName());
            }
        } finally {
            session.close();
        }
    }
}