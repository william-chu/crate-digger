package dao;

import models.Track;
import models.Track;
import models.Track;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oTrackDaoTest {
    private static Sql2oTrackDao trackDao;
    private static Connection conn;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/cratedigger_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        trackDao = new Sql2oTrackDao(sql2o);
        conn = sql2o.open();
    }
    
    public Track setupNewTrack(){
        return new Track("Good Song", 1);
    } 

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        trackDao.clearAll();
    }

    @AfterClass
    public static void shutDown() throws Exception{
        conn.close();
        System.out.println("connection closed");
    }
    

    @Test
    public void getAllByReleaseId() {
        Track testTrack = setupNewTrack();
        Track testTrack2 = new Track("Cool dude track", 2);
        Track testTrack3 = new Track("whatever song", 1);
        trackDao.add(testTrack);
        trackDao.add(testTrack2);
        trackDao.add(testTrack3);
        assertEquals(2, trackDao.getAllByReleaseId(1).size());
    }

    @Test
    public void add() {
        Track testTrack = setupNewTrack();
        int originalIdOfTrack = testTrack.getId();
        trackDao.add(testTrack);
        assertNotEquals(originalIdOfTrack, testTrack.getId());
    }

    @Test
    public void update() {
        Track testTrack = setupNewTrack();
        trackDao.add(testTrack);
        trackDao.update(1, "cool man track");
        List<Track> foundTracks = trackDao.getAllByReleaseId(1);
        assertEquals("cool man track", foundTracks.get(0).getTitle());
    }

    @Test
    public void deleteById() {
        Track testTrack = setupNewTrack();
        trackDao.add(testTrack);
        trackDao.deleteById(testTrack.getId());
        assertEquals(0, trackDao.getAllByReleaseId(1).size());
    }
    

    @Test
    public void clearAllTracksByRelesaseId() {
        Track testTrack = setupNewTrack();
        trackDao.add(testTrack);
        trackDao.clearAllTracksByRelesaseId(testTrack.getReleaseId());
        assertEquals(0, trackDao.getAllByReleaseId(1).size());
    }
}