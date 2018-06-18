package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrackTest {

    @Test
    public void newTrackInstantiatesCorrectly() {
        Track testTrack = new Track("God's Plan", 1);
        assertTrue(testTrack instanceof Track);
    }

    @Test
    public void setId() {
        Track testTrack = new Track("God's Plan", 1);
        testTrack.setId(2);
        assertEquals(2, testTrack.getId());
    }

    @Test
    public void setTitle() {
        Track testTrack = new Track("God's Plan", 1);
        testTrack.setTitle("Electric Boogaloo");
        assertEquals("Electric Boogaloo", testTrack.getTitle());
    }

    @Test
    public void setReleaseId() {
        Track testTrack = new Track("God's Plan", 1);
        testTrack.setReleaseId(2);
        assertEquals(2, testTrack.getReleaseId());
    }
}