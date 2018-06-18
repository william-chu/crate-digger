package dao;

import models.Artist;
import models.Release;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oArtistDao implements ArtistDao{

    private final Sql2o sql2o;

    public Sql2oArtistDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Artist artist) {
        String sql = "INSERT INTO artists (name, imageUrl) VALUES (:name, :imageUrl)";
        try(Connection con = sql2o.open()){ //try to open a connection
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
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM artists")
                    .executeAndFetch(Artist.class);
        }
    }

    @Override
    public List<Release> getAllReleasesByArtistId(int artistId) {
        return null;
    }

    @Override
    public void addArtistToRelease(Artist artist, Release release) {

    }

    @Override
    public Artist findById(int id) {
        return null;
    }

    @Override
    public void update(int id, String name) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void clearAllReleasesByArtistId(int artistId) {

    }
}
