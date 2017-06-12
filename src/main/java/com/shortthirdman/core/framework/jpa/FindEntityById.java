package com.shortthirdman.core.framework.jpa;

import com.shortthirdman.core.framework.model.Artist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

public class FindEntityById {
    public static final String PERSISTENCE_UNIT_NAME = "music";

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        ArtistDao dao = new ArtistDao(em);

        //
        // Find an artist with ID = 1 from the database. The entity is
        // exists in the database.
        //
        Artist artist = dao.findById(1L);
        System.out.println("Artist = " + artist);

        try {
            //
            // Find an entity that is not exists in the database will
            // throw an exception.
            //
            artist = dao.findById(100L);
            System.out.println("Artist = " + artist);
        } catch (EntityNotFoundException e) {
            System.out.println("Can't find artist in the database.");
        }
    }
}