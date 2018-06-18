package dao;

import models.Artist;
import models.Release;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oArtistDaoTest {
    private Sql2oArtistDao artistDao;
    private Sql2oReleaseDao releaseDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        artistDao = new Sql2oArtistDao(sql2o);
        releaseDao = new Sql2oReleaseDao(sql2o);
        conn = sql2o.open();
    }

    public Artist setupNewArtist(){
        return new Artist("Rolling Stones", "www.test.url");
    }

    public Release setupNewRelease(){
        return new Release("Talkie Walkie", "Virgin", "VI34567", 5,"picture", 5, "ebay", "LP", 20, "2004-10-10" , true, "www.air.com" );
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingArtistSetsId() throws Exception{
        Artist testArtist = setupNewArtist();
        int originalId = testArtist.getId();
        artistDao.add(testArtist);
        assertNotEquals(originalId, testArtist.getId());
    }

    @Test
    public void getAll() {
        Artist testArtist = setupNewArtist();
        Artist testArtist2 = setupNewArtist();
        artistDao.add(testArtist);
        artistDao.add(testArtist2);
        assertEquals(2,  artistDao.getAll().size());


    }

    @Test
    public void addArtistToRelease() {
        Artist testArtist = setupNewArtist();
        artistDao.add(testArtist);
        Release testRelease = setupNewRelease();
        releaseDao.add(testRelease);
        Release testRelease2 = setupNewRelease();
        releaseDao.add(testRelease2);

        artistDao.addArtistToRelease(testArtist, testRelease);
        artistDao.addArtistToRelease(testArtist, testRelease2);

        assertEquals(2, artistDao.getAllReleasesByArtistId(testArtist.getId()).size());
    }

    @Test
    public void findById() {
        Artist testArtist = setupNewArtist();
        artistDao.add(testArtist);
        Artist foundArtist = artistDao.findById(testArtist.getId());
        assertEquals("Rolling Stones", foundArtist.getName() );
    }

    @Test
    public void update() {
        Artist testArtist = setupNewArtist();
        artistDao.add(testArtist);
        int idOfArtistToUpdate = testArtist.getId();
        artistDao.update(idOfArtistToUpdate, "The Cramps", "www.mtv2.com");
        assertEquals("www.mtv2.com", artistDao.findById(idOfArtistToUpdate).getImageUrl());
    }

    @Test
    public void deleteById() {
        Artist testArtist = setupNewArtist();
        artistDao.add(testArtist);
        Artist testArtist2 = setupNewArtist();
        artistDao.add(testArtist2);
        artistDao.deleteById(testArtist.getId());
        assertEquals(1, artistDao.getAll().size());
    }


}