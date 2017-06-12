package com.shortthirdman.core.framework.hibernate;

import java.util.List;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.cfg.AnnotationConfiguration;

import com.shortthirdman.core.framework.hibernate.model.Recording;

public class FetchMode {
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

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        final Session session = getSession();
        try {
            Criteria criteria = session.createCriteria(Recording.class)
                    .setFetchMode("artist", FetchMode.SELECT)
                    .setFetchMode("label", FetchMode.SELECT)
                    .add(Restrictions.eq("title", "Please Please Me"));

            List recordings = criteria.list();
            for (Recording recording : recordings) {
                System.out.println("Recording  = " + recording.getTitle());
                System.out.println("Artist     = " + recording.getArtist().getName());
            }

        } finally {
            session.close();
        }
    }
}