package com.shortthirdman.core.framework.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.HibernateException;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.cfg.AnnotationConfiguration;

import com.shortthirdman.core.framework.hibernate.model.Genre;

import java.util.List;

public class RestrictionInDemo {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new AnnotationConfiguration().
                    configure("hibernate.cfg.xml").
                    buildSessionFactory();
        }
        catch (Throwable ex) {
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
            //
            // Create a Criteria an add an in constraint for the property id.
            // This in restrictions will return genre with id 1, 2, 3 and 4.
            //
            Criteria criteria = session.createCriteria(Genre.class)
                    .add(Restrictions.in("id", new Long[] {1l, 2l, 3l, 4l}));

            List<Genre> result = criteria.list();

            for (Genre genre : result) {
                System.out.println(genre.getId() + "; " + genre.getName());
            }
        } finally {
            session.close();
        }
    }
}