package com.shortthirdman.core.framework.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.shortthirdman.core.framework.model.Artist;

public class EntityPersist {
    public static final String PERSISTENCE_UNIT_NAME = "music";

    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager manager = factory.createEntityManager();

        String[] artistNames = {"Bryan Adams", "Mr. Big", "Metallica"};

        ArtistDao dao = new ArtistDao(manager);

        for (String name : artistNames) {
            Artist artist = new Artist();
            artist.setName(name);
            dao.createArtist(artist);
        }

        List artistList = dao.getArtists();
        for (Artist artist : artistList) {
            System.out.println("artist = " + artist);
        }
    }
}
