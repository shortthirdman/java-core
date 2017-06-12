package com.shortthirdman.core.framework.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.cfg.AnnotationConfiguration;

import com.shortthirdman.core.framework.hibernate.model.Track;

public class ProjectionsCount {
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

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        final Session session = getSession();
        try {
            Criteria criteria = session.createCriteria(Track.class).setProjection(Projections.rowCount());

            List result = criteria.list();
            if (!result.isEmpty()) {
                Integer rowCount = (Integer) result.get(0);
                System.out.println("Total records: " + rowCount);
            }
        } finally {
            session.close();
        }
    }
}