package dao;

import models.Track;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oTrackDao implements TrackDao {
    private final Sql2o sql2o;

    public Sql2oTrackDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Track> getAllByReleaseId(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM tracks WHERE releaseId = :id")
                    .addParameter("id", id)
                    .executeAndFetch(Track.class);
        }
    }

    @Override
    public void add(Track track) {
        String sql = "INSERT INTO tracks (title, releaseId) VALUES (:title, :releaseId)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(track)
                    .executeUpdate()
                    .getKey();
            track.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void update(int id, String title) {
        String sql = "UPDATE tracks SET title = :title WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("title", title)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from tracks WHERE id = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    @Override
    public void clearAllTracksByRelesaseId(int id) {
        String sql = "DELETE from tracks WHERE releaseId = :id";
        try(Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from tracks";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }

    }
}
