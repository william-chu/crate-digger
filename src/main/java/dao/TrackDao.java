package dao;

import models.Track;

import java.util.List;

public interface TrackDao {
    // LIST
    List<Track> getAllByReleaseId(int id);

    // CREATE
    void add(Track track);


    // UPDATE
    void update(int id, String title);

    // DELETE
    void deleteById(int id);
    void clearAllTracksByRelesaseId();
}
