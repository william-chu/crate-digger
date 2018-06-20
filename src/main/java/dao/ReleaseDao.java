package dao;

import models.Artist;
import models.Release;


import java.util.List;

public interface ReleaseDao {
    // LIST
    List<Release> getAll();
    List<Release> getRecent();
    List<Release> getWishlist();
    List<Artist> getAllArtistsByReleaseId(int releaseId);
    List<Release> getAllLps();
    List<Release> getAllEps();
    List<Release> getAllSingles();
    List<Release> getAllSeventies();


    // CREATE
    void add(Release release);

    //READ
    Release findById(int id);
    int getTotalValue();



    // UPDATE
    void update(int id, String title, String label, String labelNumber, int mediaCondition, String sleeveType, int sleeveCondition, String seller, String mediaType, int price, String datePurchased, boolean isInCollection, String imageUrl);

    // DELETE
    void deleteById(int id);
    void clearAll();
}
