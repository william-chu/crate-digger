
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import dao.Sql2oReleaseDao;
import dao.Sql2oArtistDao;
import models.Artist;
import models.Release;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) { //type “psvm + tab” to autocreate this
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/cratedigger.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oReleaseDao releaseDao = new Sql2oReleaseDao(sql2o);
        Sql2oArtistDao artistDao = new Sql2oArtistDao(sql2o);

        //get: show all tasks in all categories and show all categories
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Release> recentReleases = releaseDao.getRecent();
            model.put("releases", recentReleases);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/releases", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Release> allReleases = releaseDao.getAll();
            model.put("releases", allReleases);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/releases/wishlist", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Release> wishlist = releaseDao.getWishlist();
            model.put("wishlist", wishlist);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/artists", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Artist> allArtists = artistDao.getAll();
            model.put("artists", allArtists);
            return new ModelAndView(model, "artists.hbs");
        }, new HandlebarsTemplateEngine());

        get("/artists/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfArtistToFind = Integer.parseInt(req.params("id"));
            Artist artist = artistDao.findById(idOfArtistToFind);
            List<Release> releases = artistDao.getAllReleasesByArtistId(idOfArtistToFind);
            model.put("artist", artist);
            model.put("releases", releases);
            return new ModelAndView(model, "artist-detail.hbs");
        }, new HandlebarsTemplateEngine());

        get("/releases/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfReleaseToFind = Integer.parseInt(req.params("id"));
            Release release = releaseDao.findById(idOfReleaseToFind);
            List<Artist> artists = releaseDao.getAllArtistsByReleaseId(idOfReleaseToFind);
            model.put("artists", artists);
            model.put("release", release);
            return new ModelAndView(model, "release-detail.hbs");
        }, new HandlebarsTemplateEngine());
    }
}