package dao;

import models.Track;

import java.util.List;

public interface TrackDao {
    // CREATE
    void add(Track track);

    // READ
    List<Track> getAllByReleaseId(int id);
    Track findById(int id);

    // UPDATE
    void update(int id, String title);

    // DELETE
    void deleteById(int id);
    void clearAllTracksByRelesaseId(int id);
    void clearAll();
}
