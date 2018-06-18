package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArtistTest {


    @Test
    public void newArtistInstantiatesCorrectly() {
        Artist testArtist = new Artist("Rolling Stones", "www.test.url");
        assertTrue(testArtist instanceof Artist);

    }

    @Test
    public void setId() {
        Artist testArtist = new Artist("Rolling Stones", "www.test.url");
        testArtist.setId(1);
        assertEquals(1, testArtist.getId());
    }


    @Test
    public void setName() {
        Artist testArtist = new Artist("Rolling Stones", "www.test.url");
        testArtist.setName("Beck");
        assertEquals("Beck", testArtist.getName());
    }


    @Test
    public void setImageUrl() {
        Artist testArtist = new Artist("Rolling Stones", "www.test.url");
        testArtist.setImageUrl("www.beck.beck");
        assertEquals("www.beck.beck", testArtist.getImageUrl());
    }
}