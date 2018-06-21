package dao;

import models.Artist;
import models.Release;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oReleaseDao implements ReleaseDao {

    private final Sql2o sql2o;

    public Sql2oReleaseDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Release> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM releases WHERE isInCollection = :collectionBoolean")
                    .addParameter("collectionBoolean", true)
                    .executeAndFetch(Release.class);
        }
    }

    @Override
    public List<Release> getAllEps() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM releases WHERE isInCollection = true AND mediaType = :large OR mediaType = :small OR mediaType = :medium")
                    .addParameter("large", "12\"")
                    .addParameter("small", "EP")
                    .addParameter("medium", "10\"")
                    .executeAndFetch(Release.class);
        }
    }

    @Override
    public List<Release> getAllLps() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM releases WHERE isInCollection = true AND mediaType = :lp")
                    .addParameter("lp", "LP")
                    .executeAndFetch(Release.class);
        }
    }
    @Override
    public List<Release> getAllSingles() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM releases WHERE isInCollection = true AND mediaType = :large OR mediaType = :small")
                    .addParameter("large", "12\" Single")
                    .addParameter("small", "Single")
                    .executeAndFetch(Release.class);
        }
    }

    @Override
    public List<Release> getAllSeventies() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM releases WHERE isInCollection = true AND mediaType = :rpm")
                    .addParameter("rpm", "78 RPM")
                    .executeAndFetch(Release.class);
        }
    }

    @Override
    public int getTotalValue() {
        try(Connection con = sql2o.open()){
            List<Release> releases = con.createQuery("SELECT * FROM releases WHERE isInCollection = true")
                    .executeAndFetch(Release.class);
            int total = 0;
            for (Release release:releases) {
                total += release.getPrice();
            }
            return total;
        }
    }

    @Override
    public List<Artist> getAllArtistsByReleaseId(int releaseId) {
        ArrayList<Artist> artists = new ArrayList<>();

        String joinQuery = "SELECT artistId FROM artists_releases WHERE releaseId = :releaseId";


        try (Connection con = sql2o.open()) {
            List<Integer> allArtistIds = con.createQuery(joinQuery)
                    .addParameter("releaseId", releaseId)
                    .executeAndFetch(Integer.class);
            for (Integer artistId : allArtistIds){
                String artistQuery = "SELECT * FROM artists WHERE id = :artistId";
                artists.add(
                        con.createQuery(artistQuery)
                                .addParameter("artistId", artistId)
                                .executeAndFetchFirst(Artist.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return artists;
    }

    @Override
    public void add(Release release) {
        String sql = "INSERT INTO releases (title, label, labelNumber, mediaCondition, sleeveType, sleeveCondition, seller, mediaType, price, datePurchased, isInCollection, imageUrl) VALUES (:title, :label, :labelNumber, :mediaCondition, :sleeveType, :sleeveCondition, :seller, :mediaType, :price, :datePurchased, :isInCollection, :imageUrl)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .addParameter("isInCollection", release.isInCollection())
                    .bind(release)
                    .executeUpdate()
                    .getKey();
            release.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Release findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM releases WHERE id = :id ")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Release.class);
        }
    }

    @Override
    public void update(int id, String title, String label, String labelNumber, int mediaCondition, String sleeveType, int sleeveCondition, String seller, String mediaType, int price, String datePurchased, boolean isInCollection, String imageUrl) {
        String sql = "UPDATE releases SET (title, label, labelNumber, mediaCondition, sleeveType, sleeveCondition, seller, mediaType, price, datePurchased, isInCollection, imageUrl) = (:title, :label, :labelNumber, :mediaCondition, :sleeveType, :sleeveCondition, :seller, :mediaType, :price, :datePurchased, :isInCollection, :imageUrl) WHERE id = :id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("title", title)
                    .addParameter("label", label)
                    .addParameter("labelNumber", labelNumber)
                    .addParameter("mediaCondition", mediaCondition)
                    .addParameter("sleeveType", sleeveType)
                    .addParameter("sleeveCondition", sleeveCondition)
                    .addParameter("seller", seller)
                    .addParameter("mediaType", mediaType)
                    .addParameter("price", price)
                    .addParameter("datePurchased", datePurchased)
                    .addParameter("isInCollection", isInCollection)
                    .addParameter("imageUrl", imageUrl)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from releases WHERE id = :id";
        String deleteJoin = "DELETE from artists_releases WHERE releaseId = :releaseId";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("releaseId", id)
                    .executeUpdate();

        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

    @Override
    public List<Release> getRecent() {
        try(Connection con = sql2o.open()){
            return  con.createQuery("SELECT * FROM releases WHERE isInCollection = true ORDER BY id DESC LIMIT 9")
                    .executeAndFetch(Release.class);
        }
    }
    @Override
    public List<Release> getWishlist() {
        try(Connection con = sql2o.open()){
            return  con.createQuery("SELECT * FROM releases WHERE isInCollection = :collectionBoolean")
                    .addParameter("collectionBoolean", false)
                    .executeAndFetch(Release.class);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from releases";
        String deleteJoin = "DELETE from artists_releases";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }

//    @Override
//    public List<Release> search(String query) {
//        try(Connection con = sql2o.open()){
//            return con.createQuery("SELECT * FROM releases WHERE * LIKE :query")
//                    .addParameter("query", "%"+ query +"%")
//                    .executeAndFetch(Release.class);
//        }
//    }
}
