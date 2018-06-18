package dao;

import models.Artist;
import models.Release;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oReleaseDaoTest {

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
    public void addingReleaseSetsId() {
        Release testRelease = setupNewRelease();
        int originalId = testRelease.getId();
        releaseDao.add(testRelease);
        assertNotEquals(originalId, testRelease.getId());
    }

    @Test
    public void getAll() {
        Release testRelease = setupNewRelease();
        releaseDao.add(testRelease);
        Release testRelease2 = setupNewRelease();
        releaseDao.add(testRelease2);
        assertEquals(2, releaseDao.getAll().size());
    }

    @Test
    public void getAllArtistsByReleaseId() {
        Artist testArtist = setupNewArtist();
        artistDao.add(testArtist);
        Artist testArtist2 = new Artist("The Cramps", "www.mtv2.com");

        Release testRelease = setupNewRelease();
        releaseDao.add(testRelease);

        artistDao.addArtistToRelease(testArtist, testRelease);
        artistDao.addArtistToRelease(testArtist2, testRelease);

        assertEquals(2, releaseDao.getAllArtistsByReleaseId(testRelease.getId()).size());
    }

    @Test
    public void findById() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteById() {
    }
}