package dao;

import models.Note;

import java.util.List;

public interface NoteDao {
    // CREATE
    void add(Note note);

    // READ
    List<Note> getAllByReleaseId(int id);
    Note findById(int id);

    // UPDATE
    void update(int id, String content);

    // DELETE
    void deleteById(int id);
    void clearAllNotesByRelesaseId( int id);
    void clearAll();
}
