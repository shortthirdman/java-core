package com.shortthirdman.core.framework.jpa;

import com.shortthirdman.core.framework.model.Artist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EntityRemove {
    public static final String PERSISTENCE_UNIT_NAME = "music";

    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager manager = factory.createEntityManager();

        ArtistDao dao = new ArtistDao(manager);
        System.out.println("Before Delete:");
        printArtists(dao.getArtists());

        //
        // Remove artist with Id = 1.
        //
        dao.deleteArtistById(1L);

        //
        // Remove artist with Id = 2.
        //
        Artist artist = dao.findById(2L);
        dao.deleteArtist(artist);

        System.out.println("After Delete:");
        printArtists(dao.getArtists());
    }

    private static void printArtists(List artists) {
        for (Artist artist : artists) {
            System.out.println("Artist = " + artist);
        }
    }
}