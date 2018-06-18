package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class NoteTest {

    @Test
    public void newNoteInstantiatesCorrectly() {
        Note testNote = new Note("NM lyric insert", 1);
        assertTrue(testNote instanceof Note);
    }

    @Test
    public void setId() {
        Note testNote = new Note("NM lyric insert", 1);
        testNote.setId(1);
        assertEquals(1, testNote.getId());
    }

    @Test
    public void setContent() {
        Note testNote = new Note("NM lyric insert", 1);
        testNote.setConent("spilled coffee on sleeve");
        assertEquals("spilled coffee on sleeve", testNote.getConent());
    }


    @Test
    public void setReleaseId() {
        Note testNote = new Note("NM lyric insert", 1);
        testNote.setReleaseId(3);
        assertEquals(3, testNote.getReleaseId());
    }
}