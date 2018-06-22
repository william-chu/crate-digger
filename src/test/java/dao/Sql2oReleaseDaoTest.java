package dao;

import models.Artist;
import models.Release;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oReleaseDaoTest {

    private static Sql2oArtistDao artistDao;
    private static Sql2oReleaseDao releaseDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/cratedigger_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
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
        System.out.println("clearing database");
        releaseDao.clearAll();
        artistDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }

    @Test
    public void add_addingReleaseSetsId() {
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
        Release testRelease = setupNewRelease();
        releaseDao.add(testRelease);
        Release foundRelease = releaseDao.findById(testRelease.getId());
        assertEquals("Talkie Walkie", foundRelease.getTitle() );
    }

    @Test
    public void update() {
        Release testRelease = setupNewRelease();
        releaseDao.add(testRelease);
        int idOfReleaseToUpdate = testRelease.getId();
        releaseDao.update(idOfReleaseToUpdate, "England's Newest Hitmakers", "Decca", "xxx", 4, "cardboard jacket", 3, "Tom", "EP", 2000, "2010-04-04",false, "www.mtv.com");
        assertEquals("cardboard jacket", releaseDao.findById(idOfReleaseToUpdate).getSleeveType());
    }

    @Test
    public void deleteById() {
        Release testRelease = setupNewRelease();
        releaseDao.add(testRelease);
        Release testRelease2 = setupNewRelease();
        releaseDao.add(testRelease2);
        releaseDao.deleteById(testRelease.getId());
        assertEquals(1, releaseDao.getAll().size());
    }

    @Test
    public void getRecent() {
        Release testRelease = setupNewRelease();
        Release testRelease2 = setupNewRelease();
        Release testRelease3 = setupNewRelease();
        Release testRelease4 = setupNewRelease();
        Release testRelease5 = setupNewRelease();
        Release testRelease6 = setupNewRelease();
        Release testRelease7 = setupNewRelease();
        Release testRelease8 = setupNewRelease();
        Release testRelease9 = setupNewRelease();
        Release testRelease10 = setupNewRelease();
        releaseDao.add(testRelease);
        releaseDao.add(testRelease2);
        releaseDao.add(testRelease3);
        releaseDao.add(testRelease4);
        releaseDao.add(testRelease5);
        releaseDao.add(testRelease6);
        releaseDao.add(testRelease7);
        releaseDao.add(testRelease8);
        releaseDao.add(testRelease9);
        releaseDao.add(testRelease10);
        assertEquals(9, releaseDao.getRecent().size());
    }

    @Test
    public void getWishlist() {
        Release testRelease = setupNewRelease();
        testRelease.setInCollection(false);
        releaseDao.add(testRelease);
        assertEquals(1, releaseDao.getWishlist().size());
    }

    @Test
    public void getAllSeventies() {
        Release testRelease = setupNewRelease();
        testRelease.setInCollection(true);
        testRelease.setMediaType("78 RPM");
        releaseDao.add(testRelease);
        assertEquals(1, releaseDao.getAllSeventies().size());
    }

    @Test
    public void getAllSingles() {
        Release testRelease = setupNewRelease();
        testRelease.setInCollection(true);
        testRelease.setMediaType("Single");
        releaseDao.add(testRelease);
        assertEquals(1, releaseDao.getAllSingles().size());
    }

    @Test
    public void getAllEps() {
        Release testRelease = setupNewRelease();
        testRelease.setInCollection(true);
        testRelease.setMediaType("EP");
        releaseDao.add(testRelease);
        assertEquals(1, releaseDao.getAllEps().size());
    }

    @Test
    public void getAllLps() {
        Release testRelease = setupNewRelease();
        testRelease.setInCollection(true);
        testRelease.setMediaType("LP");
        releaseDao.add(testRelease);
        assertEquals(1, releaseDao.getAllLps().size());
    }

    @Test
    public void getTotalValue() {
        Release testRelease = setupNewRelease();
        testRelease.setInCollection(true);
        releaseDao.add(testRelease);
        Release testRelease2 = setupNewRelease();
        testRelease2.setInCollection(true);
        releaseDao.add(testRelease2);
        int expectedTotal = testRelease.getPrice() + testRelease2.getPrice();
        assertEquals(expectedTotal, releaseDao.getTotalValue());
    }

    @Test
    public void clearAll() {
        Release testRelease = setupNewRelease();
        releaseDao.add(testRelease);
        Release testRelease2 = setupNewRelease();
        releaseDao.add(testRelease2);
        releaseDao.clearAll();
        assertEquals(0, releaseDao.getAll().size());
    }
}