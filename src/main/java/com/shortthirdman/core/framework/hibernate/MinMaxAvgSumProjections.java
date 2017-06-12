package com.shortthirdman.core.framework.hibernate.criteria;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.cfg.AnnotationConfiguration;

import com.shortthirdman.core.framework.hibernate.model.Track;

public class MinMaxAvgSumProjections {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new AnnotationConfiguration().
                    configure("hibernate.cfg.xml").
                    buildSessionFactory();
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
            Criteria criteria = session.createCriteria(Track.class)
                    .setProjection(Projections.max("duration"));
            Integer maxDuration = (Integer) criteria.uniqueResult();
            System.out.println("Max Track Duration = " + maxDuration);

            criteria.setProjection(Projections.min("duration"));
            Integer minDuration = (Integer) criteria.uniqueResult();
            System.out.println("Min Track Duration = " + minDuration);
            
            criteria.setProjection(Projections.avg("duration"));
            Double avgDuration = (Double) criteria.uniqueResult();
            System.out.println("Avg Track Duration = " + avgDuration);
            
            criteria.setProjection(Projections.sum("duration"));
            Integer totalDuration = (Integer) criteria.uniqueResult();
            System.out.println("Total Track Duration = " + totalDuration);
        } finally {
            session.close();
        }
    }
}