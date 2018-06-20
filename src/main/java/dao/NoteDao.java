package dao;

import models.Note;

import java.util.List;

public interface NoteDao {
    // LIST
    List<Note> getAllByReleaseId(int id);
    Note findById(int id);

    // CREATE
    void add(Note note);


    // UPDATE
    void update(int id, String content);

    // DELETE
    void deleteById(int id);
    void clearAllNotesByRelesaseId( int id);
    void clearAll();
}
