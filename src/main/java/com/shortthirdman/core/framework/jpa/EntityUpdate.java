package com.shortthirdman.core.framework.jpa;

import com.shortthirdman.core.framework.model.Artist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityUpdate {
    public static final String PERSISTENCE_UNIT_NAME = "music";

    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        ArtistDao dao = new ArtistDao(em);
        Artist artist = dao.findById(1L);
        System.out.println("Artist = " + artist);

        artist.setName("The New Artist Name");
        dao.updateArtist(artist);

        artist = dao.findById(artist.getId());
        System.out.println("Artist = " + artist);
    }
}