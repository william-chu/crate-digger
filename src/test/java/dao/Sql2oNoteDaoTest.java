package dao;

import models.Note;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.*;

public class Sql2oNoteDaoTest {

    private Sql2oNoteDao noteDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        noteDao = new Sql2oNoteDao(sql2o);
        conn = sql2o.open();
    }

    public Note setupNewNote(){
        return new Note("Upgraded Sleeve", 1);
    }


    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingNoteSetsId() {
        Note testNote = setupNewNote();
        int originalIdOfNote = testNote.getId();
        noteDao.add(testNote);
        assertNotEquals(originalIdOfNote, testNote.getId());
    }

    @Test
    public void getAllByReleaseId() {
        Note testNote = setupNewNote();
        Note testNote2 = new Note("Cool dude", 2);
        Note testNote3 = new Note("whatever", 1);
        noteDao.add(testNote);
        noteDao.add(testNote2);
        noteDao.add(testNote3);
        assertEquals(2, noteDao.getAllByReleaseId(1).size());
    }

    @Test
    public void findById() {
        Note testNote = setupNewNote();
        Note testNote2 = new Note("Cool dude", 2);
        Note testNote3 = new Note("whatever", 1);
        noteDao.add(testNote);
        noteDao.add(testNote2);
        noteDao.add(testNote3);
        assertEquals("whatever", noteDao.findById(3).getContent());
    }

    @Test
    public void update() {
        Note testNote = setupNewNote();
        noteDao.add(testNote);
        noteDao.update(1, "cool man");
        List<Note> foundNotes = noteDao.getAllByReleaseId(1);
        assertEquals("cool man", foundNotes.get(0).getContent());
    }

    @Test
    public void deleteById() {
        Note testNote = setupNewNote();
        noteDao.add(testNote);
        noteDao.deleteById(testNote.getId());
        assertEquals(0, noteDao.getAllByReleaseId(1).size());
    }

    @Test
    public void clearAllNotesByRelesaseId() {
        Note testNote = setupNewNote();
        noteDao.add(testNote);
        noteDao.clearAllNotesByRelesaseId(testNote.getReleaseId());
        assertEquals(0, noteDao.getAllByReleaseId(1).size());
    }
}