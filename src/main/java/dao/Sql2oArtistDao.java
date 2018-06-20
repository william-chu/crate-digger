package dao;

import models.Artist;
import models.Release;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oArtistDao implements ArtistDao {

    private final Sql2o sql2o;

    public Sql2oArtistDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Artist artist) {
        String sql = "INSERT INTO artists (name, imageUrl) VALUES (:name, :imageUrl)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(artist)
                    .executeUpdate()
                    .getKey();
            artist.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Artist> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM artists")
                    .executeAndFetch(Artist.class);
        }
    }

    @Override
    public List<Release> getAllReleasesByArtistId(int artistId) {
        ArrayList<Release> releases = new ArrayList<>();

        String joinQuery = "SELECT releaseId FROM artists_releases WHERE artistId = :artistId";


        try (Connection con = sql2o.open()) {
            List<Integer> allReleasesIds = con.createQuery(joinQuery)
                    .addParameter("artistId", artistId)
                    .executeAndFetch(Integer.class);
            for (Integer releaseId : allReleasesIds) {
                String releaseQuery = "SELECT * FROM releases WHERE id = :releaseId";
                releases.add(
                        con.createQuery(releaseQuery)
                                .addParameter("releaseId", releaseId)
                                .executeAndFetchFirst(Release.class));
            }
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
        return releases;
    }

    @Override
    public void addArtistToRelease(Artist artist, Release release) {
        String sql = "INSERT INTO artists_releases (artistId, releaseId) VALUES (:artistId, :releaseId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("artistId", artist.getId())
                    .addParameter("releaseId", release.getId())
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public Artist findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM artists WHERE id = :id ")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Artist.class);
        }
    }

    @Override
    public void update(int id, String name, String imageUrl) {
        String sql = "UPDATE artists SET (name, imageUrl) = (:name, :imageUrl) WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("imageUrl", imageUrl)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from artists WHERE id = :id";
        String deleteJoin = "DELETE from artists_releases WHERE artistId = :artistId";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
            con.createQuery(deleteJoin)
                    .addParameter("artistId", id)
                    .executeUpdate();

        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void clearAll() {
        String sql = "DELETE from artists";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }
}

