package models;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class ReleaseTest {


    public Release setupRelease(){
        return new Release("Talkie Walkie", "Virgin", "VI34567", 5,"picture", 5, "ebay", "LP", 20, "2004-10-10" , true, "www.air.com" );
    }

    @Test
    public void newReleaseInstantiatesCorrectly() {
        Release testRelease = setupRelease();
        assertTrue(testRelease instanceof Release);
    }

    @Test
    public void setId() {
        Release testRelease = setupRelease();
        testRelease.setId(1);
        assertEquals(1, testRelease.getId());
    }

    @Test
    public void setTitle() {
        Release testRelease = setupRelease();
        testRelease.setTitle("England's Newest Hitmakers");
        assertEquals("England's Newest Hitmakers", testRelease.getTitle());
    }

    @Test
    public void setLabel() {
        Release testRelease = setupRelease();
        testRelease.setLabel("Decca");
        assertEquals("Decca", testRelease.getLabel());
    }

    @Test
    public void setLabelNumber() {
        Release testRelease = setupRelease();
        testRelease.setLabelNumber("zzz");
        assertEquals("zzz", testRelease.getLabelNumber());
    }

    @Test
    public void setMediaCondition() {
        Release testRelease = setupRelease();
        testRelease.setMediaCondition(4);
        assertEquals(4, testRelease.getMediaCondition());
    }

    @Test
    public void setSleeveType() {
        Release testRelease = setupRelease();
        testRelease.setSleeveType("Cardboard Jacket");
        assertEquals("Cardboard Jacket", testRelease.getSleeveType());
    }

    @Test
    public void setSleeveCondition() {
        Release testRelease = setupRelease();
        testRelease.setSleeveCondition(2);
        assertEquals(2, testRelease.getSleeveCondition());
    }

    @Test
    public void setSeller() {
        Release testRelease = setupRelease();
        testRelease.setSeller("John");
        assertEquals("John", testRelease.getSeller());
    }

    @Test
    public void setMediaType() {
        Release testRelease = setupRelease();
        testRelease.setMediaType("12\" Single");
        assertEquals("12\" Single", testRelease.getMediaType());
    }

    @Test
    public void setPrice() {
        Release testRelease = setupRelease();
        testRelease.setPrice(2000);
        assertEquals(2000, testRelease.getPrice());
    }

    @Test
    public void setDatePurchased() {
        Release testRelease = setupRelease();
        testRelease.setDatePurchased("2017-12-07");
        assertEquals("2017-12-07", testRelease.getDatePurchased());
    }

    @Test
    public void setInCollection() {
        Release testRelease = setupRelease();
        testRelease.setInCollection(false);
        assertEquals(false, testRelease.isInCollection());
    }

    @Test
    public void setImageUrl() {
        Release testRelease = setupRelease();
        testRelease.setImageUrl("www.mtv.com");
        assertEquals("www.mtv.com", testRelease.getImageUrl());
    }
}