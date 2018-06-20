package dao;

import models.Artist;
import models.Release;

import java.util.List;

public interface ArtistDao {
    // LIST
    List<Artist> getAll();
    List<Release> getAllReleasesByArtistId(int artistId);

    // CREATE
    void add(Artist artist);
    void addArtistToRelease(Artist artist, Release release);

    //READ
    Artist findById(int id);
    Artist findByName(String name);



    // UPDATE
    void update(int id, String name, String imageUrl);

    // DELETE
    void deleteById(int id);
    void clearAll();
}
