package dao;

import models.Artist;
import models.Release;

import java.util.List;

public interface ArtistDao {
    // CREATE
    void add(Artist artist);
    void addArtistToRelease(Artist artist, Release release);

    //READ
    List<Artist> getAll();
    List<Release> getAllReleasesByArtistId(int artistId);
    List<Release> getWishlistByArtistId(int artistId);
    Artist findById(int id);
    Artist findByName(String name);

    // UPDATE
    void update(int id, String name, String imageUrl);

    // DELETE
    void deleteById(int id);
    void clearAll();
}
