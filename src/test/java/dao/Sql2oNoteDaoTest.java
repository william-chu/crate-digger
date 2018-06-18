package dao;

import models.Note;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oNoteDaoTest {

    private Sql2oNoteDao noteDao;
//    private Sql2oReleaseDao releaseDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        noteDao = new Sql2oNoteDao(sql2o);
//        releaseDao = new Sql2oReleaseDao(sql2o);
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

    }

    @Test
    public void update() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void clearAllNotesByRelesaseId() {
    }
}